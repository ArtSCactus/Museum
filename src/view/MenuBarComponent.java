package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarComponent {
    private MenuBar bar;
    private Menu addMenu;
    private Menu editMenu;
    private Menu deleteMenu;

    public MenuBarComponent() {
        bar = new MenuBar();
        addMenu = new Menu("Add");
        editMenu = new Menu("Edit");
        deleteMenu = new Menu("Remove");
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

    private void initMenuBar(){
        initAddMenu();
        initEditMenu();
        initRemoveMenu();
        bar.getMenus().addAll(addMenu, editMenu, deleteMenu);
    }

    private void initAddMenu(){
        MenuItem addExhibitItem = new MenuItem("Exhibit");
        MenuItem addExpositionItem = new MenuItem("Exposition");
        MenuItem addAuthorItem = new MenuItem("Author");
        addMenu.getItems().addAll(addExhibitItem, addExpositionItem, addAuthorItem);
    }

    private void initEditMenu(){
        MenuItem editExhibitItem = new MenuItem("Exhibit");
        MenuItem editExpositionItem = new MenuItem("Exposition");
        MenuItem editAuthorItem = new MenuItem("Author");
        editMenu.getItems().addAll(editExhibitItem, editExpositionItem, editAuthorItem);
    }

    private void initRemoveMenu(){
        MenuItem removeExhibitItem = new MenuItem("Exhibit");
        MenuItem removeExpositionItem = new MenuItem("Exposition");
        MenuItem removeAuthorItem = new MenuItem("Author");
        deleteMenu.getItems().addAll(removeExhibitItem, removeExpositionItem, removeAuthorItem);
    }

}
