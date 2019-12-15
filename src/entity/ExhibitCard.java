package entity;

import java.sql.Date;
import java.util.Objects;

public class ExhibitCard {
    private String fund;
    private String name;
    private String id;
    private Date creationDate;
    private String dateAccuracy;
    private String authorCode;

    public ExhibitCard(String fund, String name, String id, Date creationDate, String dateAccuracy, String authorCode) {
        this.fund = fund;
        this.name = name;
        this.id = id;
        this.creationDate = creationDate;
        this.dateAccuracy = dateAccuracy;
        this.authorCode = authorCode;
    }

    private ExhibitCard() {
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;

    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDateAccuracy() {
        return dateAccuracy;
    }

    public void setDateAccuracy(String dateAccuracy) {
        this.dateAccuracy = dateAccuracy;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public static class Builder {
        private ExhibitCard newCard;

        public Builder() {
            this.newCard = new ExhibitCard();
        }

        public Builder withFund(String fund) {
            newCard.fund = fund;
            return this;
        }

        public Builder withName(String name) {
            newCard.name = name;
            return this;
        }

        public Builder withID(String id) {
            newCard.id = id;
            return this;
        }

        public Builder withCreationDate(Date date) {
            newCard.creationDate = date;
            return this;
        }

        public Builder withDateAccuracy(String dateAccuracy) {
            newCard.dateAccuracy = dateAccuracy;
            return this;
        }

        public Builder withAuthorCode(String authorCode){
            newCard.authorCode=authorCode;
            return this;
        }

        public ExhibitCard build() {
            return newCard;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExhibitCard)) return false;
        ExhibitCard that = (ExhibitCard) o;
        return Objects.equals(getFund(), that.getFund()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreationDate(), that.getCreationDate()) &&
                Objects.equals(getDateAccuracy(), that.getDateAccuracy()) &&
                Objects.equals(getAuthorCode(), that.getAuthorCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFund(), getName(), getId(), getCreationDate(), getDateAccuracy(), getAuthorCode());
    }

    @Override
    public String toString() {
        return "ExhibitCard{" +
                "fund='" + fund + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", dateAccuracy='" + dateAccuracy + '\'' +
                ", authorCode='" + authorCode + '\'' +
                '}';
    }
}
