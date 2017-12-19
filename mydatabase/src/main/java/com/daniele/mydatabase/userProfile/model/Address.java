package com.daniele.mydatabase.userProfile.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "STREET_NAME")
    private String streetName;
    @Column(name = "STREET_NUMBER")
    private int streetNumber;
    private String city;

    private Address() {
    }

    public Address(AddressBuilder addressBuilder) {
        this.streetNumber = addressBuilder.address.streetNumber;
        this.city = addressBuilder.address.city;
        this.streetName = addressBuilder.address.streetName;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public static class AddressBuilder {

        protected Address address;

        public static AddressBuilder forCreation() {
            return new AddressBuilder(new Address());
        }

        private AddressBuilder(Address address) {
            this.address = address;
        }

        public AddressBuilder withStreetName(String streetName) {
            this.address.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(int streetNumber) {
            this.address.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.address.city = city;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
