package view
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
class MainView : Application() {
    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("/view/MainView.fxml"))
        val root: Parent = loader.load()
        val scene = Scene(root)
        scene.stylesheets.add(javaClass.getResource("/view/style.css")!!.toString())
        primaryStage.scene = scene
        primaryStage.title = "StudentsApp"
        primaryStage.width = 975.0
        primaryStage.height = 650.0
        primaryStage.show()
    }
}

