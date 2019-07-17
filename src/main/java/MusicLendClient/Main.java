package MusicLendClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
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
}
