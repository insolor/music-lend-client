package MusicLendClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
    static Stage stage;

    @FXML
    private TextField txtUserName, txtPassword, txtWebserviceURL;

    @FXML
    private void login(ActionEvent event) throws Exception {
        Main.connection = new DummyConnection(txtWebserviceURL.getText(), txtUserName.getText(), txtPassword.getText());

        openMainWindow();

        stage.close();
    }

    private void openMainWindow() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
        Stage mainWindow = new Stage();
        mainWindow.setTitle("Аренда музыкальных инструментов");
        Scene scene = new Scene(root);
        mainWindow.setScene(scene);
        // mainWindow.setMaximized(Boolean.TRUE);
        mainWindow.show();
    }

    @FXML
    private void cancel(ActionEvent event) {
        stage.close();
    }
}
