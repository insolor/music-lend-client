package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    static Stage stage;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabOrderInstruments, tabInstrumentsInUse, tabAdmin;

    @FXML
    void initialize() throws IOException {
        tabPane.getTabs().remove(tabAdmin);

        try {
            Main.localUser = LocalUser.fromUser(Main.connection.getUser());
        }
        catch (Connection.UnexpectedResultException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Ошибка");
            errorAlert.setContentText("Ошибка при запросе данных:\n".concat(ex.getMessage()));
            errorAlert.showAndWait();
            stage.close();
        }

        if(Main.localUser.isAdmin()) {
            tabPane.getTabs().add(tabAdmin);
        }

        tabOrderInstruments.setContent(FXMLLoader.load(getClass().getResource("/OrderInstruments.fxml")));
        tabInstrumentsInUse.setContent(FXMLLoader.load(getClass().getResource("/InstrumentsInUse.fxml")));
    }
}
