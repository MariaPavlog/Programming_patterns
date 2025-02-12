package filter

import org.example.Student

class gitFilter(newFilter: FilterInterface) : FilterDecorator(newFilter) {
    override fun filter(students: List<Student>, searchParam: SearchParam): List<Student> {
        val filteredStudents = ArrayList<Student>()
        students.forEach {
            if (searchParam.gitChoice == SearchChoice.ANY ||
                searchParam.gitChoice == SearchChoice.NO && it.git == null ||
                searchParam.gitChoice == SearchChoice.YES && it.git != null && it.git!!.contains(searchParam.gitFilter, ignoreCase = true))
            {
                filteredStudents.add(it)
            }
        }
        return tempFilter.filter(filteredStudents, searchParam)
    }
}