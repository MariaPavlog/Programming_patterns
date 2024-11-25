package org.example
import java.io.File
import java.io.FileNotFoundException

class Student(
    override val id: Int,
    name: String,
    surname: String,
    secondname: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
): SudentAbst(){
    companion object {
        fun readFromTxt(filePath: String): List<Student> {
            val file = File(filePath)
            if (!file.exists()) throw FileNotFoundException("Файл '$filePath' не найден")
            return buildList {
                var currentLine = 1
                for (line in file.readLines()) {
                    add(Student(line))
                    if (line.isNotEmpty()) {
                        try {
                            add(Student(line))
                        }
                        catch (e: Exception) {
                            throw Exception("Reading ERROR '$filePath', line $currentLine: ${e.message}")
                        }
                    }
                    currentLine++
                }
            }
        }
        fun writeToTxt(filePath: String, students: Iterable<Student>) {
            val file = File(filePath)
            file.printWriter().use {
                for (student in students) {
                    it.println(student.toStringRow())
                }
            }
        }
    }


    var name = name
        get() = field
        set(value) {
            if (CheckName(value)) field = value
            //else
                //throw IllegalArgumentException("Name is incorrect")
                //println("name")
        }
    var surname = surname
        get() = field
        set(value) {
            if (CheckSecondname(value)) field = value
                //else
                //throw IllegalArgumentException("Secondname is incorrect")
                //println("surname")
    }
    var secondname = secondname
        get() = field
        set(value) {
            if (CheckName(value)) field = value
            //else
                //throw IllegalArgumentException("Surname is incorrect")
                //println("secondname")
    }


    var phone = phone
        get() = field
        set(value) {
            if (CheckPhone(value)) field = value
            else throw IllegalArgumentException("Number is incorrect")
        }

    var telegram = telegram
        get() = field
        set(value) {
            if (CheckTelegram(value)) field = value
           // else
                //throw IllegalArgumentException("Inavlid name for nick in telegram")
                //println("telegram")
    }

    var email = email
        get() = field
        set(value) {
            if (CheckEmail(value)) field = value
            else
                throw IllegalArgumentException("Email adress is invalid")
                println("email")
        }

    override var git = git
        get() = field
        set(value) {
            if (CheckGit(value)) field = value
            //else
                //throw IllegalArgumentException("Git link is invalid")
        }


    constructor(
        ID: Int,
        surname: String,
        name: String,
        secondname: String,
        phone: String
    ) : this(ID, name, surname, secondname) {
        this.phone = phone }

    constructor(
        ID: Int,
        surname: String,
        name: String,
        secondname: String,
        email: String,
        git: String
    ) : this(ID, name, surname, secondname) {
        this.email = email
        this.git = git }

    constructor(hashMap: Map<String, Any>) : this(
        hashMap["ID"] as Int,
        hashMap["surname"] as String,
        hashMap["name"] as String,
        hashMap["secondname"] as String,
        hashMap["phone"] as? String,
        hashMap["telegram"] as? String,
        hashMap["email"] as? String,
        hashMap["git"] as? String,
    )
    init {
        this.surname = surname
        this.name = name
        this.secondname = secondname
        this.phone = phone
        this.telegram = telegram
        this.email = email
        this.git = git
    }
    constructor(row: String) : this(row.split(',').also {
        if (it.size != 8 || it.any { "\n" in it })
            throw IllegalArgumentException("The format is invalid")
    })

    private constructor(row: List<String>) : this(
        row[0].toIntOrNull().let { it ?: throw IllegalArgumentException("Invalid id. Supposed to be natural") },
        row[1],
        row[2],
        row[3],
        row[4].ifEmpty { null },
        row[5].ifEmpty { null },
        row[6].ifEmpty { null },
        row[7].ifEmpty { null }
    )
    override fun toString(): String {
        var str = "[ID $id] $surname $name $secondname"
        if (phone != null) str += "\nНомер телефона: $phone"
        if (telegram != null) str += "\nTelegram: $telegram"
        if (email != null) str += "\nEmail: $email"
        if (git != null) str += "\nGit: $git"
        return "$str\n"
    }
    private fun toStringRow() = listOf(
        id.toString(), surname, name, secondname, phone ?: "", telegram ?: "", email ?: "", git ?: "").joinToString(",")
    fun anyGit(): Boolean {
        val result = git != null
        println("У студента $surname $name $secondname гит ${if (result) "при" else "от"}сутствует!")
        return result
    }

    fun anyContact(): Boolean {
        val result = phone != null || telegram != null || email != null
        println("Студент $surname $name $secondname , контакты:  ${if (result) "ЕСТЬ" else "НЕТ"}")
        return result
    }
    fun setContacts(hashMap: Map<String, String?>) {
        if (hashMap.containsKey("phone"))
            phone = hashMap["phone"]
        if (hashMap.containsKey("telegram"))
            telegram = hashMap["telegram"]
        if (hashMap.containsKey("email"))
            email = hashMap["email"]
    }

    fun getInfoSt() : String {
        val contactText = mapOf(
            "phone" to "номер телефона",
            "telegram" to "Telegram",
            "email" to "электронная почта"
        )
        val git = "git${if (this.git != null) ": ${this.git}" else " не указан"}"
        val contact = getContact().let {
            if (it != null) "${contactText[it.first]}: ${it.second}" else "контакты не указаны"
        }
        return "Студент ${getInitials()}, $git, $contact"
    }
    private fun getInitials() = "$name ${surname[0]}.${if (secondname.isNotEmpty()) " ${secondname[0]}." else ""}"
    private fun getContact() =
        if      (phone != null) Pair("phone", phone)
        else if (telegram != null) Pair("telegram", telegram)
        else if (email != null) Pair("email", email)
        else null


}
