package com.taxtelecom.chelnyedu.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;

/**
 * Created by sagel on 05.07.17.
 */
public class PhonebookConfiguration extends Configuration{
    @JsonProperty
    @NotEmpty
    private String message;

    @JsonProperty
    @Max(10)
    private int messageRepetitions;

    @JsonProperty
    private String additionalMessage = "This is optional";

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public String getMessage(){
        return message;
    }

    public int getMessageRepetitions(){
        return messageRepetitions;
    }
}
