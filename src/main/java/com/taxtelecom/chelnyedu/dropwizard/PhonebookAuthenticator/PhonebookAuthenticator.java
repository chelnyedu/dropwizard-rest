package com.taxtelecom.chelnyedu.dropwizard.PhonebookAuthenticator;
import com.google.common.base.Optional;
import com.taxtelecom.chelnyedu.dropwizard.dao.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.skife.jdbi.v2.DBI;


/**
 * Created by user on 17.07.17.
 */
public class PhonebookAuthenticator implements Authenticator<BasicCredentials, Boolean>{
    private final UserDAO userDao;

    public PhonebookAuthenticator(DBI jdbi) {
        userDao = jdbi.onDemand(UserDAO.class);
    }

    @Override
    public Optional<Boolean> authenticate(BasicCredentials bc) throws AuthenticationException {
        boolean validUser = (userDao.getUser(bc.getUsername(), bc.getPassword())==1);
        if (validUser){
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
