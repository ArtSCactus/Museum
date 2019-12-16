package entity;

import java.util.Objects;

public class Set {
    private String name;
    private String cardNumber;
    private String annotation;

    public Set(String name, String cardNumber, String annotation) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.annotation = annotation;
    }

    private Set() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public static class Builder{
        private Set newSet;
        public Builder(){
            newSet =  new Set();
        }
        public Builder withName(String name){
            newSet.name = name;
            return this;
        }

        public Builder withCardNumber(String cardNumber){
            newSet.cardNumber = cardNumber;
            return this;
        }

        public Builder withAnnotation(String annotation){
            newSet.annotation = annotation;
            return this;
        }

        public Set build(){
            return newSet;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Set)) return false;
        Set set = (Set) o;
        return Objects.equals(getName(), set.getName()) &&
                Objects.equals(getCardNumber(), set.getCardNumber()) &&
                Objects.equals(getAnnotation(), set.getAnnotation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCardNumber(), getAnnotation());
    }

    @Override
    public String toString() {
        return "Set{" +
                "name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
