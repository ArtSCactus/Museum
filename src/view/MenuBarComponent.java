package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarComponent {
    private MenuBar bar;

    public MenuBarComponent() {
        bar = new MenuBar();
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

    public void add(Menu value){
        bar.getMenus().add(value);
    }

    public void addOnPosition(Menu value, int position){
        bar.getMenus().add(position, value);
    }

}
