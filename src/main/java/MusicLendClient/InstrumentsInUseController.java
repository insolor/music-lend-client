package MusicLendClient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InstrumentsInUseController {
    private User user = Main.connection.getUser();

    @FXML
    TableView<Instrument> tableInstrumentsInUse;

    @FXML
    void returnInstrument() {
        Instrument instrument = tableInstrumentsInUse.getSelectionModel().getSelectedItem();
        if(instrument != null) {
            Main.connection.returnInstrument(instrument);
            updateInstrumentsInUseList();
            Main.connection.getShop().invalidate();
        }
    }

    @FXML
    void returnAllInstruments() {
        Main.connection.returnAllInstruments();
        updateInstrumentsInUseList();
        Main.connection.getShop().invalidate();
    }

    private void initTableColumns() {
        TableColumn<Instrument, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableInstrumentsInUse.getColumns().add(nameColumn);
    }

    private void updateInstrumentsInUseList() {
        tableInstrumentsInUse.setItems(FXCollections.observableArrayList(user.getInstrumentsInUse()));
    }

    @FXML
    void initialize() {
        initTableColumns();
        updateInstrumentsInUseList();
        user.addListener(observable -> updateInstrumentsInUseList());
    }
}
