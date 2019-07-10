package MusicLendClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class CartController {
    @FXML
    TableView<Instrument> tableInstrumentsInCart;

    @FXML
    TextArea textDescription;

    @FXML
    void initialize() {

    }

    @FXML
    private void removeFromCart(ActionEvent event) {

    }
}
