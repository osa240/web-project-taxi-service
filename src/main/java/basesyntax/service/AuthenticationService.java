package basesyntax.service;

import basesyntax.exception.AuthenticationException;
import basesyntax.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
