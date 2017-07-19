package com.taxtelecom.chelnyedu.dropwizard;

import com.google.common.base.Optional;
import com.taxtelecom.chelnyedu.dropwizard.dao.UserDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;

public class PhonebookAuthenticator implements Authenticator<BasicCredentials, Boolean>{
    UserDAO userDao;
	public PhonebookAuthenticator(UserDAO userDao) {
		this.userDao = userDao;
	}
	public Optional<Boolean> authenticate(BasicCredentials c) throws AuthenticationException {
		boolean validUser = (userDao.getUser(c.getUsername(),
				c.getPassword()) == 1);
		if (validUser) {
			return Optional.of(true);
		}
		return Optional.absent();
	}
}
