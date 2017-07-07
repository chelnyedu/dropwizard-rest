package com.taxtelecom.chelnyedu.dropwizard;

import javax.validation.constraints.Max;

import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class PhonebookConfiguration extends Configuration {
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
	
	public String getMessage() {
		return message;
	
	}

	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	public DataSourceFactory getDataSourceFactory(){
		return database;
	}
	
	public int getMessageRepetitions() {
		return messageRepetitions;
	}
}
