package view.dialogs;

import controller.Controller;
import entity.ExhibitCard;
import entity.Request;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.RequestConverter;
import util.ResultSetConverter;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditDialog {

    public void showEditDialog(Controller controller) {
        List<ExhibitCard> cards = null;
        try {
            cards =
                    ResultSetConverter.toExhibitCards(controller.executePreparedRequest("select * from exhibits"));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        List<String> codes = new ArrayList<>();
        for (ExhibitCard card : cards) {
            codes.add(card.getId());
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.getItems().addAll(codes);
        choiceDialog.setSelectedItem(codes.get(0));
        choiceDialog.setTitle("Choose exhibit");
        choiceDialog.setHeaderText("Please, choose the exhibit code");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            choiceDialog.close();
            ExhibitCard foundCard = null;
            for (ExhibitCard card : cards) {
                if (card.getId().equals(result.get())) {
                    foundCard = card;
                    break;
                }
            }
            mainPart(controller, foundCard);
        } else {
            choiceDialog.close();
        }
    }

    private void mainPart(Controller controller, ExhibitCard cardToEdit) {
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 425, 225);
        Stage stage = new Stage();
        stage.setMaxWidth(425);
        stage.setMaxHeight(285);
        stage.setMinWidth(425);
        stage.setMaxHeight(285);
        Label exhibitCodeLabel = new Label("Code number:");
        TextField exhibitCode = new TextField(cardToEdit.getId());
        exhibitCode.setPromptText("Enter code here");
        pane.add(exhibitCodeLabel, 0, 0);
        pane.add(exhibitCode, 1, 0);

        Label exhibitNameLabel = new Label("Name:");
        TextField exhibitName = new TextField(cardToEdit.getName());
        exhibitName.setPromptText("Enter name here");
        pane.add(exhibitNameLabel, 0, 1);
        pane.add(exhibitName, 1, 1);

        Label exhibitFundLabel = new Label("Fund:");
        TextField exhibitFund = new TextField(cardToEdit.getFund());
        exhibitFund.setPromptText("Enter exhibit fund here");
        pane.add(exhibitFundLabel, 0, 2);
        pane.add(exhibitFund, 1, 2);

        Label exhibitCreationDateLabel = new Label("Creation date:");
        DatePicker exhibitCreationDate = new DatePicker(LocalDate.parse(cardToEdit.getCreationDate().toString()));
        exhibitCreationDate.setPromptText("Choose creation date");
        pane.add(exhibitCreationDateLabel, 0, 3);
        pane.add(exhibitCreationDate, 1, 3);

        Label exhibitDateAccuracyLabel = new Label("Date accuracy:");
        TextField exhibitDateAccuracy = new TextField(cardToEdit.getDateAccuracy());
        exhibitDateAccuracy.setPromptText("Enter date accuracy here");
        pane.add(exhibitDateAccuracyLabel, 0, 4);
        pane.add(exhibitDateAccuracy, 1, 4);

        Label exhibitAuthorLabel = new Label("Author:");
        TextField exhibitAuthor = new TextField(cardToEdit.getAuthorCode());
        exhibitAuthor.setPromptText("Enter author code");
        pane.add(exhibitAuthorLabel, 0, 5);
        pane.add(exhibitAuthor, 1, 5);
        pane.setPrefWidth(200);

        Button addBtn = new Button("Add");
        addBtn.setOnAction(event -> {
            ExhibitCard changedExhibitCard = new ExhibitCard.Builder()
                    .withID(exhibitCode.getText())
                    .withName(exhibitName.getText())
                    .withFund(exhibitFund.getText())
                    .withCreationDate(Date.valueOf(exhibitCreationDate.getValue()))
                    .withDateAccuracy(exhibitDateAccuracy.getText())
                    .withAuthorCode(exhibitAuthor.getText())
                    .build();
            Request request = RequestConverter.exhibitToUpdate(cardToEdit, changedExhibitCard);
            try {
                controller.executePreparedUpdate(request.getStatement(), request.getArgs());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Successful operation");
                alert.setContentText("Successfully changed the exhibit");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Access error");
                alert.setContentText("Cannot change row due to connection/access errors");
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
        cancelBtn.setOnAction(event -> {
            stage.close();
        });
    }
}
