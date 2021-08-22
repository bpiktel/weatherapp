package pap21l.z09.weatherappserver.Repository;

import org.springframework.data.repository.CrudRepository;

import pap21l.z09.weatherappserver.Entity.Forecast;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {

    boolean existsByCity(String city);
    Forecast findByCity(String city);

}
