package org.example

open class StudentList {
    protected var students = mutableMapOf<Int, Student>()
    protected var autoIncrementNextId = 1

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