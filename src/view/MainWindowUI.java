package view;

import entity.Author;
import entity.ExhibitCard;
import exceptions.DriverNotFoundException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.Controller;
import util.ResultSetConverter;
import view.enums.UIPlacement;

import java.sql.SQLException;
import java.util.List;

public class MainWindowUI {
    private TableComponent tableComponent;
    private MenuBarComponent toolBar;
    private Controller controller;
    private Stage primaryStage;
    private BorderPane pane;
    private Scene scene;

    public MainWindowUI() {
        tableComponent = new TableComponent();
        controller = new Controller();
        primaryStage = new Stage();
        pane = new BorderPane();
        toolBar = new MenuBarComponent(tableComponent, controller);
        scene = new Scene(pane, 500, 500);
    }

    public MainWindowUI(int width, int height) {
        tableComponent = new TableComponent();
        controller = new Controller();
        toolBar = new MenuBarComponent(tableComponent, controller);
        primaryStage = new Stage();
        pane = new BorderPane();
        scene = new Scene(pane, width, height);
    }

    public void run() throws SQLException {
        try {
            controller.connect();
        } catch (SQLException | DriverNotFoundException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(scene);
        placeComponent(tableComponent.getTable(), UIPlacement.CENTER);
        placeComponent(toolBar.getBar(), UIPlacement.TOP);
        List<ExhibitCard> cards = ResultSetConverter.toExhibitCards(
                controller.executePreparedRequest("select * from exhibits"));
        tableComponent.showExhibits(cards);
        List<Author> authors = ResultSetConverter.toAuthors(
                controller.executePreparedRequest("select * from authors"));
        tableComponent.showAuthors(authors);

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
