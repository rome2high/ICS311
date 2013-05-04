package com.abc.bank.dto;

import java.io.*;

import com.programix.util.*;

public class Address implements Serializable {
    private String street;
    private String city;
    private State state;
    private String zip;
    
    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        Address other = (Address) obj;
        return ObjectTools.isSame(street, other.street) &&
               ObjectTools.isSame(city, other.city) &&
               ObjectTools.isSame(state, other.state) &&
               ObjectTools.isSame(zip, other.zip);
    }

    public int hashCode() {
        return street != null ? street.hashCode() : 0;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getName() + "[");
        sb.append("street=" + street);
        sb.append(", city=" + city);
        sb.append(", state=" + state);
        sb.append(", zip=" + zip);
        sb.append("]");
        return sb.toString();
    }
}
