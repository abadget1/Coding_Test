package MagMutual.TakeHomeProject.UserAPI;

import MagMutual.TakeHomeProject.UserAPI.controllers.UserController;
import MagMutual.TakeHomeProject.UserAPI.model.User;
import MagMutual.TakeHomeProject.UserAPI.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private final UserService userService = new UserService();

    @Autowired
    private final UserController userController = new UserController();

    @Test
    void testGetUsersByDateRange_validRange() {
        LocalDate startDate = LocalDate.of(2000, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 1);
        List<User> expectedUsers = userService.getUsersByDateRange(startDate, endDate);
        ResponseEntity<List<User>> response = userController.getUsersByDateRange(startDate, endDate);
        assertEquals(expectedUsers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUsersByDateRange_invalidRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2000, 1, 1);
        ResponseEntity<List<User>> response = userController.getUsersByDateRange(startDate, endDate);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetUsersByProfession_validProfession() {
        String profession = "developer";
        List<User> expectedUsers = userService.getUsersByProfession(profession);
        ResponseEntity<List<User>> response = userController.getUsersByProfession(profession);
        assertEquals(expectedUsers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUsersByProfession_invalidProfession() {
        String profession = "NotValid";
        ResponseEntity<List<User>> response = userController.getUsersByProfession(profession);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetUserById_validId() {
        long id = 100;
        User expectedUser = userService.getUserById(id);
        User user = userController.getUserById(id);
        assertEquals(expectedUser, user);
    }

    @Test
    void testGetUserById_invalidId() {
        long id = -1;
        User user = userController.getUserById(id);
        assertNull(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = userService.getAllUsers();
        List<User> users = userController.getAllUsers();
        assertEquals(expectedUsers, users);
    }

    @Test
    void testGetUsersByCity_validCity() {
        String city = "Atlanta";
        List<User> expectedUsers = userService.getUsersByCity(city);
        ResponseEntity<List<User>> response = userController.getUsersByCity(city);
        assertEquals(expectedUsers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUsersByCity_invalidCity() {
        String city = "NotValid";
        ResponseEntity<List<User>> response = userController.getUsersByCity(city);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetUserByName_validName() {
        User expectedUser = userService.getUserByName("Andree","Flita");
        ResponseEntity<User> response = userController.getUserByName("Andree","Flita");
        assertEquals(expectedUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetUserByName_invalidName() {
        ResponseEntity<User> response = userController.getUserByName("NotValid","NotValid");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
