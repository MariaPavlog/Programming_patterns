
package view
import org.example.DataTable
import model.TableRow
import controller.MainWindowController
import javafx.collections.FXCollections
import javafx.scene.control.TableColumn
import javafx.scene.control.cell.PropertyValueFactory
import kotlin.math.ceil
class MainView(private val controller: MainWindowController) : ViewInterface {
    override fun setTableParams(columnNames: List<String>, wholeEntitiesCount: Int, pageNumber: Int, pageSize: Int) {
        val table = controller.studentsTable
        val properties = listOf("id", "fullName", "git", "contact")
        val columns = buildList {
            for (i in columnNames.indices) {
                val tableColumn = TableColumn<TableRow, String>(columnNames[i])
                tableColumn.cellValueFactory = PropertyValueFactory(properties[i])
                add(tableColumn)
            }
        }
        table.columns.removeAll { true }
        table.columns.addAll(columns)
        val maxPage = ceil(wholeEntitiesCount.toDouble() / pageSize).toInt()
        controller.pageNumberLabel.text = "Страница $pageNumber из $maxPage"
        controller.btnPrevPage.isDisable = pageNumber == 1
        controller.btnNextPage.isDisable = pageNumber == maxPage
    }
    override fun setTableData(dataTable: DataTable) {
        val table = controller.studentsTable
        val rows = FXCollections.observableArrayList<TableRow>()
        for (row in 0..<dataTable.getRowCount()) {
            val id = dataTable[row, 0] as Int
            val fullName = dataTable[row, 1] as String?
            val git = dataTable[row, 2] as String?
            val contact = dataTable[row, 3] as String?
            rows.add(TableRow(id, fullName, git, contact))
        }
        table.items.removeAll { true }
        table.items.addAll(rows)
    }
}