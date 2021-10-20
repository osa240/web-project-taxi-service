package basesyntax.controller.driver;

import basesyntax.lib.Injector;
import basesyntax.model.Car;
import basesyntax.service.CarService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetMyCurrentCarsController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetMyCurrentCarsController.class);
    private static final Injector injector = Injector.getInstance("basesyntax");
    private final CarService carService = (CarService) injector
            .getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("servlet for url - '/drivers/cars/' was called...");
        Long driverId = (Long) req.getSession().getAttribute("driver_id");
        List<Car> cars = carService.getAllByDriver(driverId);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
