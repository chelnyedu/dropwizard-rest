package com.taxtelecom.chelnyedu.dropwizard.PhonebookAuthenticator;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;


/**
 * Created by user on 17.07.17.
 */
public class PhonebookAuthenticator implements Authenticator<BasicCredentials, Boolean>{
    @Override
    public Optional<Boolean> authenticate(BasicCredentials bc) throws AuthenticationException {
        if(bc.getUsername().equals("john_doe")&& bc.getPassword().equals("secret")){
            return Optional.of(true);
        }
        return Optional.absent();
    }
}
