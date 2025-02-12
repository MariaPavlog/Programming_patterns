package filter

import org.example.Student

class baseFilter : FilterInterface {
    override fun filter(students: List<Student>, searchParam: SearchParam): List<Student> {
        return students
    }
}