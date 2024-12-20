package org.example
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
class StudentsApp : Application() {
    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("/view/MainWindow.fxml"))
        val mainWindow: Parent = loader.load()
        val mainWindowScene = Scene(mainWindow)
        mainWindowScene.stylesheets.add(javaClass.getResource("/view/style.css")!!.toString())
        primaryStage.scene = mainWindowScene
        primaryStage.title = "StudentsApp"
        primaryStage.width = 975.0
        primaryStage.height = 650.0
        primaryStage.show()
    }
}