package org.example
class StudentList(val dataSource: StudentListInterface) {
    fun getStudentById(id: Int) = dataSource.getStudentById(id)
    fun getStudentShortList(k: Int, n: Int) = dataSource.getStudentShortList(k, n)
    fun add(student: Student) = dataSource.add(student)
    fun replace(id: Int, newStudent: Student) = dataSource.replace(id, newStudent)
    fun remove(id: Int) = dataSource.remove(id)
    fun getStudentShortCount() = dataSource.getStudentShortCount()
}