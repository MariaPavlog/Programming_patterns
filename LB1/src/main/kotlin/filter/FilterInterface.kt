package filter

import org.example.Student

interface FilterInterface {
    fun filter(students: List<Student>, searchParam: SearchParam): List<Student>
}
