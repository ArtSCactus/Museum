package view.dialogs;

import controller.Controller;
import entity.Transfer;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.ResultSetConverter;
import view.TableComponent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SearchDialog {

    public void showSearchExhibitDialog(Controller controller) {
        TextInputDialog nameChooser = new TextInputDialog();
        nameChooser.setTitle("Choose set or exhibit name");
        nameChooser.setHeaderText("Enter exhibit or set name");
        nameChooser.setContentText("Please, enter set or exhibit name here");
        Optional<String> dialogResult = nameChooser.showAndWait();
        if (dialogResult.isPresent()) {
            try {
                String date = showDatePickerDialog();
                if (date == null) {
                    return;
                }
                List<Transfer> transfers =
                        ResultSetConverter.toCommonTransfers(controller.executePreparedRequest(
                                "select number, action, date, set_number\n" +
                                        "from transfers group by number, action, transfers.date, set_number" +
                                        " having (max(transfers.date)<'" + date + "') " +
                                        "and set_number='" + dialogResult.get() + "'"));
                showTableWithResults(transfers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            nameChooser.close();
        }
    }

    private void showTableWithResults(List<Transfer> transfers) {
        BorderPane pane = new BorderPane();
        Stage stage = new Stage();
        Scene scene = new Scene(pane, 500, 500);
        TableComponent table = new TableComponent();
        table.showCommonTransfers(FXCollections.observableArrayList(transfers));
        pane.setCenter(table.getTable());
        stage.setScene(scene);
        stage.showAndWait();
    }

    private String showDatePickerDialog() {
        DialogPane pane = new DialogPane();
        Scene scene = new Scene(pane, 200, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        DatePicker picker = new DatePicker(LocalDate.now());
        Button applyBtn = new Button("Apply");
        final String[] result = new String[1];
        applyBtn.setOnAction(event -> {
            result[0] = picker.getValue().toString();
            stage.close();
        });
        pane.setHeaderText("Choose date");
        pane.setContent(applyBtn);
        pane.setHeader(picker);
        stage.showAndWait();
        return result[0];
    }
}
