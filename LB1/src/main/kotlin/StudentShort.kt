package org.example

class StudentShort : SudentAbst {
    companion object {
        private val surnameWithInitialsRegex = Regex("""^[\p{L}-]+ \p{L}\.( \p{L}\.)?$""")

        fun isValidSurnameWithInitials(value: String) = surnameWithInitialsRegex.matches(value)
            fun isValidContact(value: String?) = value == null || phoneRegex.matches(value) || telegramRegex.matches(value) || emailRegex.matches(value)
        }
    override val id: Int
    var surnameWithInitials: String
        get() = field
    override var git: String?
        get() = field
    var contact: String?
        get() = field
        constructor(id: Int, info: String) {
            this.id = id
            val (initials, git, contact) = info.split(", ")
            this.surnameWithInitials = initials.drop(8)
            if (!isValidSurnameWithInitials(this.surnameWithInitials))
                throw IllegalArgumentException("ФИО имеет недопустимое значение")
            this.git = if (":" in git) git.drop(5) else null
            if (!CheckGit(this.git))
                throw IllegalArgumentException("Ссылка на Git имеет недопустимое значение")
            this.contact = if (":" in contact) contact.substringAfterLast(": ") else null
            if (!isValidContact(this.contact))
                throw IllegalArgumentException("Контакт имеет недопустимое значение")
        }
        constructor(student: Student) : this(student.id, student.getInfoSt())
        override fun toString(): String {
            var str = "[ID $id] $surnameWithInitials"
            if (git != null) str += "\nGit: $git"
            if (contact != null) str += "\nКонтакт: $contact"
            return "$str\n"
        }

    }
