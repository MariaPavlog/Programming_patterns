package controller
import javafx.scene.control.Alert
import model.ModelInterface
import model.StudentShortModel
import view.MainView
import view.ViewInterface
class StudentListController(mainWindowController: MainWindowController) : ControllerInterface {
    private val view: ViewInterface = MainView(mainWindowController)
    val model: ModelInterface = StudentShortModel(listOf(view))
    var page: Int = 1
    var pageSize: Int = 15
    init {
        refreshData()
    }
    fun refreshData() {
        try {
            model.refreshData(page, pageSize)
        }
        catch (ex: Exception) {
            val errorAlert = Alert(Alert.AlertType.ERROR)
            errorAlert.title = "Ошибка подключения к базе данных"
            errorAlert.headerText = "При подключении к базе данных возникла ошибка:"
            errorAlert.contentText = ex.localizedMessage
            errorAlert.showAndWait()
        }
    }
}