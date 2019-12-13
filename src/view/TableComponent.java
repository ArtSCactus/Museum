package view;

import entity.Author;
import entity.ExhibitCard;
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
}
