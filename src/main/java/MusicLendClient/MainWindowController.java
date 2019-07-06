package MusicLendClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class MainWindowController {
    static String WebserviceURL, access_token;

    private User user;


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabOrderInstruments, tabInstrumentsInUse, tabAdmin;

    @FXML
    void initialize() throws IOException {
        tabPane.getTabs().remove(tabInstrumentsInUse);
        tabPane.getTabs().remove(tabAdmin);

        user = new User();

        if(user.isAdmin()) {
            tabPane.getTabs().add(tabAdmin);
        }

        if(!user.getInstrumentsInUse().isEmpty()) {
            tabPane.getTabs().add(tabInstrumentsInUse);
        }

        Parent tabContents = FXMLLoader.load(getClass().getResource("/OrderInstruments.fxml"));
        tabOrderInstruments.setContent(tabContents);
    }
}
