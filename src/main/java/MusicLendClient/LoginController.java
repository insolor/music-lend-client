package MusicLendClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class LoginController {
    static Stage stage;

    @FXML
    private TextField txtUserName, txtPassword, txtWebserviceURL;

    @FXML
    private void login(ActionEvent event) throws Exception {
        try {
            Main.connection = new DummyConnection(txtWebserviceURL.getText(), txtUserName.getText(), txtPassword.getText());
        }
        catch (BadUserException badUserException) {
            Alert alert = new Alert(AlertType.ERROR, "Неправильное имя пользователя или пароль", ButtonType.OK);
            alert.showAndWait();
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
    private void cancel(ActionEvent event) {
        stage.close();
    }
}
