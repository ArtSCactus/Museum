package view.dialogs;

import controller.Controller;
import entity.ExhibitCard;
import entity.Request;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class DeleteDialog {
    public static void showDeleteDialog(Controller controller) {
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
        ExhibitCard foundCard = null;
        if (result.isPresent()) {
            choiceDialog.close();
            for (ExhibitCard card : cards) {
                if (card.getId().equals(result.get())) {
                    foundCard = card;
                    break;
                }
            }
        } else {
            choiceDialog.close();
        }
        Request request = RequestConverter.exhibitToDelete(foundCard);
        try {
            controller.executePreparedStatement(request.getStatement(), request.getArgs());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
