package pap21l.z09.weatherappserver.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import pap21l.z09.weatherappserver.Entity.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    boolean existsByName(String name);
    Profile findByName(String name);
    List<Profile> findAll();
    void delete(Profile profile);
}
