package MusicLendClient;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class OrderInstrumentsController {
    private Shop shop;

    @FXML
    TableView<Instrument> tableAvailableInstruments;

    @FXML
    void initialize() {
        shop = Main.connection.getShop();
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableAvailableInstruments.getColumns().add(nameColumn);

        TableColumn<Instrument, BigDecimal> priceForDay = new TableColumn<>("Цена за сутки");
        priceForDay.setCellValueFactory(new PropertyValueFactory<>("priceForDay"));
        tableAvailableInstruments.getColumns().add(priceForDay);

        tableAvailableInstruments.getItems().addAll(shop.getAvailableInstruments());
    }
}
