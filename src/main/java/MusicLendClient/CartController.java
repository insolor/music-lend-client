package MusicLendClient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class CartController {
    private Cart cart;

    @FXML
    TextField txtPromo, txtSum, txtDiscountPercent, txtDiscountSum, txtSumToPay;

    @FXML
    Spinner<Integer> spinNumberOfDays;

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
        tableInstrumentsInCart.setItems(FXCollections.observableArrayList(cart.getInstruments()));
    }

    @FXML
    void initialize() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Ошибка");

        try {
            cart = Main.connection.getCart();
        }
        catch (IOException ex) {
            errorAlert.setContentText("Ошибка соединения");
            errorAlert.showAndWait();
            cart = new Cart();
        }
        catch (Connection.UnexpectedResultException ex) {
            errorAlert.setContentText("Ошибка при запросе данных:\n".concat(ex.getMessage()));
            errorAlert.showAndWait();
            cart = new Cart();
        }

        initTableColumns();
        updateInstrumentsInCart();
        spinNumberOfDays.getValueFactory().setValue(cart.getDays());
        txtPromo.setText(cart.getPromocode());

        // action when promocode text is changed
        txtPromo.textProperty().addListener((observable, oldValue, newValue)-> {
            BigDecimal oldPercent, newPercent;
            oldPercent = Main.connection.getPromocodePercent(oldValue);
            newPercent = Main.connection.getPromocodePercent(newValue);
            if(!oldPercent.equals(newPercent)) {
                // TODO: show promocode status somehow
                recalcCart();
            }
        });

        // action when number of days is changed
        spinNumberOfDays.valueProperty().addListener((observable, oldValue, newValue)-> recalcCart());

        recalcCart();
    }

    @FXML
    void removeFromCart() {
        Instrument instrument = tableInstrumentsInCart.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            cart.getInstruments().remove(instrument);
            Main.connection.removeFromCart(instrument);
            updateInstrumentsInCart();
            recalcCart();
        }
    }

    @FXML
    void pay() {
        if(!cart.getInstruments().isEmpty()) {
            cart.setPromocode(txtPromo.getText());
            cart.setDays(spinNumberOfDays.getValue());
            Main.connection.pay(cart);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Оплачено", ButtonType.OK);
            alert.showAndWait();

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Ошибка");

            try {
                cart = Main.connection.getCart();
            }
            catch (IOException ex) {
                errorAlert.setContentText("Ошибка соединения");
                errorAlert.showAndWait();
                cart = new Cart();
            }
            catch (Connection.UnexpectedResultException ex) {
                errorAlert.setContentText("Ошибка при запросе данных:\n".concat(ex.getMessage()));
                errorAlert.showAndWait();
                cart = new Cart();
            }

            updateInstrumentsInCart();
            Main.localUser.invalidate();
        }
    }

    private void recalcCart() {
        Cart cart = new Cart(tableInstrumentsInCart.getItems(), txtPromo.getText(), spinNumberOfDays.getValue());

        CartCalculationResult result = Main.connection.calculateCart(cart);
        txtDiscountPercent.setText(result.getDiscountPercent().toString());
        txtDiscountSum.setText(result.getDiscountSum().toString());
        txtSumToPay.setText(result.getSumToBePaid().toString());
        txtSum.setText(result.getSumToBePaid().add(result.getDiscountSum()).toString());
    }
}
