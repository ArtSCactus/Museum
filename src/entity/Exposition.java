package entity;

import java.sql.Date;
import java.util.Objects;

public class Exposition {
    private String number;
    private String orgName;
    private String phoneNumber;
    private String contactPerson;
    private String address;
    private Date startDate;
    private Date endDate;

    public Exposition(String number, String orgName, String phoneNumber,
                      String contactPerson, String address, Date startDate, Date endDate) {
        this.number = number;
        this.orgName = orgName;
        this.phoneNumber = phoneNumber;
        this.contactPerson = contactPerson;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Exposition() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public static class Builder {
        private Exposition newExposition;

        public Builder() {
            newExposition = new Exposition();
        }

        public Builder withOrgName(String name) {
            newExposition.orgName = name;
            return this;
        }

        public Builder withAddress(String address) {
            newExposition.address = address;
            return this;
        }

        public Builder withContactPerson(String contactPerson) {
            newExposition.contactPerson = contactPerson;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            newExposition.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withNumber(String number) {
            newExposition.number = number;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            newExposition.startDate = startDate;
            return this;
        }

        public Builder withEndDate(Date endDate) {
            newExposition.endDate = endDate;
            return this;
        }

        public Exposition build() {
            return newExposition;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exposition)) return false;
        Exposition that = (Exposition) o;
        return Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getOrgName(), that.getOrgName()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getContactPerson(), that.getContactPerson()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getOrgName(),
                getPhoneNumber(), getContactPerson(), getAddress(), getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        return "Expositions{" +
                "number='" + number + '\'' +
                ", name='" + orgName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", address='" + address + '\'' +
                ", dateStart=" + startDate +
                ", dateEnd=" + endDate +
                '}';
    }
}
