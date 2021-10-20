package basesyntax.dao;

import basesyntax.model.Car;
import java.util.List;

public interface CarDao extends GenericDao<Car> {
    List<Car> getAllByDriver(Long driverId);
}
