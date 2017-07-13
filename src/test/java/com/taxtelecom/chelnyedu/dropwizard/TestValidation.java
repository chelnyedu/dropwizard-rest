package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import org.junit.Test;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

/**
 * Created by user on 13.07.17.
 */
public class TestValidation {
    private Contact contact = new Contact(1, "John", "Doe", "+123456789");
    private Validator validator;


    @Test
    public void validationContact(){

    }

}
