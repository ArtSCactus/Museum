package entity;

import java.sql.Date;
import java.util.Objects;

public class ExhibitCard {
    private String fund;
    private String name;
    private long id;
    private Date creationDate;
    private String dateAccuracy;

    public ExhibitCard(String fund, String name, long id, Date creationDate, String dateAccuracy) {
        this.fund = fund;
        this.name = name;
        this.id = id;
        this.creationDate = creationDate;
        this.dateAccuracy = dateAccuracy;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        public Builder withID(long id) {
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

        public ExhibitCard build() {
            return newCard;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExhibitCard)) return false;
        ExhibitCard that = (ExhibitCard) o;
        return getId() == that.getId() &&
                getFund().equals(that.getFund()) &&
                getName().equals(that.getName()) &&
                getCreationDate().equals(that.getCreationDate()) &&
                getDateAccuracy().equals(that.getDateAccuracy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFund(), getName(), getId(), getCreationDate(), getDateAccuracy());
    }

    @Override
    public String toString() {
        return "ExhibitCard{" +
                "fund='" + fund + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", creationDate=" + creationDate +
                ", isDateAccurate=" + dateAccuracy +
                '}';
    }
}
