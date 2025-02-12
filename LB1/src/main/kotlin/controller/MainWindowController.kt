package controller

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.stage.Modality
import javafx.stage.Stage
import model.TableRow
import model.ModelInterface
import model.StudentShortModel
import org.example.StudentList
import view.MainView
import view.ViewInterface
import java.util.logging.*

class MainWindowController : ControllerInterface {
    private val logger = Logger.getLogger(MainWindowController::class.java.name)

    init {
        logger.level = Level.ALL  // Level.ALL или Level.SEVERE
    }

    private val view: ViewInterface = MainView(this)
    private val model: ModelInterface = StudentShortModel(listOf(view))
    private var page: Int = 1
    private var pageSize: Int = 15

    @FXML
    private fun refreshData() {
        logger.info("Refreshing data for page $page with page size $pageSize...")
        try {
            // Получаем значения фильтров
            val surname = surnameField.text.trim()
            val name = nameField.text.trim()
            val patronym = patronymField.text.trim()

            val git = when (gitToggleGroup.selectedToggle) {
                gitYes -> true
                gitNo -> false
                else -> null // Не важно
            }

            val email = when (emailToggleGroup.selectedToggle) {
                emailYes -> true
                emailNo -> false
                else -> null // Не важно
            }

            val phone = when (phoneToggleGroup.selectedToggle) {
                phoneYes -> true
                phoneNo -> false
                else -> null // Не важно
            }

            val telegram = when (telegramToggleGroup.selectedToggle) {
                telegramYes -> true
                telegramNo -> false
                else -> null // Не важно
            }
            // Передаем полученные значения в модель для фильтрации
            model.refreshData(page, pageSize, surname, name, patronym, git, email, phone, telegram)

        } catch (ex: Exception) {
            logger.severe("Error refreshing data: $ex")
            val errorAlert = Alert(Alert.AlertType.ERROR)
            errorAlert.title = "Ошибка подключения к базе данных"
            errorAlert.headerText = "При подключении к базе данных возникла ошибка:"
            errorAlert.contentText = ex.localizedMessage
            errorAlert.showAndWait()
        }
    }


    private var selectedIds = listOf<Int>()

    @FXML
    private lateinit var surnameField: TextField
    @FXML
    private lateinit var nameField: TextField
    @FXML
    private lateinit var patronymField: TextField
    private val gitToggleGroup = ToggleGroup()
    @FXML
    private lateinit var gitYes: RadioButton
    @FXML
    private lateinit var gitNo: RadioButton
    @FXML
    private lateinit var gitNotImportant: RadioButton
    @FXML
    private lateinit var gitField: TextField
    private val emailToggleGroup = ToggleGroup()
    @FXML
    private lateinit var emailYes: RadioButton
    @FXML
    private lateinit var emailNo: RadioButton
    @FXML
    private lateinit var emailNotImportant: RadioButton
    @FXML
    private lateinit var emailField: TextField
    private val phoneToggleGroup = ToggleGroup()
    @FXML
    private lateinit var phoneYes: RadioButton
    @FXML
    private lateinit var phoneNo: RadioButton
    @FXML
    private lateinit var phoneNotImportant: RadioButton
    @FXML
    private lateinit var phoneField: TextField
    private val telegramToggleGroup = ToggleGroup()
    @FXML
    private lateinit var telegramYes: RadioButton
    @FXML
    private lateinit var telegramNo: RadioButton
    @FXML
    private lateinit var telegramNotImportant: RadioButton
    @FXML
    private lateinit var telegramField: TextField
    @FXML
    lateinit var addBtn: Button
    @FXML
    lateinit var editBtn: Button
    @FXML
    lateinit var delBtn: Button
    @FXML
    lateinit var studentsTable: TableView<TableRow>
    @FXML
    lateinit var rowsPerPageField: TextField
    @FXML
    lateinit var pageNumberLabel: Label
    @FXML
    lateinit var btnPrevPage: Button
    @FXML
    lateinit var btnNextPage: Button

    @FXML
    private fun initialize() {
        logger.info("Initializing main window controller...")
        listOf(gitYes, gitNo, gitNotImportant).forEach { it.toggleGroup = gitToggleGroup }
        listOf(emailYes, emailNo, emailNotImportant).forEach { it.toggleGroup = emailToggleGroup }
        listOf(phoneYes, phoneNo, phoneNotImportant).forEach { it.toggleGroup = phoneToggleGroup }
        listOf(telegramYes, telegramNo, telegramNotImportant).forEach { it.toggleGroup = telegramToggleGroup }
        resetFilters()

        // Обработчики событий для текстовых полей и радиокнопок
        surnameField.textProperty().addListener { _, _, _ -> refreshData() }
        nameField.textProperty().addListener { _, _, _ -> refreshData() }
        patronymField.textProperty().addListener { _, _, _ -> refreshData() }
        gitToggleGroup.selectedToggleProperty().addListener { _, _, _ -> refreshData() }
        emailToggleGroup.selectedToggleProperty().addListener { _, _, _ -> refreshData() }
        phoneToggleGroup.selectedToggleProperty().addListener { _, _, _ -> refreshData() }
        telegramToggleGroup.selectedToggleProperty().addListener { _, _, _ -> refreshData() }

        // Остальной код инициализации
        studentsTable.selectionModel.selectionMode = SelectionMode.MULTIPLE
        rowsPerPageField.text = "15"
        rowsPerPageField.setOnKeyTyped {
            if (!rowsPerPageField.text.matches(Regex("\\d*"))) {
                rowsPerPageField.text = (rowsPerPageField.text.replace(Regex("\\D"), ""))
            }
            page = 1
            pageSize = rowsPerPageField.text.ifEmpty { "0" }.toInt()
            refreshData()
        }
        studentsTable.selectionModel.selectedItemProperty().addListener { _, _, _ ->
            delBtn.isDisable = studentsTable.selectionModel.selectedIndices.size == 0
            val ids = model.getIdsOfCurrentPageRows()
            val selectedIndices = studentsTable.selectionModel.selectedIndices
            selectedIds = buildList {
                for (i in selectedIndices) {
                    add(ids[i])
                }
            }
            editBtn.isDisable = selectedIds.size != 1
        }
        logger.info("Main window controller initialized")
        refreshData()
    }

    @FXML
    private fun resetFilters() {
        surnameField.clear()
        nameField.clear()
        patronymField.clear()
        gitToggleGroup.selectToggle(gitNotImportant)
        emailToggleGroup.selectToggle(emailNotImportant)
        phoneToggleGroup.selectToggle(phoneNotImportant)
        telegramToggleGroup.selectToggle(telegramNotImportant)
        gitField.clear()
        emailField.clear()
        phoneField.clear()
        telegramField.clear()
        gitField.isDisable = true
        emailField.isDisable = true
        phoneField.isDisable = true
        telegramField.isDisable = true
        logger.info("Filters have been reset")
    }

    private fun showForm(fillValues: (FormController) -> Unit, submitAction: (FormController) -> Unit, formTitle: String, errTitle: String, errHeader: String) {
        val loader = FXMLLoader(javaClass.getResource("/view/FormWindow.fxml"))
        val formWindow: Parent = loader.load()
        val formController = loader.getController<FormController>()
        val formWindowScene = Scene(formWindow)
        val formWindowStage = Stage()
        formWindowStage.scene = formWindowScene
        formWindowStage.initModality(Modality.APPLICATION_MODAL)
        formWindowStage.title = formTitle
        formWindowStage.width = 300.0
        formWindowStage.height = 400.0
        fillValues(formController)
        formController.labelTop.text = formTitle
        formWindowStage.show()
        formController.submitButton.setOnAction {
            try {
                submitAction(formController)
                formWindowStage.close()
                logger.info("Form submitted")
            } catch (ex: IllegalArgumentException) {
                logger.severe("Error submitting form: $ex")
                val errorAlert = Alert(Alert.AlertType.ERROR)
                errorAlert.title = errTitle
                errorAlert.headerText = errHeader
                errorAlert.contentText = ex.localizedMessage
                errorAlert.showAndWait()
            }
        }
        formController.cancelButton.setOnAction {
            formWindowStage.close()
        }
    }

    @FXML
    private fun addStudentButton() {
        logger.info("Showing dialog for adding a new student")
        showForm({}, { formController -> model.addStudent(
            formController.surnameField.text,
            formController.nameField.text,
            formController.patronymField.text,
            formController.gitField.text.ifEmpty { null },
            formController.emailField.text.ifEmpty { null },
            formController.phoneField.text.ifEmpty { null },
            formController.telegramField.text.ifEmpty { null }
        ) }, "Добавить студента", "Ошибка добавления", "При добавлении студента возникла ошибка:")
    }

    @FXML
    private fun editStudentButton() {
        logger.info("Showing dialog for editing student with ID ${selectedIds[0]}")
        showForm({
            val studentForEdit = model.getStudentById(selectedIds[0])
            it.surnameField.text = studentForEdit?.surname ?: ""
            it.nameField.text = studentForEdit?.name ?: ""
            it.patronymField.text = studentForEdit?.secondname ?: ""
            it.gitField.text = studentForEdit?.git ?: ""
            it.emailField.text = studentForEdit?.email ?: ""
            it.phoneField.text = studentForEdit?.phone ?: ""
            it.telegramField.text = studentForEdit?.telegram ?: ""
            it.submitButton.text = "Сохранить"
        }, { formController -> model.editStudent(
            selectedIds[0],
            formController.surnameField.text,
            formController.nameField.text,
            formController.patronymField.text,
            formController.gitField.text.ifEmpty { null },
            formController.emailField.text.ifEmpty { null },
            formController.phoneField.text.ifEmpty { null },
            formController.telegramField.text.ifEmpty { null }
        ) }, "Изменить данные о студенте", "Ошибка редактирования", "При изменении данных возникла ошибка:")
    }

    @FXML
    private fun deleteStudentButton() {
        logger.info("Deleting students with IDs $selectedIds")
        selectedIds.forEach { model.delStudent(it) }
    }

    @FXML
    private fun updateButton() {
        refreshData()
    }

    @FXML
    private fun prevPage() {
        logger.info("Navigating to page $page")
        page--
        refreshData()
    }

    @FXML
    private fun nextPage() {
        logger.info("Navigating to page $page")
        page++
        refreshData()
    }
}

