package MagMutual.TakeHomeProject.UserAPI.controllers;

import MagMutual.TakeHomeProject.UserAPI.model.User;
import MagMutual.TakeHomeProject.UserAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/date-range")
    public ResponseEntity<List<User>> getUsersByDateRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<User> users = userService.getUsersByDateRange(startDate, endDate);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profession")
    public ResponseEntity<List<User>> getUsersByProfession(@RequestParam("profession") String profession) {
        List<User> users = userService.getUsersByProfession(profession);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/city/{city}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable String city) {
        List<User> users = userService.getUsersByCity(city);
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseEntity<User> getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        User user = userService.getUserByName(firstName, lastName);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
