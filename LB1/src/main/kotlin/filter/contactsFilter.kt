package filter

import org.example.Student

class contactsFilter(newFilter: FilterInterface) : FilterDecorator(newFilter) {
    override fun filter(students: List<Student>, searchParam: SearchParam): List<Student> {
        val filteredStudents = ArrayList<Student>()
        students.forEach {
            if (searchParam.emailChoice == SearchChoice.ANY ||
                searchParam.emailChoice == SearchChoice.NO && it.email == null ||
                searchParam.emailChoice == SearchChoice.YES && it.email != null && it.email!!.contains(searchParam.emailFilter, ignoreCase = true) &&
                searchParam.phoneChoice == SearchChoice.ANY ||
                searchParam.phoneChoice == SearchChoice.NO && it.phone == null ||
                searchParam.phoneChoice == SearchChoice.YES && it.phone != null && it.phone!!.contains(searchParam.phoneFilter, ignoreCase = true) &&
                searchParam.telegramChoice == SearchChoice.ANY ||
                searchParam.telegramChoice == SearchChoice.NO && it.telegram == null ||
                searchParam.telegramChoice == SearchChoice.YES && it.telegram != null && it.telegram!!.contains(searchParam.telegramFilter, ignoreCase = true))
            {
                filteredStudents.add(it)
            }
        }
        return tempFilter.filter(filteredStudents, searchParam)
    }
}