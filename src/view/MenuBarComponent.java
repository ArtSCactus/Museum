package view;

import controller.Controller;
import entity.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.ResultSetConverter;
import view.dialogs.AddDialog;
import view.dialogs.DeleteDialog;
import view.dialogs.EditDialog;
import view.dialogs.SearchDialog;

import java.sql.SQLException;
import java.util.List;

public class MenuBarComponent {
    private MenuBar bar;
    private Menu addMenu;
    private Menu editMenu;
    private Menu deleteMenu;
    private Menu viewMenu;
    private Menu searchMenu;
    private TableComponent table;
    private Controller controller;

    public MenuBarComponent(TableComponent table, Controller controller) {
        bar = new MenuBar();
        addMenu = new Menu("Add");
        editMenu = new Menu("Edit");
        deleteMenu = new Menu("Remove");
        viewMenu = new Menu("View");
        searchMenu = new Menu("Search ");
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
        initSearchMenu();
        bar.getMenus().addAll(addMenu, editMenu, deleteMenu, viewMenu, searchMenu);
    }

    private void initAddMenu() {
        MenuItem addExhibitItem = new MenuItem("Exhibit");
        addExhibitItem.setOnAction(event -> {
            new AddDialog().showAddExhibitDialog(controller);
        });
        MenuItem addExpositionItem = new MenuItem("Exposition");
        MenuItem addAuthorItem = new MenuItem("Author");
        MenuItem transferItem = new MenuItem("Transfer");
        transferItem.setOnAction(event -> {
           new AddDialog().showAddAddTransferDialog(controller);
        });
        addMenu.getItems().addAll(addExhibitItem, addExpositionItem, addAuthorItem, transferItem);
    }

    private void initEditMenu() {
        MenuItem editExhibitItem = new MenuItem("Exhibit");
        editExhibitItem.setOnAction(event ->{
            new EditDialog().showEditDialog(controller);
        });
        MenuItem editExpositionItem = new MenuItem("Exposition");
        MenuItem editAuthorItem = new MenuItem("Author");
        MenuItem editTransfer = new MenuItem("Transfer");
        editTransfer.setOnAction(event -> {
            new EditDialog().showEditTransferDialog(controller);
        });
        editMenu.getItems().addAll(editExhibitItem, editExpositionItem, editAuthorItem, editTransfer);
    }

    private void initRemoveMenu() {
        MenuItem removeExhibitItem = new MenuItem("Exhibit");
        removeExhibitItem.setOnAction(event -> {
            DeleteDialog.showDeleteDialog(controller);
        });
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
                e.printStackTrace();
            }
            table.showAuthors(authors);
        });
        MenuItem viewInternalTransfers = new MenuItem("Internal transfers");
        viewInternalTransfers.setOnAction(event -> {
            List<Transfer> transfers = null;
            try {
                transfers = ResultSetConverter.toInternalTransfers(
                        controller.executePreparedRequest("select * from internal_transfers"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            table.showInternalTransfers(transfers);
        });
        MenuItem viewExternalTransfers = new MenuItem("External transfers");
        viewExternalTransfers.setOnAction(event -> {
            List<Transfer> transfers = null;
            try {
                transfers = ResultSetConverter.toExternalTransfers(
                        controller.executePreparedRequest("select * from external_transfers"));
            } catch (SQLException e) {
                e.printStackTrace();

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
                e.printStackTrace();
            }
            table.showFunds(funds);
        });

        MenuItem viewSets = new MenuItem("Sets");
        viewSets.setOnAction(event -> {
            List<Set> sets = null;
            try{
                sets = ResultSetConverter.toSets(
                        controller.executePreparedRequest("select * from sets")
                );
            }catch (SQLException e){
                e.printStackTrace();
            }
            table.showSets(sets);
        });
        viewMenu.getItems().addAll(viewExhibitItem, viewExpositionItem, viewAuthorItem, viewInternalTransfers,
                viewExternalTransfers, viewFundsItem, viewSets);
    }

    private void initSearchMenu(){
        MenuItem exhibitSearchItem = new MenuItem("Last exhibit/set placement");
        exhibitSearchItem.setOnAction(event -> {
            new SearchDialog().showSearchExhibitDialog(controller);
        });
        searchMenu.getItems().addAll(exhibitSearchItem);
    }

}
