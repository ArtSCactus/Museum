package entity;

import java.sql.Date;
import java.util.Objects;

public class Author {
    private String number;
    private String firstName;
    private String secondName;
    private String thirdName;
    private Date birthday;
    private String country;

    public Author(String number, String firstName, String secondName, String thirdName, Date birthday, String country) {
        this.number = number;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.birthday = birthday;
        this.country = country;
    }

    private Author() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Builder {
        private Author newAuthor;

        public Builder() {
            newAuthor = new Author();
        }

        public Builder withNumber(String number) {
            newAuthor.number = number;
            return this;
        }

        public Builder withFirstName(String firstName) {
            newAuthor.firstName = firstName;
            return this;
        }

        public Builder withSecondName(String secondName) {
            newAuthor.secondName = secondName;
            return this;
        }

        public Builder withThirdName(String thirdName) {
            newAuthor.thirdName = thirdName;
            return this;
        }

        public Builder withBirthday(Date birthday) {
            newAuthor.birthday = birthday;
            return this;
        }

        public Builder withCountry(String country) {
            newAuthor.country = country;
            return this;
        }

        public Author build() {
            return newAuthor;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getFirstName().equals(author.getFirstName()) &&
                getSecondName().equals(author.getSecondName()) &&
                getThirdName().equals(author.getThirdName()) &&
                getBirthday().equals(author.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getSecondName(), getThirdName(), getBirthday());
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", birthdate=" + birthday +
                '}';
    }
}
