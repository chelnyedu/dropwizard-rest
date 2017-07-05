package com.taxtelecom.chelnyedu.dropwizard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration>{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(App.class);
	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public void initialize(Bootstrap<Configuration> arg0) {}

	@Override
	public void run(Configuration arg0, Environment arg1) throws Exception {
			LOGGER.info("Method App#run() called");
			System.out.println( "Hello world, by Dropwizard!" );
	}

}
