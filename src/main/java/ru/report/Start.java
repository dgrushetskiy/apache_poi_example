package ru.report;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.report.controller.StartController;

import java.io.IOException;

public class Start extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Report");
        initRootLayout();
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getResource("/fxml/start.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            StartController controller = loader.getController();
            controller.init();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
