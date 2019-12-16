package entity;

import java.sql.Date;
import java.util.Objects;

public class Transfer {
    private String number;
    private String action;
    private Date date;
    private String expositionNumber;
    private String fundTakeFrom;
    private String fundPlacedIn;
    private String setNumber;

    public Transfer(String number, String action, Date date, String expositionNumber,
                    String fundTakeFrom, String fundPlacedIn, String setNumber) {
        this.number = number;
        this.action = action;
        this.date = date;
        this.expositionNumber = expositionNumber;
        this.fundTakeFrom = fundTakeFrom;
        this.fundPlacedIn = fundPlacedIn;
        this.setNumber = setNumber;
    }

    private Transfer() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExpositionNumber() {
        return expositionNumber;
    }

    public void setExpositionNumber(String expositionNumber) {
        this.expositionNumber = expositionNumber;
    }

    public String getFundTakeFrom() {
        return fundTakeFrom;
    }

    public void setFundTakeFrom(String fundTakeFrom) {
        this.fundTakeFrom = fundTakeFrom;
    }

    public String getFundPlacedIn() {
        return fundPlacedIn;
    }

    public void setFundPlacedIn(String fundPlacedIn) {
        this.fundPlacedIn = fundPlacedIn;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public static class Builder {
        private Transfer newTransfer;

        public Builder() {
            newTransfer = new Transfer();
        }

        public Builder withNumber(String number) {
            newTransfer.number = number;
            return this;
        }

        public Builder withAction(String action) {
            newTransfer.action = action;
            return this;
        }

        public Builder withDate(Date date) {
            newTransfer.date = date;
            return this;
        }


        public Builder withExpositionNumber(String number) {
            newTransfer.expositionNumber = number;
            return this;
        }

        public Builder withFundTakeFrom(String fundNumber) {
            newTransfer.fundTakeFrom = fundNumber;
            return this;
        }

        public Builder withFundPlacedIn(String fundNumber) {
            newTransfer.fundPlacedIn = fundNumber;
            return this;
        }

        public Builder withSetNumber(String setNumber){
            newTransfer.setNumber = setNumber;
            return this;
        }

        public Transfer build() {
            return newTransfer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transfer)) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(getNumber(), transfer.getNumber()) &&
                Objects.equals(getAction(), transfer.getAction()) &&
                Objects.equals(getDate(), transfer.getDate()) &&
                Objects.equals(getExpositionNumber(), transfer.getExpositionNumber()) &&
                Objects.equals(getFundTakeFrom(), transfer.getFundTakeFrom()) &&
                Objects.equals(getFundPlacedIn(), transfer.getFundPlacedIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAction(), getDate(), getExpositionNumber(), getFundTakeFrom(), getFundPlacedIn());
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "number='" + number + '\'' +
                ", action='" + action + '\'' +
                ", startDate=" + date +
                ", expositionNumber='" + expositionNumber + '\'' +
                ", fundTakeFrom='" + fundTakeFrom + '\'' +
                ", fundPlaceIn='" + fundPlacedIn + '\'' +
                '}';
    }
}
