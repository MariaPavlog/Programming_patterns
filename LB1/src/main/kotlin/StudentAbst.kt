package org.example

abstract class SudentAbst {
    companion object {
        @JvmStatic
        protected val nameRegex = Regex("""^[\p{L}-]+$""")
        @JvmStatic
        protected val phoneRegex = Regex("""^\+?[0-9]{10,15}$""")
        @JvmStatic
        protected val telegramRegex = Regex("""^@\w{5,32}$""")
        @JvmStatic
        protected val emailRegex = Regex("""^[A-Za-z0-9_+-]+(\.[A-Za-z0-9_+-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)+$""")
        @JvmStatic
        protected val gitRegex = Regex("""^(https?://)?([A-Za-z0-9]+\.)?[A-Za-z0-9]+\.[A-Za-z0-9]+/[A-Za-z0-9_-]+/?$""")
        @JvmStatic
        protected fun CheckName(value: String) = nameRegex.matches(value)
        @JvmStatic
        protected fun CheckSecondname(value: String) = value.isEmpty() || CheckName(value)
        @JvmStatic
        protected fun CheckPhone(value: String?) = value == null || phoneRegex.matches(value)
        @JvmStatic
        protected fun CheckTelegram(value: String?) = value == null || telegramRegex.matches(value)
        @JvmStatic
        protected fun CheckEmail(value: String?) = value == null || emailRegex.matches(value)
        @JvmStatic
        protected fun CheckGit(value: String?) = value == null || gitRegex.matches(value)
    }
    abstract val id: Int
    abstract var git: String?
    abstract override fun toString(): String
    fun show() = println(this.toString())
}