package com.taxtelecom.chelnyedu.dropwizard.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * Interface for database query in table users
 */
public interface UserDAO {
	@SqlQuery("select count(*) from users where username = :username and password = :password")
	int getUser(@Bind("username") String username,
			@Bind("password") String password);
}
