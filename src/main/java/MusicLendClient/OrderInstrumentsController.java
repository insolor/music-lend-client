package MusicLendClient;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private void updateAvailableInstrumentsList() {
        tableAvailableInstruments.setItems(FXCollections.observableArrayList(shop.getAvailableInstruments()));
    }

    @FXML private void addToCart() {
        Main.connection.addToCart(tableAvailableInstruments.getSelectionModel().getSelectedItem());
        updateAvailableInstrumentsList();
    }

    @FXML private void showCart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Cart.fxml"));
        Stage mainWindow = new Stage();
        mainWindow.setTitle("Корзина");
        Scene scene = new Scene(root);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    @FXML
    void initialize() {
        shop = Main.connection.getShop();
        initTableColumns();
        updateAvailableInstrumentsList();

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
