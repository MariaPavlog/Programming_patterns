package org.example

import java.io.File
import java.io.FileNotFoundException
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration

@Serializable
data class StudentSeьrializable(val id: Int,
                               val surname: String,
                               val name: String,
                               val secondname: String,
                               val phone: String? = null,
                               val telegram: String? = null,
                               val email: String? = null,
                               val git: String? = null) {
    constructor(student: Student) : this(student.id,
        student.surname,
        student.name,
        student.secondname,
        student.phone,
        student.telegram,
        student.email,
        student.git)
}

interface FormatStrategy {
    fun load(file: File) : MutableMap<Int, Student>
    fun save(file: File, students: MutableMap<Int, Student>)
}

class TXTFormatStrategy : FormatStrategy {
    override fun load(file: File) : MutableMap<Int, Student> {
        val students = mutableMapOf<Int, Student>()
        var currentLine = 1
        for (line in file.readLines()) {
            if (line.isNotEmpty()) {
                try {
                    val student = Student(line)
                    students[student.id] = student
                } catch (e: Exception) {
                    throw Exception("Ошибка при чтении файла '${file.path}', строка $currentLine: ${e.message}")
                }
            }
            currentLine++
        }
        return students
    }

    override fun save(file: File, students: MutableMap<Int, Student>) {
        file.printWriter().use {
            for (student in students.values) {
                it.println(student.toStringRow())
            }
        }
    }
}

class JSONFormatStrategy : FormatStrategy {
    override fun load(file: File) : MutableMap<Int, Student> {
        return Json.decodeFromString<Array<StudentSerializable>>(file.readText()).map {
            Student(it.id, it.surname, it.name, it.secondname, it.phone, it.telegram, it.email, it.git)
        }.associateBy({ it.id }, { it }).toMutableMap()
    }

    override fun save(file: File, students: MutableMap<Int, Student>) {
        val jsonString = Json.encodeToString(students.values.map { StudentSerializable(it) })
        file.writeText(jsonString)
    }
}

class YAMLFormatStrategy : FormatStrategy {
    override fun load(file: File) : MutableMap<Int, Student> {
        return Yaml.default.decodeFromString(ListSerializer(StudentSerializable.serializer()), file.readText()).map {
            Student(it.id, it.surname, it.name, it.secondname, it.phone, it.telegram, it.email, it.git)
        }.associateBy({ it.id }, { it }).toMutableMap()
    }

    override fun save(file: File, students: MutableMap<Int, Student>) {
        val config = YamlConfiguration(encodeDefaults = false)
        val yamlString = Yaml(configuration = config).encodeToString(students.values.map { StudentSerializable(it) })
        file.writeText(yamlString)
    }
}

class StudentList(var formatStrategy: FormatStrategy) {
    private var students = mutableMapOf<Int, Student>()
    private var autoIncrementNextId = 1

    fun load(filepath: String) {
        val file = File(filepath)
        if (!file.exists()) throw FileNotFoundException("Файл '$filepath' не найден")
        students = formatStrategy.load(file)
        autoIncrementNextId = (students.keys.maxOrNull() ?: 0) + 1
    }

    fun save(filepath: String) {
        val file = File(filepath)
        formatStrategy.save(file, students)
    }
    fun getStudentById(id: Int) = students[id] ?: throw IllegalArgumentException("Студент с ID $id не найден")

    fun getStudentShortList(k: Int, n: Int) : DataListStudentShort {
        if (k < 1) throw IllegalArgumentException("Значение k должно быть больше или равно 1")
        if (n < 0) throw IllegalArgumentException("Значение n не должно быть отрицательным")
        val studList = students.values.toList()
        val firstElem = (k - 1) * n
        if (firstElem >= studList.size || n == 0) return DataListStudentShort(listOf())
        val lastElem = (firstElem + n - 1).coerceAtMost(studList.size - 1)
        return DataListStudentShort(students.values.toList().slice(firstElem..lastElem).map {
            StudentShort(it)
        })
    }

    fun sortByStudentName() {
        students = students.toList()
            .sortedBy { "${it.second.surname} ${it.second.name} ${it.second.secondname}" }
            .toMap().toMutableMap()
    }

    fun add(student: Student) {
        students[autoIncrementNextId] = student.copyWithChangedId(autoIncrementNextId)
        autoIncrementNextId++
    }

    fun replace(id: Int, newStudent: Student) {
        if (!students.containsKey(id)) throw IllegalArgumentException("Студент с ID $id не найден")
        students[id] = newStudent.copyWithChangedId(id)
    }

    fun remove(id: Int) {
        if (!students.containsKey(id)) throw IllegalArgumentException("Студент с ID $id не найден")
        students.remove(id)
    }

    fun getStudentShortCount() = students.size
}