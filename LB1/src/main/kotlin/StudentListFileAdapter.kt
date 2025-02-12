package org.example
import filter.SearchParam
class StudentListFileAdapter(private val studentListFileObject: StudentListFile) : StudentListInterface {
    override fun getStudentById(id: Int): Student? {
        return try { studentListFileObject.getStudentById(id) }
        catch (e: IllegalArgumentException) { null }
    }
    override fun getStudentShortList(k: Int, n: Int) : DataListStudentShort {
        return studentListFileObject.getStudentShortList(k, n)
    }
    override fun getStudentShortListFiltered(k: Int, n: Int, searchParam: SearchParam?) : DataListStudentShort {
        return studentListFileObject.getStudentShortListFiltered(k, n, searchParam)
    }
    override fun add(student: Student) : Boolean {
        studentListFileObject.add(student)
        return true
    }
    override fun replace(id: Int, newStudent: Student) : Boolean {
        try {
            studentListFileObject.replace(id, newStudent)
            return true
        }
        catch (e: IllegalArgumentException) {
            return false
        }
    }
    override fun remove(id: Int) : Boolean {
        try {
            studentListFileObject.remove(id)
            return true
        }
        catch (e: IllegalArgumentException) {
            return false
        }
    }
    override fun getStudentShortCount() : Int {
        return studentListFileObject.getStudentShortCount()
    }
}