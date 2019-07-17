package MusicLendClient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class InstrumentsInUseController {
    @FXML
    TableView<Instrument> tableInstrumentsInUse;

    @FXML
    void returnInstrument() {
        Instrument instrument = tableInstrumentsInUse.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            Main.connection.returnInstrument(instrument);
            updateInstrumentsInUseList();
            Main.shop.invalidate();
        }
    }

    @FXML
    void returnAllInstruments() {
        Main.connection.returnAllInstruments();
        updateInstrumentsInUseList();
        Main.shop.invalidate();
    }

    private void initTableColumns() {
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableInstrumentsInUse.getColumns().add(nameColumn);
    }

    private void updateInstrumentsInUseList() {
        tableInstrumentsInUse.setItems(FXCollections.observableArrayList(Main.localUser.getInstrumentsInUse()));
    }

    @FXML
    void initialize() {
        initTableColumns();
        updateInstrumentsInUseList();
        Main.localUser.addListener(observable -> updateInstrumentsInUseList());
    }
}
