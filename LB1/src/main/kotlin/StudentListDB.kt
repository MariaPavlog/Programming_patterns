package org.example
import java.sql.DriverManager

class StudentListDB(databaseURL: String) {
    val conn = DriverManager.getConnection(databaseURL)
    val stmt = conn.createStatement()
    fun getStudentById(id: Int) : Student? {
        val resultSet = stmt.executeQuery("SELECT * FROM Student WHERE \"id\" = $id")
        return if (resultSet.next()) Student(mapOf(
            "id" to resultSet.getInt("id"),
            "surname" to resultSet.getString("surname"),
            "name" to resultSet.getString("name"),
            "patronym" to resultSet.getString("secondname"),
            "phone" to resultSet.getString("phone"),
            "telegram" to resultSet.getString("telegram"),
            "email" to resultSet.getString("email"),
            "git" to resultSet.getString("git")
        )) else null
    }
    fun getStudentShortList(k: Int, n: Int) : DataListStudentShort {
        if (k < 1) throw IllegalArgumentException("Значение k должно быть больше или равно 1")
        if (n < 0) throw IllegalArgumentException("Значение n не должно быть отрицательным")
        val firstElem = (k - 1) * n
        val resultSet = stmt.executeQuery("SELECT * FROM Student LIMIT $firstElem, $n")
        val studentsSlice = buildList {
            while (resultSet.next()) {
                add(StudentShort(Student(mapOf(
                    "id" to resultSet.getInt("id"),
                    "surname" to resultSet.getString("surname"),
                    "name" to resultSet.getString("name"),
                    "patronym" to resultSet.getString("secondname"),
                    "phone" to resultSet.getString("phone"),
                    "telegram" to resultSet.getString("telegram"),
                    "email" to resultSet.getString("email"),
                    "git" to resultSet.getString("git")
                ))))
            }
        }
        return DataListStudentShort(studentsSlice)
    }
    fun add(student: Student) {
        val sql = "INSERT INTO Student" +
                "(\"surname\", \"name\", \"secondname\", \"phone\", \"telegram\", \"email\", \"git\")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        val preparedStatement = conn.prepareStatement(sql)
        preparedStatement.setString(1, student.surname)
        preparedStatement.setString(2, student.name)
        preparedStatement.setString(3, student.secondname)
        preparedStatement.setString(4, student.phone)
        preparedStatement.setString(5, student.telegram)
        preparedStatement.setString(6, student.email)
        preparedStatement.setString(7, student.git)
        preparedStatement.executeUpdate()
    }
    fun replace(id: Int, newStudent: Student) {
        val sql = "UPDATE Student SET \"surname\" = ?, \"name\" = ?, \"secondname\" = ?," +
                "\"phone\" = ?, \"telegram\" = ?, \"email\" = ?, \"git\" = ?" +
                "WHERE id = ?"
        val preparedStatement = conn.prepareStatement(sql)
        preparedStatement.setString(1, newStudent.surname)
        preparedStatement.setString(2, newStudent.name)
        preparedStatement.setString(3, newStudent.secondname)
        preparedStatement.setString(4, newStudent.phone)
        preparedStatement.setString(5, newStudent.telegram)
        preparedStatement.setString(6, newStudent.email)
        preparedStatement.setString(7, newStudent.git)
        preparedStatement.setInt(8, id)
        preparedStatement.executeUpdate()
    }
    fun remove(id: Int) : Boolean {
        val sql = "DELETE FROM Student WHERE \"id\" = ?"
        val preparedStatement = conn.prepareStatement(sql)
        preparedStatement.setInt(1, id)
        return preparedStatement.executeUpdate() > 0
    }
    fun getStudentShortCount() : Int {
        val resultSet = stmt.executeQuery("SELECT count(*) AS cnt FROM Student")
        resultSet.next()
        return resultSet.getInt(1)
    }
}