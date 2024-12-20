package view
import org.example.DataTable
interface ViewInterface {
    fun setTableParams(columnNames: List<String>, wholeEntitiesCount: Int, pageNumber: Int, pageSize: Int)
    fun setTableData(dataTable: DataTable)
}