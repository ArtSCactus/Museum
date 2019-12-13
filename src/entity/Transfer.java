package entity;

import java.sql.Date;
import java.util.Objects;

public class Transfer {
    private String number;
    private String action;
    private Date startDate;
    private Date endDate;
    private String expositionNumber;
    private String fundTakeFrom;
    private String fundPlaceIn;

    public Transfer(String number, String action, Date startDate, Date endDate, String expositionNumber,
                    String fundTakeFrom, String fundPlaceIn) {
        this.number = number;
        this.action = action;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expositionNumber = expositionNumber;
        this.fundTakeFrom = fundTakeFrom;
        this.fundPlaceIn = fundPlaceIn;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getFundPlaceIn() {
        return fundPlaceIn;
    }

    public void setFundPlaceIn(String fundPlaceIn) {
        this.fundPlaceIn = fundPlaceIn;
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

        public Builder withStartDate(Date startDate) {
            newTransfer.startDate = startDate;
            return this;
        }

        public Builder withEndDate(Date endDate){
            newTransfer.endDate = endDate;
            return this;
        }

        public Builder withExpositionNumber(String number) {
            newTransfer.expositionNumber = number;
            return this;
        }

        public Builder withFundTakeFrom(String fundNumber){
            newTransfer.fundTakeFrom =fundNumber;
            return  this;
        }

        public Builder withFundPlacedIn(String fundNumber){
            newTransfer.fundPlaceIn = fundNumber;
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
                Objects.equals(getStartDate(), transfer.getStartDate()) &&
                Objects.equals(getEndDate(), transfer.getEndDate()) &&
                Objects.equals(getExpositionNumber(), transfer.getExpositionNumber()) &&
                Objects.equals(getFundTakeFrom(), transfer.getFundTakeFrom()) &&
                Objects.equals(getFundPlaceIn(), transfer.getFundPlaceIn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getAction(), getStartDate(), getEndDate(), getExpositionNumber(), getFundTakeFrom(), getFundPlaceIn());
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "number='" + number + '\'' +
                ", action='" + action + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", expositionNumber='" + expositionNumber + '\'' +
                ", fundTakeFrom='" + fundTakeFrom + '\'' +
                ", fundPlaceIn='" + fundPlaceIn + '\'' +
                '}';
    }
}
