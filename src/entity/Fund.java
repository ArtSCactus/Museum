package entity;

import java.util.Objects;

public class Fund {
    private String name;
    private String cardOrSetNumber;

    public Fund(String name, String cardOrSetNumber) {
        this.name = name;
        this.cardOrSetNumber = cardOrSetNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardOrSetNumber() {
        return cardOrSetNumber;
    }

    public void setCardOrSetNumber(String cardOrSetNumber) {
        this.cardOrSetNumber = cardOrSetNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fund)) return false;
        Fund fund = (Fund) o;
        return getName().equals(fund.getName()) &&
                getCardOrSetNumber().equals(fund.getCardOrSetNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCardOrSetNumber());
    }

    @Override
    public String toString() {
        return "Fund{" +
                "name='" + name + '\'' +
                ", cardOrSetNumber='" + cardOrSetNumber + '\'' +
                '}';
    }
}
