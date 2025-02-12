import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class StudentListTest {
    private lateinit var studentList: StudentList

    @BeforeEach
    fun setUp() {
        val studentsJSON = StudentListFile(JSONFormatStrategy())
        studentList = StudentList(StudentListFileAdapter(studentsJSON))
        studentsJSON.load("lab3_output.json")
    }

    @Test
    fun getStudents() {
        var actual: Student? = studentList.getStudentById(1)
        var expected: Student? = Student(0, "Эзри", "Артём", "Александрович")
        assertEquals(expected, actual)

        actual = studentList.getStudentById(2)
        expected = Student(0, "Тестов", "Тест", "Тестович", "+79876543210", "@test123", "test@example.com", "https://github.com/test123")
        assertEquals(expected, actual)

        actual = studentList.getStudentById(0)
        assertNull(actual)

        actual = studentList.getStudentById(7)
        assertNull(actual)
    }

    @Test
    fun getList() {
        val dataListStudentShort = studentList.getStudentShortList(2, 4)

        val actualNames = dataListStudentShort.getNames()
        val expectedNames = listOf("№", "ФИО", "Git", "Контакт для связи")
        assertEquals(expectedNames, actualNames)

        val actualData = dataListStudentShort.getData()
        assertTrue(actualData.getRowCount() == 2)
        assertTrue(actualData.getColCount() == 4)
        assertEquals(1, actualData[0, 0])
        assertEquals("Изменённый С. Х.", actualData[0, 1])
        assertNull(actualData[0, 2])
        assertNull(actualData[0, 3])
        assertEquals(2, actualData[1, 0])
        assertEquals("Студент Н. В.", actualData[1, 1])
        assertNull(actualData[1, 2])
        assertNull(actualData[1, 3])

        val actualData2 = studentList.getStudentShortList(3, 1).getData()
        assertTrue(actualData2.getRowCount() == 1)
        assertEquals(1, actualData2[0, 0])
        assertEquals("Тестов Т. Т.", actualData2[0, 1])
        assertEquals("https://github.com/test123", actualData2[0, 2])
        assertEquals("+79876543210", actualData2[0, 3])

        assertTrue(studentList.getStudentShortList(9, 9).getData().getRowCount() == 0)
    }

    @Test
    fun addStudents() {
        assertThrows(IllegalArgumentException::class.java) {
            studentList.add(Student(0, "", "", ""))
        }

        assertThrows(IllegalArgumentException::class.java) {
            studentList.add(Student(0, "Новый", "Студ3нт", ""))
        }

        val st = Student(0, "Новый", "Студент", "")
        assertTrue(studentList.add(Student(0, "Новый", "Студент", "")))
        assertEquals(st, studentList.getStudentById(9))

        assertThrows(IllegalArgumentException::class.java) {
            studentList.add(Student(0, "Новый", "Студент", "", email = "abracadabra"))
        }
    }

    @Test
    fun removeStudents() {
        assertTrue(studentList.remove(3))
        assertFalse(studentList.remove(7))
        assertFalse(studentList.remove(-1))
        assertFalse(studentList.remove(0))
        assertFalse(studentList.remove(100))
        assertEquals(5, studentList.getStudentShortCount())
        assertNull(studentList.getStudentById(3))
        assertNull(studentList.getStudentById(7))
    }

    @Test
    fun editStudents() {
        val st = Student(0, "Изменённый", "Студент", "", telegram = "@skullemoji")
        assertTrue(studentList.replace(2, st))
        assertTrue(studentList.replace(8, st))
        assertEquals(st, studentList.getStudentById(2))
        assertEquals(studentList.getStudentById(2), studentList.getStudentById(8))

        assertFalse(studentList.replace(7, st))
        assertFalse(studentList.replace(-1, st))
        assertFalse(studentList.replace(0, st))
        assertFalse(studentList.replace(100, st))
    }
}