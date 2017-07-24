package com.taxtelecom.chelnyedu.dropwizard.representations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dropwizard.validation.ValidationMethod;
import org.hibernate.validator.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for representation of contacts. It includes id, firstName,
 * lastName, phone, mail, comment.
 * Using JsonValidation
 */
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
    @Length(max=250)
    private final String comment;

    @JsonIgnore
    @ValidationMethod(message="John Doe is not a valid person!")
    public boolean isValidPerson() {
           	if (firstName.equals("John") && lastName.equals("Doe")) {
               		return false;
            }
            return true;
    }

    public Contact(){
        this.id = 0;
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
        this.comment = null;
        this.mail = null;
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

    public String getMail(){ return mail; }

    public String getComment(){ return comment; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (mail != null ? !mail.equals(contact.mail) : contact.mail != null) return false;
        return comment != null ? comment.equals(contact.comment) : contact.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
