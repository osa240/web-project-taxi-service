package basesyntax.controller.manufacturer;

import basesyntax.lib.Injector;
import basesyntax.model.Manufacturer;
import basesyntax.service.ManufacturerService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllManufacturersController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetAllManufacturersController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("servlet for url - '/manufacturers/' was called...");
        List<Manufacturer> allManufacturers = manufacturerService.getAll();
        req.setAttribute("manufacturers", allManufacturers);
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/all.jsp").forward(req, resp);
    }
}
