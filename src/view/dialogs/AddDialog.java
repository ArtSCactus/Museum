package view.dialogs;

import controller.Controller;
import entity.ExhibitCard;
import entity.Request;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.RequestConverter;

import java.sql.Date;
import java.sql.SQLException;

public class AddDialog {
    public void showAddExhibitDialog(Controller controller) {
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 425, 225);
        Stage stage = new Stage();
        stage.setMaxWidth(425);
        stage.setMaxHeight(260);
        stage.setMinWidth(425);
        stage.setMaxHeight(260);
        Label exhibitCodeLabel = new Label("Code number:");
        TextField exhibitCode = new TextField();
        exhibitCode.setPromptText("Enter code here");
        pane.add(exhibitCodeLabel, 0, 0);
        pane.add(exhibitCode, 1, 0);

        Label exhibitNameLabel = new Label("Name:");
        TextField exhibitName = new TextField();
        exhibitName.setPromptText("Enter name here");
        pane.add(exhibitNameLabel, 0, 1);
        pane.add(exhibitName, 1, 1);

        Label exhibitFundLabel = new Label("Fund:");
        TextField exhibitFund = new TextField();
        exhibitFund.setPromptText("Enter exhibit fund here");
        pane.add(exhibitFundLabel, 0, 2);
        pane.add(exhibitFund, 1, 2);

        Label exhibitCreationDateLabel = new Label("Creation date:");
        DatePicker exhibitCreationDate = new DatePicker();
        exhibitCreationDate.setPromptText("Choose creation date");
        pane.add(exhibitCreationDateLabel, 0, 3);
        pane.add(exhibitCreationDate, 1, 3);

        Label exhibitDateAccuracyLabel = new Label("Date accuracy:");
        TextField exhibitDateAccuracy = new TextField();
        exhibitDateAccuracy.setPromptText("Enter date accuracy here");
        pane.add(exhibitDateAccuracyLabel, 0, 4);
        pane.add(exhibitDateAccuracy, 1, 4);

        Label exhibitAuthorLabel = new Label("Author:");
        TextField exhibitAuthor = new TextField();
        exhibitAuthor.setPromptText("Enter author code");
        pane.add(exhibitAuthorLabel, 0, 5);
        pane.add(exhibitAuthor, 1, 5);
        pane.setPrefWidth(200);

        Button addBtn = new Button("Add");
        addBtn.setOnAction(event -> {
            ExhibitCard exhibitCard = new ExhibitCard.Builder()
                    .withID(exhibitCode.getText())
                    .withName(exhibitName.getText())
                    .withFund(exhibitFund.getText())
                    .withCreationDate(Date.valueOf(exhibitCreationDate.getValue()))
                    .withDateAccuracy(exhibitDateAccuracy.getText())
                    .withAuthorCode(exhibitAuthor.getText())
                    .build();
            Request request = RequestConverter.exhibitToInsert(exhibitCard);

            try {
                controller.executePreparedInsert(request.getStatement(), request.getArgs());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Cannot add element");
                alert.setContentText("Element with id  \'"+exhibitCard.getId()+"\' already exists");
                alert.showAndWait();
                e.printStackTrace();
            }

        });
        addBtn.setMinWidth(pane.getPrefWidth());
        pane.add(addBtn, 0, 6);
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMinWidth(pane.getPrefWidth());
        pane.add(cancelBtn, 1, 6);

        stage.setScene(scene);
        stage.showAndWait();
        cancelBtn.setOnAction(event->{
            stage.close();
        });
    }
}
