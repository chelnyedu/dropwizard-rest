package com.taxtelecom.chelnyedu.dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
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

	@Valid
    @NotNull
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
    	String url = System.getenv("DATABASE_URL");
		DatabaseConfiguration dataBaseConfiguration = DataBaseConfiguration.create(url);
		database = dataBaseConfiguration.getDataSourceFactory(null);
		return database;
	}

	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.database = dataSourceFactory;
	}
	
	public String getAdditionalMessage() {
		return additionalMessage;
	}
	
	public String getMessage() {
		return message;
	
	}
	
	public int getMessageRepetitions() {
		return messageRepetitions;
	}
}
