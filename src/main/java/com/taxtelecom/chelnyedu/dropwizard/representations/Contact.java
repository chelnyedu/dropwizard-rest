package com.taxtelecom.chelnyedu.dropwizard.representations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.validation.ValidationMethod;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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
    @JsonProperty
    @NotBlank
    @Length(min=2, max=30)
    private final String mail;
    @JsonProperty
    @NotBlank
    @Length( max=50)
    private final String comment;

    @JsonIgnore
    @ValidationMethod(message="John Doe is not a valid person!")
    public boolean isValidPerson() {
           	if (firstName.equals("John") && lastName.equals("Doe")) {
               		return false;
               	} else {
               		return true;
            }
    }

    public Contact(){
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
        this.mail = null;
        this.comment = null;
    }

    public Contact(int id, String firstName, String lastName, String phone, String mail, String comment){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mail = mail;
        this.comment = comment;
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

    public String getMail() {
        return mail;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(mail, contact.mail) &&
                Objects.equals(comment, contact.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, mail, comment);
    }
}
