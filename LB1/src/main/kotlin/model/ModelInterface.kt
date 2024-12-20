package model
import org.example.DataListStudentShort
import org.example.Student
interface ModelInterface {
    fun notify(listStudentShort: DataListStudentShort)
    fun refreshData(k: Int, n: Int)
    fun addStudent(surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?)
    fun editStudent(idToEdit: Int, surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?)
    fun delStudent(id: Int)
    fun getIdsOfCurrentPageRows(): List<Int>
    fun getStudentById(id: Int): Student?
}