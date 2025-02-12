package filter

data class SearchParam(val surnameFilter: String,
                       val nameFilter: String,
                       val patronymFilter: String,
                       val gitChoice: SearchChoice,
                       val gitFilter: String,
                       val emailChoice: SearchChoice,
                       val emailFilter: String,
                       val phoneChoice: SearchChoice,
                       val phoneFilter: String,
                       val telegramChoice: SearchChoice,
                       val telegramFilter: String
)

enum class SearchChoice {
    YES,
    NO,
    ANY
}