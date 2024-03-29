package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;


public class LoginController {
    static Stage stage;

    @FXML
    ComboBox<String> comboConnectionType;

    @FXML
    private TextField txtUserName, txtPassword, txtWebserviceURL;

    @FXML
    private void login() throws IOException {
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
            Main.showError("Неправильное имя пользователя или пароль", "");
            return;
        }
        catch (Connection.UnexpectedResultException | IOException ex) {
            Main.showError("Не удалось подключиться к серверу", "");
            return;
        }

        openMainWindow();
        stage.close();
    }

    private void openMainWindow() throws IOException {
        Stage mainWindow = new Stage();
        mainWindow.setTitle("Аренда музыкальных инструментов");
        MainWindowController.stage = mainWindow;

        Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
        Scene scene = new Scene(root);
        mainWindow.setScene(scene);
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
        comboConnectionType.setValue("REST API");
    }
}
