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
}