package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OrderInstrumentsController {
    private Shop shop;

    @FXML
    ListView listAvailableInstruments;

    @FXML
    void initialize() {
        shop = Main.connection.getShop();

        for (Instrument instrument: shop.getAvailableInstruments()) {
            listAvailableInstruments.getItems().add(instrument.getName());
        }
    }
}
