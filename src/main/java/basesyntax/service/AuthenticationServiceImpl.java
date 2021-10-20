package basesyntax.service;

import basesyntax.exception.AuthenticationException;
import basesyntax.lib.Inject;
import basesyntax.lib.Service;
import basesyntax.model.Driver;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isEmpty() || !driver.get().getPassword().equals(password)) {
            throw new AuthenticationException("Login or password are incorrect!");
        }
        return driver.get();
    }
}
