package org.example
import filter.SearchChoice
import filter.SearchParam
class StudentListDB : StudentListInterface{

   override fun getStudentById(id: Int) : Student? {
        val resultSet = Database.executeQuery("SELECT * FROM Student WHERE \"id\" = $id")
        return if (resultSet.next()) Student(mapOf(
            "ID" to resultSet.getInt("ID"),
            "surname" to resultSet.getString("surname"),
            "name" to resultSet.getString("name"),
            "secondname" to resultSet.getString("secondname"),
            "phone" to resultSet.getString("phone"),
            "telegram" to resultSet.getString("telegram"),
            "email" to resultSet.getString("email"),
            "git" to resultSet.getString("git")
        )) else null
    }
    override fun getStudentShortListFiltered(k: Int, n: Int, searchParam: SearchParam?): DataListStudentShort {
        if (searchParam == null) return getStudentShortList(k, n)
        if (k < 1) throw IllegalArgumentException("Значение k должно быть больше или равно 1")
        if (n < 0) throw IllegalArgumentException("Значение n не должно быть отрицательным")
        val firstElem = (k - 1) * n
        var query = buildList {
            if (searchParam.surnameFilter.isNotEmpty()) add("surname LIKE '%${searchParam.surnameFilter}%'")
            if (searchParam.nameFilter.isNotEmpty()) add("name LIKE '%${searchParam.nameFilter}%'")
            if (searchParam.patronymFilter.isNotEmpty()) add("patronym LIKE '%${searchParam.patronymFilter}%'")
            if (searchParam.gitChoice == SearchChoice.NO) add("git IS NULL")
            if (searchParam.gitChoice == SearchChoice.YES) add("git LIKE '%${searchParam.gitFilter}%'")
            if (searchParam.emailChoice == SearchChoice.NO) add("email IS NULL")
            if (searchParam.emailChoice == SearchChoice.YES) add("email LIKE '%${searchParam.emailFilter}%'")
            if (searchParam.phoneChoice == SearchChoice.NO) add("phone IS NULL")
            if (searchParam.phoneChoice == SearchChoice.YES) add("phone LIKE '%${searchParam.phoneFilter}%'")
            if (searchParam.telegramChoice == SearchChoice.NO) add("telegram IS NULL")
            if (searchParam.telegramChoice == SearchChoice.YES) add("telegram LIKE '%${searchParam.telegramFilter}%'")
        }.joinToString(" AND ")
        if (query.isNotEmpty()) query = "WHERE $query"
        query = "SELECT * FROM Student $query LIMIT $firstElem, $n"
        val resultSet = Database.executeQuery(query)
        val studentsSlice = buildList {
            while (resultSet.next()) {
                add(StudentShort(Student(mapOf(
                    "id" to resultSet.getInt("id"),
                    "surname" to resultSet.getString("surname"),
                    "name" to resultSet.getString("name"),
                    "secondname" to resultSet.getString("secondname"),
                    "phone" to resultSet.getString("phone"),
                    "telegram" to resultSet.getString("telegram"),
                    "email" to resultSet.getString("email"),
                    "git" to resultSet.getString("git")
                ))))
            }
        }
        return DataListStudentShort(studentsSlice)
    }
   override fun getStudentShortList(k: Int, n: Int) : DataListStudentShort {
        if (k < 1) throw IllegalArgumentException("Значение k должно быть больше или равно 1")
        if (n < 0) throw IllegalArgumentException("Значение n не должно быть отрицательным")
        val firstElem = (k - 1) * n
        val resultSet = Database.executeQuery("SELECT * FROM Student LIMIT $firstElem, $n")
        val studentsSlice = buildList {
            while (resultSet.next()) {
                add(StudentShort(Student(mapOf(
                    "ID" to resultSet.getInt("ID"),
                    "surname" to resultSet.getString("surname"),
                    "name" to resultSet.getString("name"),
                    "secondname" to resultSet.getString("secondname"),
                    "phone" to resultSet.getString("phone"),
                    "telegram" to resultSet.getString("telegram"),
                    "email" to resultSet.getString("email"),
                    "git" to resultSet.getString("git")
                ))))
            }
        }
        return DataListStudentShort(studentsSlice)
    }
    override fun add(student: Student) : Boolean {
        val sql = "INSERT INTO Student" +
                "(\"surname\", \"name\", \"secondname\", \"phone\", \"telegram\", \"email\", \"git\")" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        return Database.executeUpdate(sql) {
            it.setString(1, student.surname)
            it.setString(2, student.name)
            it.setString(3, student.secondname)
            it.setString(4, student.phone)
            it.setString(5, student.telegram)
            it.setString(6, student.email)
            it.setString(7, student.git)
        } >0
    }
    override fun replace(id: Int, newStudent: Student) : Boolean {
        val sql = "UPDATE Student SET \"surname\" = ?, \"name\" = ?, \"secondname\" = ?," +
                "\"phone\" = ?, \"telegram\" = ?, \"email\" = ?, \"git\" = ?" +
                "WHERE id = ?"
        return Database.executeUpdate(sql) {
            it.setString(1, newStudent.surname)
            it.setString(2, newStudent.name)
            it.setString(3, newStudent.secondname)
            it.setString(4, newStudent.phone)
            it.setString(5, newStudent.telegram)
            it.setString(6, newStudent.email)
            it.setString(7, newStudent.git)
            it.setInt(8, id)
        }>0
    }
    override fun remove(id: Int) : Boolean {
        val sql = "DELETE FROM Student WHERE \"id\" = ?"
        return Database.executeUpdate(sql) { it.setInt(1, id) } > 0
    }
    override fun getStudentShortCount() : Int {
        val resultSet = Database.executeQuery("SELECT count(*) AS cnt FROM Student")
        resultSet.next()
        return resultSet.getInt(1)
    }
}