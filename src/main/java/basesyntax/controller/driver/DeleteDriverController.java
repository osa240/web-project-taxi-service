package basesyntax.controller.driver;

import basesyntax.lib.Injector;
import basesyntax.service.DriverService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteDriverController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteDriverController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("servlet for url - '/drivers/delete' was called..");
        driverService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/drivers/all");
    }
}
