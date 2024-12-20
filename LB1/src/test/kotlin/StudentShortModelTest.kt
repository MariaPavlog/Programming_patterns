package model

import org.example.DataListStudentShort
import org.example.Student
import org.example.StudentList
import org.example.StudentListDB
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import view.ViewInterface

class StudentShortModelTest {

    private lateinit var views: List<ViewInterface>
    private lateinit var studentShortModel: StudentShortModel
    private lateinit var mockView: ViewInterface
    private lateinit var mockStudentList: StudentList
    private lateinit var mockDataListStudentShort: DataListStudentShort

    @BeforeEach

    fun setUp() {
        mockView = mock(ViewInterface::class.java)
        views = listOf(mockView)

        // Замокируем StudentList
        mockStudentList = mock(StudentList::class.java)

        // Замокируем DataListStudentShort
        mockDataListStudentShort = mock(DataListStudentShort::class.java)

        // Инициализация модели с моками
        // Убедитесь, что StudentShortModel принимает mockStudentList в своем конструкторе
        studentShortModel = StudentShortModel(views) // если требуется
    }



    @Test
    fun `test addStudent method`() {
        // Подготовка
        val student = Student(0, "Doe", "John", "Smith", null, null, null, null)
        `when`(mockStudentList.add(student)).then { /* do nothing */ }
        `when`(mockStudentList.getStudentShortList(1, 15)).thenReturn(mockDataListStudentShort)

        // Вызов метода addStudent
        studentShortModel.addStudent("Doe", "John", "Smith", null, null, null, null)

        // Проверка, что refreshData был вызван
        verify(mockView).setTableParams(any(), any(), any(), any())
    }

    @Test
    fun `test editStudent method`() {
        val student = Student(0, "Doe", "John", "Smith", null, null, null, null)
        `when`(mockStudentList.replace(anyInt(), eq(student))).then { /* do nothing */ }
        `when`(mockStudentList.getStudentShortList(1, 15)).thenReturn(mockDataListStudentShort)

        studentShortModel.editStudent(1, "Doe", "John", "Smith", null, null, null, null)

        verify(mockView).setTableParams(any(), any(), any(), any())
    }

    @Test
    fun `test delStudent method`() {
        `when`(mockStudentList.remove(anyInt())).then { /* do nothing */ }
        `when`(mockStudentList.getStudentShortList(1, 15)).thenReturn(mockDataListStudentShort)

        studentShortModel.delStudent(1)

        verify(mockView).setTableParams(any(), any(), any(), any())
    }

    @Test
    fun `test getIdsOfCurrentPageRows method`() {
        `when`(mockStudentList.getStudentShortList(1, 15)).thenReturn(mockDataListStudentShort)

        studentShortModel.getIdsOfCurrentPageRows()

        verify(mockDataListStudentShort).getIds()
    }

    @Test
    fun `test getStudentById method`() {
        val student = Student(1, "Doe", "John", "Smith", null, null, null, null)
        `when`(mockStudentList.getStudentById(1)).thenReturn(student)

        val result = studentShortModel.getStudentById(1)

        assert(result == student)
    }
}
