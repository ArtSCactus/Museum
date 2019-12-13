package view;

import javafx.scene.control.TableView;

public class MainWindow {
    private TableView table;

    public MainWindow(TableView table) {
        this.table = table;
    }

    public MainWindow() {
        table = new TableView();
    }

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }
}
