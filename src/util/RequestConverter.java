package util;

import entity.ExhibitCard;
import entity.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestConverter {
    public static Request exhibitToInsert(ExhibitCard obj){
        String statement =
                "Insert into exhibits (number, name, creation_date, date_accuracy, author) values(?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(obj.getId(), obj.getName(),
                obj.getCreationDate(),
                obj.getDateAccuracy(), obj.getAuthorCode()));
        return new Request(statement, args);
    }

    public static Request exhibitToUpdate(ExhibitCard oldValue, ExhibitCard newValue){
        String statement =
                "update exhibits set number = ?, name = ?, creation_date = ?, date_accuracy = ?, author = ? where number = ? and name = ? and creation_date = ? and date_accuracy = ? and author = ?";
        List<Object> args = new ArrayList<>();
        args.addAll(Arrays.asList(newValue.getId(), newValue.getName(),
                newValue.getCreationDate(),
                newValue.getDateAccuracy(), newValue.getAuthorCode()));
        args.addAll(Arrays.asList(oldValue.getId(), oldValue.getName(),
                oldValue.getCreationDate(),
                oldValue.getDateAccuracy(), oldValue.getAuthorCode()));
        return new Request(statement, args);
    }
}
