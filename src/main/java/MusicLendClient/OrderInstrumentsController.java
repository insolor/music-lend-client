package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OrderInstrumentsController {
    private Shop shop;

    @FXML
    ListView listAvailableInstruments;

    @FXML
    void initialize() {
        shop = new Shop();

        listAvailableInstruments.getItems().addAll(shop.getAvailableInstruments());
    }
}
