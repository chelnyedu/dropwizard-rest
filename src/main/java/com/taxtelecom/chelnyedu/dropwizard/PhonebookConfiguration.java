package com.taxtelecom.chelnyedu.dropwizard;

import javax.validation.constraints.Max;
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
	
	public String getAdditionalMessage() {
		return additionalMessage;
	}
	
	public String getMessage() {
		return message;
	
	}



	@JsonProperty("database")
	private DataSourceFactory database = new DataSourceFactory();
	public DataSourceFactory getDataSourceFactory(){
		String postgres = System.getenv("DATABASE_URL");
		DatabaseConfiguration databaseConfiguration = DatabaseConfig.create(postgres);
		database = databaseConfiguration.getDataSourceFactory(null);
		return database;
	}
	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory){
		this.database = dataSourceFactory;
	}
	
	public int getMessageRepetitions() {
		return messageRepetitions;
	}
}
