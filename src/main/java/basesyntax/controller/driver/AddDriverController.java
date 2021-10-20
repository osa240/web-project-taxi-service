package basesyntax.controller.driver;

import basesyntax.lib.Injector;
import basesyntax.model.Driver;
import basesyntax.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDriverController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddDriverController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("servlet for url - '/drivers/add' was called...");
        req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        String licenseNumber = req.getParameter("license_number");
        Driver driver = new Driver(name, licenseNumber);
        driver.setLogin(login);
        if (!password.equals(repeatPassword)) {
            req.setAttribute("error", "Password not matches!");
            req.getRequestDispatcher("/WEB-INF/views/drivers/add.jsp").forward(req, resp);
            logger.error("servlet for url - '/drivers/add' has an error in repeat password");
            return;
        }
        driver.setPassword(password);
        driverService.create(driver);
        resp.sendRedirect("/drivers/add");
    }
}
