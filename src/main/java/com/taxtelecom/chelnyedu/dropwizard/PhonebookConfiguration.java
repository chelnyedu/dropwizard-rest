package com.taxtelecom.chelnyedu.dropwizard;

import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

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
	
	public int getMessageRepetitions() {
		return messageRepetitions;
	}
	
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();
	
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
}
