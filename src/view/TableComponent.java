package view;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.text.CaseUtils;

import java.lang.reflect.Field;

public class TableComponent {
    private TableView<ObservableList> table;
    private boolean isTableReadyForDataStream;

    public TableComponent(TableView<ObservableList> table) {
        this.table = table;
    }

    public TableComponent() {
        table = new TableView();
    }

    public TableView getTable() {
        return table;
    }

    public void addColumn(TableColumn column) {
        table.getColumns().add(column);
    }

    public boolean isTableReadyForDataStream() {
        return isTableReadyForDataStream;
    }

    public void setTableReadyForDataStream(boolean tableReadyForDataStream) {
        isTableReadyForDataStream = tableReadyForDataStream;
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
}
