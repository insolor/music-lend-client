package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    static Stage stage;

    private User user;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabOrderInstruments, tabInstrumentsInUse, tabAdmin;

    @FXML
    void initialize() throws IOException {
        tabPane.getTabs().remove(tabAdmin);

        user = Main.connection.getUser();

        if(user.isAdmin()) {
            tabPane.getTabs().add(tabAdmin);
        }

        tabOrderInstruments.setContent(FXMLLoader.load(getClass().getResource("/OrderInstruments.fxml")));
        tabInstrumentsInUse.setContent(FXMLLoader.load(getClass().getResource("/InstrumentsInUse.fxml")));
    }
}
