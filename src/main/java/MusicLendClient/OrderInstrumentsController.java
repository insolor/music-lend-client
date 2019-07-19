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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

public class OrderInstrumentsController {
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
        tableAvailableInstruments.setItems(FXCollections.observableArrayList(Main.shop.getAvailableInstruments()));
    }

    @FXML
    private void addToCart() {
        Instrument instrument = tableAvailableInstruments.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            try {
                Main.connection.addToCart(instrument);
            }
            catch (IOException ex) {
                Main.showError("Ошибка соединения", "");
                return;
            }
            catch (Connection.UnexpectedResultException ex) {
                Main.showError("Ошибка при запросе данных", ex.getMessage());
                return;
            }
            updateAvailableInstrumentsList();
        }
    }

    @FXML
    private void showCart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Cart.fxml"));
        Stage cartWindow = new Stage();
        cartWindow.setTitle("Корзина");
        cartWindow.initOwner(MainWindowController.stage);
        cartWindow.initModality(Modality.APPLICATION_MODAL);

        cartWindow.setOnCloseRequest(event -> updateAvailableInstrumentsList());

        Scene scene = new Scene(root);
        cartWindow.setScene(scene);
        cartWindow.show();
    }

    @FXML
    void initialize() throws IOException {
        Collection<Instrument> instruments;

        try {
            instruments = Main.connection.getAvailableInstruments();
        }
        catch (Connection.UnexpectedResultException ex) {
            Main.showError("Ошибка при запросе данных", ex.getMessage());
            instruments = new LinkedList<>();
        }

        Main.shop = new Shop(instruments);
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
        Main.shop.addListener(observable -> updateAvailableInstrumentsList());
    }
}
