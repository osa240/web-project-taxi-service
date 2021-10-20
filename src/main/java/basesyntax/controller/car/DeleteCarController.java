package basesyntax.controller.car;

import basesyntax.lib.Injector;
import basesyntax.service.CarService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCarController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteCarController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("servlet for url - '/cars/delete' was called...");
        carService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/cars/all");
    }
}
