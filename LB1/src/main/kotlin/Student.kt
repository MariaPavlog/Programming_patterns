package org.example

class Student (
    val ID: Int,
    name: String,
    surname: String,
    secondname: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
)
{
    var name = name
        get() = field
        set(value) {
            field = value
        }
    var surname = surname
        get() = field
        set(value) {
            field = value
        }
    var secondname = secondname
        get() = field
        set(value) {
            field = value
        }
    var phone = phone
        get() = field
        set(value) {
            field = value
        }
    var telegram = telegram
        get() = field
        set(value) {
            field = value
        }
    var email = email
        get() = field
        set(value) {
            field = value
        }
    var git = git
        get() = field
        set(value) {
            field = value
        }

    constructor(ID: Int,
                surname: String,
                name: String,
                secondname: String,
                phone: String) : this(ID, surname, name, secondname) {
        this.phone = phone
    }

    constructor(ID: Int,
                surname: String,
                name: String,
                secondname: String,
                email: String,
                git: String) : this(ID, surname, name, secondname) {
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
    fun show() = println(this.toString())
}