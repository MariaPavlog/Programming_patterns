package model
import org.example.DataListStudentShort
import Database
import org.example.Student
import org.example.StudentList
import org.example.StudentListDB
import view.ViewInterface
class StudentShortModel(private val views: List<ViewInterface>) : ModelInterface {
    private val studentList: StudentList = StudentList(StudentListDB())
    private var page = 1
    private var pageSize = 15
    init {
        Database.connect()
    }
    override fun notify(listStudentShort: DataListStudentShort) {
        for (subscriber in views) {
            subscriber.setTableParams(listStudentShort.getNames(), studentList.getStudentShortCount(), page, pageSize)
            subscriber.setTableData(listStudentShort.getData())
        }
    }
    override fun refreshData(k: Int, n: Int) {
        page = k
        pageSize = n
        val listStudentShort = studentList.getStudentShortList(k, n)
        this.notify(listStudentShort)
    }
    override fun addStudent(surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?) {
        studentList.add(Student(0, surname, name, patronym, phone, telegram, email, git))
        this.refreshData(page, pageSize)
    }
    override fun editStudent(idToEdit: Int, surname: String, name: String, patronym: String, git: String?, email: String?, phone: String?, telegram: String?) {
        studentList.replace(idToEdit, Student(0, surname, name, patronym, phone, telegram, email, git))
        this.refreshData(page, pageSize)
    }
    override fun delStudent(id: Int) {
        studentList.remove(id)
        this.refreshData(page, pageSize)
    }
    override fun getIdsOfCurrentPageRows() = studentList.getStudentShortList(page, pageSize).getIds()
    override fun getStudentById(id: Int) = studentList.getStudentById(id)
}