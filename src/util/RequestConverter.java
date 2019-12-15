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
}
