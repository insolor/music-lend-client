package MusicLendClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        stage.setTitle("Окно входа");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        LoginController.stage = stage;
        stage.show();
    }

    static Connection connection;
    static LocalUser localUser;
    static Shop shop;

    static void showError(String header, String description) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Ошибка"); // window title
        errorAlert.setHeaderText(header); // short description
        errorAlert.setContentText(description); // full description
        errorAlert.showAndWait();
    }
}
