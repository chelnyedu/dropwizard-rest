package com.taxtelecom.chelnyedu.dropwizard;

import static org.mockito.Mockito.*;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import com.google.common.base.Optional;
import com.taxtelecom.chelnyedu.dropwizard.dao.UserDAO;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;

public class PhonebookAuthenticatorTest {
	UserDAO dao = mock(UserDAO.class);
	BasicCredentials c = mock(BasicCredentials.class);
	PhonebookAuthenticator auth = new PhonebookAuthenticator(dao);
	Optional<Boolean> opt;
	Optional<Boolean> option;
	@Test
	public void optOfTrueTest() throws AuthenticationException {
		when(dao.getUser("a", "a")).thenReturn(1);
		when(c.getUsername()).thenReturn("a");
		when(c.getPassword()).thenReturn("a");
		opt = Optional.of(true);
		option = auth.authenticate(c);
		assertThat(option).isEqualTo(opt);
		
	}
	@Test
	public void optOfAbsebtTest() throws AuthenticationException {
		when(dao.getUser("b", "b")).thenReturn(0);
		when(c.getUsername()).thenReturn("b");
		when(c.getPassword()).thenReturn("b");
		option = auth.authenticate(c);
		opt = Optional.absent();
		assertThat(option).isEqualTo(opt);
	}
}
