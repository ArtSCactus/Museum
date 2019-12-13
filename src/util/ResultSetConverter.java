package util;

import entity.Author;
import entity.ExhibitCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {
    public static List<ExhibitCard> toExhibitCards(ResultSet resultSet) throws SQLException {
        List<ExhibitCard> cards = new ArrayList<>();
        while(resultSet.next()){
         ExhibitCard currentCard = new ExhibitCard.Builder()
                 .withID(resultSet.getString(1))
                 .withName(resultSet.getString(2))
                 .withCreationDate(resultSet.getDate(3))
                 .withDateAccuracy(resultSet.getString(4))
                 .build();
         cards.add(currentCard);
        }
        return cards;
    }

    public static List<Author> toAuthors(ResultSet resultSet) throws SQLException {
        List<Author> authors = new ArrayList<>();
        while(resultSet.next()){
            Author currentAuthor = new Author.Builder()
                    .withNumber(resultSet.getString(1))
                    .withFirstName(resultSet.getString(2))
                    .withSecondName(resultSet.getString(3))
                    .withThirdName(resultSet.getString(4))
                    .withBirthday(resultSet.getDate(5))
                    .withCountry(resultSet.getString(6))
                    .build();

            authors.add(currentAuthor);
        }
        return authors;
    }

}
