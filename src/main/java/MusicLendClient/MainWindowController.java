package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindowController {
    String WebserviceURL, access_token;

    private User user;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabOrderInstruments, tabInstrumentsInUse, tabAdmin;

    @FXML
    void initialize() {
        tabPane.getTabs().remove(tabInstrumentsInUse);
        tabPane.getTabs().remove(tabAdmin);

        user = new User();

        if(user.isAdmin()) {
            tabPane.getTabs().add(tabAdmin);
        }

        if(!user.getInstrumentsInUse().isEmpty()) {
            tabPane.getTabs().add(tabInstrumentsInUse);
        }
    }
}
