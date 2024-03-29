package MusicLendClient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Collection;

public class InstrumentsInUseController {
    @FXML
    TableView<Instrument> tableInstrumentsInUse;

    @FXML
    void returnInstrument() {
        Instrument instrument = tableInstrumentsInUse.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            try {
                Main.connection.returnInstrument(instrument);
            }
            catch (IOException ex) {
                Main.showError("Ошибка соединения", "");
                return;
            }
            catch (Connection.UnexpectedResultException ex) {
                Main.showError("Ошибка при запросе данных", ex.getMessage());
                return;
            }
            updateInstrumentsInUseList();
            Main.shop.invalidate();
        }
    }

    @FXML
    void returnAllInstruments() {
        try {
            Main.connection.returnAllInstruments();
        }
        catch (IOException ex) {
            Main.showError("Ошибка соединения", "");
            return;
        }
        catch (Connection.UnexpectedResultException ex) {
            Main.showError("Ошибка при запросе данных", ex.getMessage());
            return;
        }
        updateInstrumentsInUseList();
        Main.shop.invalidate();
    }

    private void initTableColumns() {
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableInstrumentsInUse.getColumns().add(nameColumn);
    }

    private void updateInstrumentsInUseList() {
        Collection<Instrument> instruments;
        try {
            instruments = Main.connection.getInstrumentsInUse();
        }
        catch (IOException ex) {
            Main.showError("Ошибка соединения", "");
            return;
        }
        catch (Connection.UnexpectedResultException ex) {
            Main.showError("Ошибка при запросе данных", ex.getMessage());
            return;
        }
        Main.localUser.setInstrumentsInUse(instruments);
        tableInstrumentsInUse.setItems(FXCollections.observableArrayList(instruments));
    }

    @FXML
    void initialize() {
        initTableColumns();
        updateInstrumentsInUseList();
        Main.localUser.addListener(observable -> updateInstrumentsInUseList());
    }
}
