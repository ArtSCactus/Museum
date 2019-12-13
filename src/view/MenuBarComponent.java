package view;

import controller.Controller;
import entity.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.ResultSetConverter;

import java.sql.SQLException;
import java.util.List;

public class MenuBarComponent {
    private MenuBar bar;
    private Menu addMenu;
    private Menu editMenu;
    private Menu deleteMenu;
    private Menu viewMenu;
    private TableComponent table;
    private Controller controller;

    public MenuBarComponent(TableComponent table, Controller controller) {
        bar = new MenuBar();
        addMenu = new Menu("Add");
        editMenu = new Menu("Edit");
        deleteMenu = new Menu("Remove");
        viewMenu = new Menu("View");
        this.table = table;
        this.controller = controller;
        initMenuBar();
    }

    public MenuBarComponent(MenuBar bar) {
        this.bar = bar;
    }

    public MenuBar getBar() {
        return bar;
    }

    public void setBar(MenuBar bar) {
        this.bar = bar;
    }

    public void add(Menu value) {
        bar.getMenus().add(value);
    }

    public void addOnPosition(Menu value, int position) {
        bar.getMenus().add(position, value);
    }

    private void initMenuBar() {
        initAddMenu();
        initEditMenu();
        initRemoveMenu();
        initViewMenu();
        bar.getMenus().addAll(addMenu, editMenu, deleteMenu, viewMenu);
    }

    private void initAddMenu() {
        MenuItem addExhibitItem = new MenuItem("Exhibit");
        MenuItem addExpositionItem = new MenuItem("Exposition");
        MenuItem addAuthorItem = new MenuItem("Author");
        addMenu.getItems().addAll(addExhibitItem, addExpositionItem, addAuthorItem);
    }

    private void initEditMenu() {
        MenuItem editExhibitItem = new MenuItem("Exhibit");
        MenuItem editExpositionItem = new MenuItem("Exposition");
        MenuItem editAuthorItem = new MenuItem("Author");
        editMenu.getItems().addAll(editExhibitItem, editExpositionItem, editAuthorItem);
    }

    private void initRemoveMenu() {
        MenuItem removeExhibitItem = new MenuItem("Exhibit");
        MenuItem removeExpositionItem = new MenuItem("Exposition");
        MenuItem removeAuthorItem = new MenuItem("Author");
        deleteMenu.getItems().addAll(removeExhibitItem, removeExpositionItem, removeAuthorItem);
    }

    private void initViewMenu() {
        MenuItem viewExhibitItem = new MenuItem("Exhibit");
        viewExhibitItem.setOnAction(event -> {
            List<ExhibitCard> cards = null;
            try {
                cards = ResultSetConverter.toExhibitCards(
                        controller.executePreparedRequest("select * from exhibits"));
            } catch (SQLException e) {
            }
            table.showExhibits(cards);
        });
        MenuItem viewExpositionItem = new MenuItem("Exposition");
        viewExpositionItem.setOnAction(event -> {
            List<Exposition> expositions = null;
            try {
                expositions = ResultSetConverter.toExpositions(
                        controller.executePreparedRequest("select * from expositions"));
            } catch (SQLException e) {
            }
            table.showExpositions(expositions);
        });
        MenuItem viewAuthorItem = new MenuItem("Author");
        viewAuthorItem.setOnAction(event -> {
            List<Author> authors = null;
            try {
                authors = ResultSetConverter.toAuthors(
                        controller.executePreparedRequest("select * from authors"));
            } catch (SQLException e) {
            }
            table.showAuthors(authors);
        });
        MenuItem viewInternalTransfers = new MenuItem("Internal transfers");
        viewInternalTransfers.setOnAction(event -> {
            List<Transfer> transfers = null;
            try {
                transfers = ResultSetConverter.toInternalTransfers(
                        controller.executePreparedRequest("select * from internal_transferes"));
            } catch (SQLException e) {
            }
            table.showInternalTransfers(transfers);
        });
        MenuItem viewExternalTransfers = new MenuItem();
        viewExternalTransfers.setOnAction(event -> {
            List<Transfer> transfers = null;
            try {
                transfers = ResultSetConverter.toExternalTransfers(
                        controller.executePreparedRequest("select * from external_transferes"));
            } catch (SQLException e) {
            }
            table.showExternalTransfers(transfers);
        });
        MenuItem viewFundsItem = new MenuItem("Funds");
        viewFundsItem.setOnAction(event -> {
            List<Fund> funds = null;
            try {
                funds = ResultSetConverter.toFunds(
                        controller.executePreparedRequest("select * from funds"));
            } catch (SQLException e) {
            }
            table.showFunds(funds);
        });
        viewMenu.getItems().addAll(viewExhibitItem, viewExpositionItem, viewAuthorItem, viewInternalTransfers,
                viewFundsItem);
    }

}
