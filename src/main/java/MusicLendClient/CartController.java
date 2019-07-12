package MusicLendClient;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class CartController {

    private User user;

    @FXML
    TextField txtPromo;

    @FXML
    Spinner spinNumberOfDays;

    @FXML
    TableView<Instrument> tableInstrumentsInCart;

    @FXML
    TextArea textDescription;

    private void initTableColumns() {
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableInstrumentsInCart.getColumns().add(nameColumn);

        TableColumn<Instrument, BigDecimal> priceForDay = new TableColumn<>("Цена за сутки");
        priceForDay.setCellValueFactory(new PropertyValueFactory<>("priceForDay"));
        tableInstrumentsInCart.getColumns().add(priceForDay);
    }

    private void updateInstrumentsInCart() {
        tableInstrumentsInCart.setItems(FXCollections.observableArrayList(user.getInstrumentsInCart()));
    }

    @FXML
    void initialize() {
        user = Main.connection.getUser();
        initTableColumns();
        updateInstrumentsInCart();
    }

    @FXML
    void removeFromCart(ActionEvent event) {
        Instrument instrument = tableInstrumentsInCart.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            Main.connection.removeFromCart(instrument);
            updateInstrumentsInCart();
        }
    }

    @FXML
    void pay(ActionEvent actionEvent) {
        if(!user.getInstrumentsInCart().isEmpty()) {
            Main.connection.pay(new Cart(user.getInstrumentsInCart(), txtPromo.getText(),
                    (Integer) spinNumberOfDays.getValue()));
            updateInstrumentsInCart();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Оплачено", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
