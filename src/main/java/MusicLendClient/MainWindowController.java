package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
            Main.showError("Ошибка при запросе данных", ex.getMessage());
            stage.close();  // FIXME: not working
            return;
        }

        if(Main.localUser.isAdmin()) {
            tabPane.getTabs().add(tabAdmin);
        }

        tabOrderInstruments.setContent(FXMLLoader.load(getClass().getResource("/OrderInstruments.fxml")));
        tabInstrumentsInUse.setContent(FXMLLoader.load(getClass().getResource("/InstrumentsInUse.fxml")));
    }
}
