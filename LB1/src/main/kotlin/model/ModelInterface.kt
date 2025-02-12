package model
import org.example.DataListStudentShort
import org.example.Student
interface ModelInterface {
    fun notify(listStudentShort: DataListStudentShort)

    fun refreshData(page: Int, pageSize: Int, surname: String?, name: String?, patronym: String?, git: Boolean?, email: Boolean?, phone: Boolean?, telegram: Boolean?)


    fun addStudent(surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?)
    fun editStudent(idToEdit: Int, surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?)
    fun delStudent(id: Int)
    fun getIdsOfCurrentPageRows(): List<Int>
    fun getStudentById(id: Int): Student?
}