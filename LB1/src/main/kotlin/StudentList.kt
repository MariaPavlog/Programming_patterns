package org.example
import filter.SearchParam
open class StudentList(val dataSource: StudentListInterface) {
    fun getStudentById(id: Int) = dataSource.getStudentById(id)
    fun getStudentShortList(k: Int, n: Int) = dataSource.getStudentShortList(k, n)
    fun getStudentShortListFiltered(k: Int, n: Int, searchParam: SearchParam?) = dataSource.getStudentShortListFiltered(k, n, searchParam)
    fun add(student: Student) = dataSource.add(student)
    fun replace(id: Int, newStudent: Student) = dataSource.replace(id, newStudent)
    fun remove(id: Int) = dataSource.remove(id)
    fun getStudentShortCount() = dataSource.getStudentShortCount()
}