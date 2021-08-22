package pap21l.z09.weatherappserver.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pap21l.z09.weatherappserver.Entity.Profile;
import pap21l.z09.weatherappserver.Repository.ProfileRepository;

@RestController
public class ProfileController {

    @Autowired
    ProfileRepository profileRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/getProfile")
    public Profile getProfile(@RequestParam(
        value = "name", defaultValue = "default") String name) {

        Profile p;
        if (profileRepository.existsByName(name)) {
            p = profileRepository.findByName(name);

        } else {
            p = new Profile();
        }
        return p;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/createProfile")
    public Profile createProfile(@RequestParam(
            value = "name", defaultValue = "default") String name,
        @RequestParam(value = "city",
            defaultValue = "Warszawa") String city) {

        Profile p;
        if (!profileRepository.existsByName(name) && name != "" && city != "") {
            p = new Profile(name, city);
            profileRepository.save(p);
        } else {
            p = new Profile();
        }
        return p;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/updateProfile")
    public Profile updateProfile(@RequestParam(
            value = "name", defaultValue = "default") String name,
        @RequestParam(value = "city",
            defaultValue = "Warszawa") String newCity) {

        Profile p;
        if (profileRepository.existsByName(name)) {
            p = profileRepository.findByName(name);
            p.setCity(newCity);
            profileRepository.save(p);
        } else {
            p = new Profile();
        }
        return p;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/deleteProfile")
    public boolean deleteProfile(@RequestParam(
        value = "name", defaultValue = "default") String name) {

        boolean deleted;
        if (profileRepository.existsByName(name)) {
            Profile p;
            p = profileRepository.findByName(name);
            profileRepository.delete(p);
            deleted = true;
        } else {
            deleted = false;
        }
        return deleted;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/getAllProfiles")
    public List<Profile> getAllProfiles() {
        List<Profile> profileList = profileRepository.findAll();
        return profileList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @GetMapping("/profileName")
    public boolean isProfileNameUsed(@RequestParam(
        value = "name", defaultValue = "default") String name) {

        return profileRepository.existsByName(name);
    }
}
