package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.math.BigDecimal;

public class OrderInstrumentsController {
    private Shop shop;

    @FXML
    ListView listAvailableInstruments;

    @FXML
    void initialize() {
        shop = Main.connection.getShop();
        shop.getAvailableInstruments()
                .add(new Instrument(1, "Test", "Some description", new BigDecimal("100.00")));

        for (Instrument instrument: shop.getAvailableInstruments()) {
            listAvailableInstruments.getItems().add(instrument.getName());
        }
    }
}
