package model


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class TableRowTest {

    private lateinit var tableRow: TableRow

    @BeforeEach
    fun setUp() {
        // Инициализация объекта TableRow перед каждым тестом
        tableRow = TableRow(1, "John Doe", "https://github.com/johndoe", "john.doe@example.com")
    }

    @Test
    fun testTableRowInitialization() {
        // Проверяем, что поля инициализируются правильно
        assertEquals(1, tableRow.id)
        assertEquals("John Doe", tableRow.fullName)
        assertEquals("https://github.com/johndoe", tableRow.git)
        assertEquals("john.doe@example.com", tableRow.contact)
    }

    @Test
    fun testTableRowWithNullValues() {
        // Проверяем инициализацию с null значениями
        val tableRowWithNulls = TableRow(2, null, null, null)

        // Проверяем, что поля инициализируются как null
        assertEquals(2, tableRowWithNulls.id)
        assertEquals(null, tableRowWithNulls.fullName)
        assertEquals(null, tableRowWithNulls.git)
        assertEquals(null, tableRowWithNulls.contact)
    }
}
