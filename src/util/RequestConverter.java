package util;

import entity.ExhibitCard;
import entity.Request;
import entity.Transfer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestConverter {
    public static Request exhibitToInsert(ExhibitCard obj) {
        String statement =
                "Insert into exhibits (number, name, creation_date, date_accuracy, author)" +
                        " values(?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(obj.getId(), obj.getName(),
                obj.getCreationDate(),
                obj.getDateAccuracy(), obj.getAuthorCode()));
        return new Request(statement, args);
    }

    public static Request exhibitToUpdate(ExhibitCard oldValue, ExhibitCard newValue) {
        String statement =
                "update exhibits set number = ?, name = ?, creation_date = ?, " +
                        "date_accuracy = ?, author = ? where number = ? and name = ? and creation_date = ? " +
                        "and date_accuracy = ? and author = ?";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(newValue.getId(), newValue.getName(),
                newValue.getCreationDate(),
                newValue.getDateAccuracy(), newValue.getAuthorCode()));
        args.addAll(Arrays.asList(oldValue.getId(), oldValue.getName(),
                oldValue.getCreationDate(),
                oldValue.getDateAccuracy(), oldValue.getAuthorCode()));
        return new Request(statement, args);
    }

    public static Request exhibitToDelete(ExhibitCard obj) {
        String statement =
                "delete from exhibits " +
                        "where number = ? and name = ? and creation_date = ? and date_accuracy = ? and author = ?";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(obj.getId(), obj.getName(),
                obj.getCreationDate(),
                obj.getDateAccuracy(), obj.getAuthorCode()));
        return new Request(statement, args);
    }

    public static Request externalTransferToInsert(Transfer obj) {
        String statement = "Insert into external_transfers (number, action, exposition_number, date, set_number) " +
                "values (?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(obj.getNumber(), obj.getAction(),
                obj.getExpositionNumber(), obj.getDate(), obj.getSetNumber()));
        return new Request(statement, args);
    }

    public static Request requestForColumn(String tableName, String columnName) {
        String statement = "select distinct ? from ?";
        List<Object> args = new ArrayList<>();
        args.add(tableName);
        args.add(columnName);
        return new Request(statement, args);
    }

    public static Request internalTransferToInsert(Transfer obj){
        String statement = "Insert into internal_transfers (number, action, fund_taken_from, fund_placed_in, date, set_number) " +
                "values (?, ?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(obj.getNumber(), obj.getAction(),
                obj.getFundTakeFrom(),obj.getFundPlacedIn(), obj.getDate(), obj.getSetNumber()));
        return new Request(statement, args);
    }

    public static Request internalTransferToUpdate(Transfer oldObj, Transfer newObj){
        String statement = "update internal_transfers set number = ?, action = ?, " +
                "fund_taken_from = ?, fund_placed_in = ?, date = ?, set_number = ? " +
                "where number = ? and action = ? and fund_taken_from = ? " +
                "and fund_placed_in = ? and date = ? and set_number = ?";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(newObj.getNumber(), newObj.getAction(),
                newObj.getFundTakeFrom(),newObj.getFundPlacedIn(), newObj.getDate(), newObj.getSetNumber()));
        args.addAll(Arrays.asList(oldObj.getNumber(), oldObj.getAction(),
                oldObj.getFundTakeFrom(),oldObj.getFundPlacedIn(), oldObj.getDate(), oldObj.getSetNumber()));
        return new Request(statement, args);
    }

    public static Request externalTransferToUpdate(Transfer oldObj, Transfer newObj){
        String statement = "update external_transfers set number = ?, action = ?, " +
                "exposition_number = ?, date = ?, set_number = ? " +
                "where number = ? and action = ? and exposition_number = ? and date = ? and set_number = ?";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(newObj.getNumber(), newObj.getAction(),
                newObj.getExpositionNumber(), newObj.getDate(), newObj.getSetNumber()));
        args.addAll(Arrays.asList(oldObj.getNumber(), oldObj.getAction(),
                oldObj.getExpositionNumber(), oldObj.getDate(), oldObj.getSetNumber()));
        return new Request(statement, args);
    }
}
