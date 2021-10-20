package basesyntax.controller.manufacturer;

import basesyntax.lib.Injector;
import basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteManufacturerController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DeleteManufacturerController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        logger.info("servlet for url - '/manufacturers/delete' was called...");
        manufacturerService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/manufacturers/all");
    }
}
