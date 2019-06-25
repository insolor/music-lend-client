package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private Button btnLogin, btnCancel;

    @FXML
    private void login(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Stage mainWindow = new Stage();
        mainWindow.setTitle("Аренда музыкальных инструментов");
        Scene scene = new Scene(root);
        mainWindow.setScene(scene);
        mainWindow.show();

        Stage stage = (Stage)btnLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }
}
