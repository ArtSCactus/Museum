package view.dialogs;

import controller.Controller;
import entity.ExhibitCard;
import entity.Request;
import entity.Transfer;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.RequestConverter;
import util.ResultSetConverter;

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
            editExhibitCardDialog(controller, foundCard);
        } else {
            choiceDialog.close();
        }
    }

    private void editExhibitCardDialog(Controller controller, ExhibitCard cardToEdit) {
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
        cancelBtn.setOnAction(event -> {
            stage.close();
        });

        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showEditTransferDialog(Controller controller){
        ChoiceDialog<String> transferTypeChoice = new ChoiceDialog<>();
        transferTypeChoice.setTitle("Transfer type choosing");
        transferTypeChoice.setContentText("Choose transfer type");
        transferTypeChoice.getItems().add("Internal transfer");
        transferTypeChoice.getItems().add("External transfer");
       transferTypeChoice.setSelectedItem(transferTypeChoice.getItems().get(0));
        Optional<String> result = transferTypeChoice.showAndWait();
        if (result.isPresent()) {
            transferTypeChoice.close();
            if (result.get().equals(transferTypeChoice.getItems().get(0))){
                //TODO: internal edit here
                editInternalTransferDialog(controller);
            } else {
                //TODO: external edit here
                editExternalTransferDialog(controller);
            }
        } else {
            transferTypeChoice.close();
        }
    }

    private void editInternalTransferDialog(Controller controller) {
        List<Transfer> cards = null;
        try {
            cards =
                    ResultSetConverter.toInternalTransfers(
                            controller.executePreparedRequest("select * from internal_transfers"));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        List<String> codes = new ArrayList<>();
        for (Transfer transfer : cards) {
            codes.add(transfer.getNumber());
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.getItems().addAll(codes);
        choiceDialog.setSelectedItem(codes.get(0));
        choiceDialog.setTitle("Choose transfer");
        choiceDialog.setHeaderText("Please, choose the transfer code");
        Optional<String> result = choiceDialog.showAndWait();
        Transfer transferObj = new Transfer.Builder().build();
        if (result.isPresent()) {
            choiceDialog.close();
            for (Transfer card : cards) {
                if (card.getNumber().equals(result.get())) {
                    transferObj = card;
                    break;
                }
            }
        } else {
            choiceDialog.close();
            return;
        }
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 425, 225);
        Stage stage = new Stage();
        Label transferCodeLabel = new Label("Code number:");
        TextField transferCode = new TextField(transferObj.getNumber());
        transferCode.setPromptText("Enter code here");
        pane.add(transferCodeLabel, 0, 0);
        pane.add(transferCode, 1, 0);

        Label actionsChoiceLabel = new Label("Actions");
        ChoiceBox<String> actionsChoice = new ChoiceBox<>();
        actionsChoice.getItems().add("Принятие в музей");
        actionsChoice.getItems().add("Перемещение между фондами");
        actionsChoice.getItems().add("Списание");
        actionsChoice.setValue(transferObj.getAction());
        pane.add(actionsChoiceLabel, 0, 1);
        pane.add(actionsChoice, 1, 1);

        List<Object> fundsTakenFrom = null;
        try {
            fundsTakenFrom = ResultSetConverter.columnToList(
                    controller.executeRequest("select distinct name from funds"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label fundTakenFromLabel = new Label("Fund taken from:");
        ChoiceBox<String> fundTakeFromChoice = new ChoiceBox<>();
        for (Object fund : fundsTakenFrom) {
            fundTakeFromChoice.getItems().add((String) fund);
        }
        if (fundsTakenFrom.size()>0) {
            fundTakeFromChoice.setValue(transferObj.getFundTakeFrom());
        }
        pane.add(fundTakenFromLabel, 0, 2);
        pane.add(fundTakeFromChoice, 1, 2);

        List<Object> fundsPlacedIn = null;
        try {
            fundsPlacedIn = ResultSetConverter.columnToList(
                    controller.executeRequest("select distinct name from funds"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label fundPlacedIn = new Label("Fund placed in:");
        ChoiceBox<String> fundPlacedInChoice = new ChoiceBox<>();
        for (Object fund : fundsPlacedIn) {
            fundPlacedInChoice.getItems().add((String) fund);
        }
        if (fundsPlacedIn.size()>0) {
            fundPlacedInChoice.setValue(transferObj.getFundPlacedIn());
        }
        pane.add(fundPlacedIn, 0, 3);
        pane.add(fundPlacedInChoice, 1, 3);


        Label transferDateLabel = new Label("Date:");
        DatePicker transferDate = new DatePicker(LocalDate.parse(transferObj.getDate().toString()));
        transferDate.setPromptText("Choose date");
        pane.add(transferDateLabel, 0, 4);
        pane.add(transferDate, 1, 4);
        List<Object> sets = null;
        try {
            sets = ResultSetConverter.columnToList(
                    controller.executeRequest("select distinct name from sets"));
            sets.addAll(ResultSetConverter.columnToList(
                    controller.executeRequest("select number from exhibits where " +
                            "not number = any (select card_number from sets)")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label transferSetsLabel = new Label("Set:");
        ChoiceBox<String> setsChoice = new ChoiceBox<>();
        for (Object set : sets) {
            setsChoice.getItems().add((String) set);
        }
        setsChoice.setValue(transferObj.getSetNumber());
        pane.add(transferSetsLabel, 0, 5);
        pane.add(setsChoice, 1, 5);

        Button addBtn = new Button("Apply");
        Transfer finalTransferObj = transferObj;
        addBtn.setOnAction(event -> {
            Transfer transfer = new Transfer.Builder()
                    .withNumber(transferCode.getText())
                    .withAction(actionsChoice.getValue())
                    .withFundTakeFrom(fundTakeFromChoice.getValue())
                    .withFundPlacedIn(fundPlacedInChoice.getValue())
                    .withDate(Date.valueOf(transferDate.getValue()))
                    .withSetNumber(setsChoice.getValue())
                    .build();
            Request request = RequestConverter.internalTransferToUpdate(finalTransferObj, transfer);
            try {
                controller.executePreparedUpdate(request.getStatement(), request.getArgs());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        addBtn.setMinWidth(pane.getPrefWidth());
        pane.add(addBtn, 0, 6);
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMinWidth(pane.getPrefWidth());
        pane.add(cancelBtn, 1, 6);
        cancelBtn.setOnAction(event -> {
            stage.close();
        });

        stage.setScene(scene);
        stage.showAndWait();
    }

    private void editExternalTransferDialog(Controller controller){
        List<Transfer> cards = null;
        try {
            cards =
                    ResultSetConverter.toExternalTransfers(
                            controller.executePreparedRequest("select * from external_transfers"));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        List<String> codes = new ArrayList<>();
        for (Transfer transfer : cards) {
            codes.add(transfer.getNumber());
        }

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        choiceDialog.getItems().addAll(codes);
        choiceDialog.setSelectedItem(codes.get(0));
        choiceDialog.setTitle("Choose transfer");
        choiceDialog.setHeaderText("Please, choose the transfer code");
        Optional<String> result = choiceDialog.showAndWait();
        Transfer transferObj = new Transfer.Builder().build();
        if (result.isPresent()) {
            choiceDialog.close();
            for (Transfer card : cards) {
                if (card.getNumber().equals(result.get())) {
                    transferObj = card;
                    break;
                }
            }
        } else {
            choiceDialog.close();
            return;
        }
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, 425, 225);
        Stage stage = new Stage();
        Label transferCodeLabel = new Label("Code number:");
        TextField transferCode = new TextField(transferObj.getNumber());
        transferCode.setPromptText("Enter code here");
        pane.add(transferCodeLabel, 0, 0);
        pane.add(transferCode, 1, 0);

        Label actionsChoiceLabel = new Label("Actions");
        ChoiceBox<String> actionsChoice = new ChoiceBox<>();
        actionsChoice.getItems().add("Отправка на выставку");
        actionsChoice.getItems().add("Принятие с выставки");
        actionsChoice.setValue(transferObj.getAction());
        pane.add(actionsChoiceLabel, 0, 1);
        pane.add(actionsChoice, 1, 1);

        List<Object> expositions = null;
        try {
            expositions = ResultSetConverter.columnToList(
                    controller.executeRequest("select distinct number from expositions"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label transferExpositionLabel = new Label("Exposition:");
        ChoiceBox<String> expositionsChoice = new ChoiceBox<>();
        for (Object set : expositions) {
            expositionsChoice.getItems().add((String) set);
        }
        if (expositions.size()>0) {
            expositionsChoice.setValue(transferObj.getExpositionNumber());
        }
        pane.add(transferExpositionLabel, 0, 2);
        pane.add(expositionsChoice, 1, 2);

        Label transferDateLabel = new Label("Date:");
        DatePicker transferDate = new DatePicker(LocalDate.parse(transferObj.getDate().toString()));
        transferDate.setPromptText("Choose date");
        pane.add(transferDateLabel, 0, 3);
        pane.add(transferDate, 1, 3);
        List<Object> sets = null;
        try {
            sets = ResultSetConverter.columnToList(
                    controller.executeRequest("select distinct name from sets"));
            sets.addAll(ResultSetConverter.columnToList(
                    controller.executeRequest("select number from exhibits where " +
                            "not number = any (select card_number from sets)")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label transferSetsLabel = new Label("Date:");
        ChoiceBox<String> setsChoice = new ChoiceBox<>();
        for (Object set : sets) {
            setsChoice.getItems().add((String) set);
        }
        setsChoice.setValue(transferObj.getSetNumber());
        pane.add(transferSetsLabel, 0, 4);
        pane.add(setsChoice, 1, 4);

        Button addBtn = new Button("Add");
        Transfer finalTransferObj = transferObj;
        addBtn.setOnAction(event -> {
            Transfer transfer = new Transfer.Builder()
                    .withNumber(transferCode.getText())
                    .withAction(actionsChoice.getValue())
                    .withExpositionNumber(expositionsChoice.getValue())
                    .withDate(Date.valueOf(transferDate.getValue()))
                    .withSetNumber(setsChoice.getValue())
                    .build();
            Request request = RequestConverter.externalTransferToUpdate(finalTransferObj, transfer);
            try {
                controller.executePreparedUpdate(request.getStatement(), request.getArgs());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        addBtn.setMinWidth(pane.getPrefWidth());
        pane.add(addBtn, 0, 5);
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMinWidth(pane.getPrefWidth());
        pane.add(cancelBtn, 1, 5);
        cancelBtn.setOnAction(event -> {
            stage.close();
        });

        stage.setScene(scene);
        stage.showAndWait();
    }
}
