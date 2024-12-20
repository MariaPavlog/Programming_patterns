import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
object Database {
    private var conn: Connection? = null
    fun connect() {
        if (isConnected()) throw Exception("База данных уже подключена")
        conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASS)
    }
    fun disconnect() {
        conn?.close()
        conn = null
    }
    fun isConnected() : Boolean = conn?.isClosed == false
    private fun checkConnection() {
        if (!isConnected()) throw Exception("Подключение к БД не установлено")
    }
    fun executeQuery(sql: String): ResultSet {
        checkConnection()
        val stmt = conn!!.createStatement()
        return stmt.executeQuery(sql)
    }
    fun executeQuery(sql: String, statementFunction: (PreparedStatement) -> Unit): ResultSet {
        checkConnection()
        val stmt = conn!!.prepareStatement(sql)
        statementFunction(stmt)
        return stmt.executeQuery()
    }
    fun executeUpdate(sql: String): Int {
        checkConnection()
        val stmt = conn!!.createStatement()
        return stmt.executeUpdate(sql)
    }
    fun executeUpdate(sql: String, statementFunction: (PreparedStatement) -> Unit): Int {
        checkConnection()
        val stmt = conn!!.prepareStatement(sql)
        statementFunction(stmt)
        return stmt.executeUpdate()
    }
}