package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindowController {
    static String WebserviceURL, access_token;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabOrderInstruments, tabInstrumentsInUse, tabAdmin;

    @FXML
    void initialize() {
        // Hide the pane if no instruments in use
        if(Boolean.TRUE) {
            tabPane.getTabs().remove(tabInstrumentsInUse);
        }

        // Hide the pane if the user is not admin
        if(Boolean.TRUE) {
            tabPane.getTabs().remove(tabAdmin);
        }
    }
}
