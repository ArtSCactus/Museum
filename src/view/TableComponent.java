package view;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.text.CaseUtils;

import java.util.List;

public class TableComponent {
    private TableView<ObservableList> table;

    public TableComponent(TableView<ObservableList> table) {
        this.table = table;
    }

    public TableComponent() {
        table = new TableView<>();
    }

    public TableView getTable() {
        return table;
    }

    public void addColumn(TableColumn column) {
        table.getColumns().add(column);
    }

    public void createAndInitColumn(String columnName, String variableName) {
        TableColumn newColumn = new TableColumn(columnName);
        CaseUtils.toCamelCase(variableName, true);
        variableName = CaseUtils.toCamelCase(variableName, false);
        newColumn.setCellValueFactory(new PropertyValueFactory(variableName));
        table.getColumns().add(newColumn);
    }

    public Object getCellValue(int column, int row) {
        return table.getColumns().get(column).getCellData(row);
    }

    public void setData(ObservableList data) {
        table.setItems(data);
    }

    public void showExhibits(List<ExhibitCard> cards){
       table.getColumns().clear();
        ObservableList<ExhibitCard> list = FXCollections.observableList(cards);
        createAndInitColumn("Name", "name");
        createAndInitColumn("Creation date", "creation date");
        createAndInitColumn("ID", "id");
        createAndInitColumn("Date accuracy", "date accuracy");
        setData(list);
    }

    public void showAuthors(List<Author> authors){
        table.getColumns().clear();
        ObservableList<Author> list = FXCollections.observableList(authors);
        createAndInitColumn("Number", "Number");
        createAndInitColumn("First name", "First name");
        createAndInitColumn("Second name", "Second name");
        createAndInitColumn("Third name", "Third name");
        createAndInitColumn("Country", "country");
        setData(list);
    }

    public void showExpositions(List<Exposition> expositions){
        table.getColumns().clear();
        ObservableList<Exposition> list = FXCollections.observableList(expositions);
        createAndInitColumn("Number", "Number");
        createAndInitColumn("Name", "Name");
        createAndInitColumn("Address", "Address");
        createAndInitColumn("Contact person", "contact person");
        createAndInitColumn("Phone number", "phone number");
        createAndInitColumn("Date of start ", "start date");
        createAndInitColumn("Date of end", "end date");
        setData(list);
    }

    public void showFunds(List<Fund> funds){
        table.getColumns().clear();
        ObservableList<Fund> list = FXCollections.observableList(funds);
        createAndInitColumn("Name", "name");
        createAndInitColumn("Card/Set number", "card or set number");
        setData(list);
    }

    public void showExternalTransfers(List<Transfer> transfers){
        table.getColumns().clear();
        ObservableList<Transfer> list = FXCollections.observableList(transfers);
        createAndInitColumn("Number","number");
        createAndInitColumn("Action","action");
        createAndInitColumn("Exposition number","exposition number");
        createAndInitColumn("Date of start", "start date");
        createAndInitColumn("Date of end", "end date");
        setData(list);
    }
    public void showInternalTransfers(List<Transfer> transfers){
        table.getColumns().clear();
        ObservableList<Transfer> list = FXCollections.observableList(transfers);
        createAndInitColumn("Number","number");
        createAndInitColumn("Action","action");
        createAndInitColumn("Taken from","fund take from");
        createAndInitColumn("Placed in", "fund placed in");
        createAndInitColumn("Date", "start date");
        setData(list);
    }

}
