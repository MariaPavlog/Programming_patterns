package org.example
import filter.SearchParam
interface StudentListInterface {
    fun getStudentById(id: Int) : Student?
    fun getStudentShortList(k: Int, n: Int) : DataListStudentShort
    fun getStudentShortListFiltered(k: Int, n: Int, searchParam: SearchParam?) : DataListStudentShort
    fun add(student: Student) : Boolean
    fun replace(id: Int, newStudent: Student) : Boolean
    fun remove(id: Int) : Boolean
    fun getStudentShortCount() : Int
}