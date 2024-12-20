package controller
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
class FormController : ControllerInterface{
    @FXML
    lateinit var labelTop: Label
    @FXML
    lateinit var surnameField: TextField
    @FXML
    lateinit var nameField: TextField
    @FXML
    lateinit var patronymField: TextField
    @FXML
    lateinit var gitField: TextField
    @FXML
    lateinit var emailField: TextField
    @FXML
    lateinit var phoneField: TextField
    @FXML
    lateinit var telegramField: TextField
    @FXML
    lateinit var cancelButton: Button
    @FXML
    lateinit var submitButton: Button
}