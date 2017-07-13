package com.taxtelecom.chelnyedu.dropwizard.representations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.ValidationMethod;

public class Contact {
    @JsonProperty
    private final int id;
    @JsonProperty
    @NotBlank
    @Length(min=2, max=255)
    private final String firstName;
    @JsonProperty
    @NotBlank
    @Length(min=2, max=255)
    private final String lastName;
    @JsonProperty
    @NotBlank
    @Length(min=2, max=30)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        return phone != null ? phone.equals(contact.phone) : contact.phone == null;
    }
    @JsonIgnore
    @ValidationMethod(message="John Doe is not a valid person!")
    public boolean isValidPerson() {
    	if (firstName.equals("John") && lastName.equals("Doe")) {
    		return false;
    	} else {
    		return true;
    	}
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
