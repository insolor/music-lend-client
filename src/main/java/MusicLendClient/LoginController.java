package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.apache.http.conn.HttpHostConnectException;

import java.util.Arrays;


public class LoginController {
    static Stage stage;

    @FXML
    ComboBox<String> comboConnectionType;

    @FXML
    private TextField txtUserName, txtPassword, txtWebserviceURL;

    @FXML
    private void login() throws Exception {
        Alert errorAlert = new Alert(AlertType.ERROR);

        try {
            if(comboConnectionType.getValue().equals("REST API")) {
                Main.connection = new RESTConnection(txtWebserviceURL.getText(), txtUserName.getText(),
                        txtPassword.getText());
            }
            else {
                Main.connection = new DummyConnection(txtUserName.getText(), txtPassword.getText());
            }
        }
        catch (Connection.BadUserException badUserException) {
            errorAlert.setContentText("Неправильное имя пользователя или пароль");
            errorAlert.showAndWait();
            return;
        }
        catch (HttpHostConnectException httpException) {
            errorAlert.setContentText("Не удалось подключиться к серверу");
            errorAlert.showAndWait();
            return;
        }

        openMainWindow();
        stage.close();
    }

    private void openMainWindow() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
        Stage mainWindow = new Stage();
        mainWindow.setTitle("Аренда музыкальных инструментов");
        Scene scene = new Scene(root);
        mainWindow.setScene(scene);
        MainWindowController.stage = mainWindow;
        // mainWindow.setMaximized(Boolean.TRUE);
        mainWindow.show();
    }

    @FXML
    private void cancel() {
        stage.close();
    }

    @FXML
    void initialize() {
        comboConnectionType.getItems().addAll(Arrays.asList("Тестовое подключение", "REST API"));
        comboConnectionType.setValue("Тестовое подключение");
    }
}
