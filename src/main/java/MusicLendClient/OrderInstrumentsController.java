package MusicLendClient;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class OrderInstrumentsController {
    private Shop shop;

    @FXML
    TableView<Instrument> tableAvailableInstruments;

    @FXML
    TextArea textDescription;

    private void initTableColumns() {
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableAvailableInstruments.getColumns().add(nameColumn);

        TableColumn<Instrument, BigDecimal> priceForDay = new TableColumn<>("Цена за сутки");
        priceForDay.setCellValueFactory(new PropertyValueFactory<>("priceForDay"));
        tableAvailableInstruments.getColumns().add(priceForDay);
    }

    @FXML private void addToCart() {
        Main.connection.addToCart(tableAvailableInstruments.getSelectionModel().getSelectedItem());
        tableAvailableInstruments.setItems(FXCollections.observableArrayList(shop.getAvailableInstruments()));
    }

    @FXML
    void initialize() {
        shop = Main.connection.getShop();
        initTableColumns();
        tableAvailableInstruments.setItems(FXCollections.observableArrayList(shop.getAvailableInstruments()));

        ChangeListener<Instrument> listener = (obs, oldValue, newValue) -> {
            if(newValue == null) {
                textDescription.setText("");
            }
            else {
                textDescription.setText(newValue.getDescription());
            }
        };
        tableAvailableInstruments.getSelectionModel().selectedItemProperty().addListener(listener);
    }
}
