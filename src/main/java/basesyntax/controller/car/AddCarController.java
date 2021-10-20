package basesyntax.controller.car;

import basesyntax.lib.Injector;
import basesyntax.model.Car;
import basesyntax.model.Manufacturer;
import basesyntax.service.CarService;
import basesyntax.service.ManufacturerService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCarController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AddCarController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("servlet for url - '/cars/add' was called...");
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String model = req.getParameter("model");
        long manufacturerId = Long.parseLong(req.getParameter("manufacturer_id"));
        Manufacturer manufacturer = manufacturerService.get(manufacturerId);
        Car car = new Car(model, manufacturer);
        carService.create(car);
        resp.sendRedirect("/cars/add");
    }
}
