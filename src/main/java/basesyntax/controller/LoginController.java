package basesyntax.controller;

import basesyntax.exception.AuthenticationException;
import basesyntax.lib.Injector;
import basesyntax.model.Driver;
import basesyntax.service.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private static final String DRIVER_ID = "driver_id";
    private static final String NAME = "name";
    private final AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("servlet for url - '/login' was called...");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Driver driver = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute(DRIVER_ID, driver.getId());
            session.setAttribute(NAME, driver.getName());
            resp.sendRedirect("/");
        } catch (AuthenticationException e) {
            req.setAttribute("error", e.getMessage());
            logger.error("servlet for url - '/login' has an error with login or password..");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
