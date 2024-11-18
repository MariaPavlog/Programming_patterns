package org.example

class Student(
    val ID: Int,
    name: String,
    surname: String,
    secondname: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
) {
    companion object {
        private val phoneRegex = Regex("""^(\+\d{1,3}[- ]?)?\(?\d{3}\)?[- ]?\d{3}[- ]?\d{4}$""")

        fun checkPhone(value: String?): Boolean {
            return value != null && phoneRegex.matches(value)
        }
        private val nameRegex = Regex("""^[\p{L}-]+$""")
        fun CheckName(value: String) = nameRegex.matches(value)
        private val telegramRegex = Regex("""^@\w{5,32}$""")
        fun CheckTelegram(value: String?) = value == null || telegramRegex.matches(value)
        private val emailRegex = Regex("""^[A-Za-z0-9_+-]+(\.[A-Za-z0-9_+-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)+$""")
        fun CheckEmail(value: String?) = value == null || emailRegex.matches(value)
        private val gitRegex = Regex("""^(https?://)?([A-Za-z0-9]+\.)?[A-Za-z0-9]+\.[A-Za-z0-9]+/[A-Za-z0-9_-]+/?$""")
        fun CheckGit(value: String?) = value == null || gitRegex.matches(value)
        fun CheckSecondname(value: String) = value.isEmpty() || CheckName(value)
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


    var phone: String? = phone
        set(value) {
            if (checkPhone(value)) {
                field = value
            } else {
               //throw IllegalArgumentException("Number is incorrect")
                //println("номер некорректный")
            }
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
           // else
                //throw IllegalArgumentException("Email adress is invalid")
                //println("email")
        }

    var git = git
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

    override fun toString(): String {
        var str = "[ID $ID] $surname $name $secondname"
        if (phone != null) str += "\nНомер телефона: $phone"
        if (telegram != null) str += "\nTelegram: $telegram"
        if (email != null) str += "\nEmail: $email"
        if (git != null) str += "\nGit: $git"
        return "$str\n"
    }
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

    fun show() = println(this.toString())
}
