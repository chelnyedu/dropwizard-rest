package com.taxtelecom.chelnyedu.dropwizard.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sagel on 06.07.17.
 */
public class Contact {
	@JsonProperty
    private final int id;
	@JsonProperty
    private final String firstName;
	@JsonProperty
    private final String lastName;
	@JsonProperty
    private final String phone;

    public Contact(){
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
    }

    public Contact(int id, String firstName, String lastName, String phone){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Contact other =(Contact)obj;
        if(id!=other.getId()) {
            return false;
        }
        if(!firstName.equals(other.getFirstName())) {
            return false;
        }
        if(!lastName.equals(other.getLastName())) {
            return false;
        }
        if(!phone.equals(other.getPhone())) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
    	int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
