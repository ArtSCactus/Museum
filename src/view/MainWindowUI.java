package view;

import entity.ExhibitCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.enums.UIPlacement;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainWindowUI {
    private TableComponent tableComponent;
    private MenuBarComponent toolBar;
    private Stage primaryStage;
    private BorderPane pane;
    private Scene scene;

    public MainWindowUI() {
        tableComponent = new TableComponent();
        primaryStage = new Stage();
        pane = new BorderPane();
        toolBar = new MenuBarComponent();
        scene = new Scene(pane, 500, 500);
    }

    public MainWindowUI(int width, int height) {
        tableComponent = new TableComponent();
        toolBar = new MenuBarComponent();
        primaryStage = new Stage();
        pane = new BorderPane();
        scene = new Scene(pane, width, height);
    }

    public void show() {
        primaryStage.setScene(scene);
        placeComponent(tableComponent.getTable(), UIPlacement.CENTER);
        placeComponent(toolBar.getBar(), UIPlacement.TOP);
        List<ExhibitCard> cards = new ArrayList<>();
        cards.add(new ExhibitCard.Builder()
                .withName("Name")
                .withCreationDate(Date.valueOf(LocalDate.now()))
                .withFund("fund")
                .withID(1)
                .withDateAccuracy("Date is accurate")
                .build()
        );
        System.out.println(Date.valueOf(LocalDate.now()).toString());
        ObservableList<ExhibitCard> list = FXCollections.observableList(cards);
        tableComponent.createAndInitColumn("Name", "NAmE");
        tableComponent.createAndInitColumn("Creation date", "creation date");
        tableComponent.createAndInitColumn("Fund", "fund");
        tableComponent.createAndInitColumn("ID", "id");
        tableComponent.createAndInitColumn("Date accuracy", "date accuracy");
        tableComponent.setData(list);
        primaryStage.showAndWait();
    }

    public void placeComponent(Node component, UIPlacement position) {
        switch (position) {
            case TOP:
                pane.setTop(component);
                break;
            case LEFT:
                pane.setLeft(component);
                break;
            case RIGHT:
                pane.setRight(component);
                break;
            case CENTER:
                pane.setCenter(component);
                break;
            case BOTTOM:
                pane.setBottom(component);
                break;
            default:
                throw new IllegalArgumentException("Wrong component position: " + position.getName());
        }
    }

}
