package MagMutual.TakeHomeProject.UserAPI;
import MagMutual.TakeHomeProject.UserAPI.model.User;
import MagMutual.TakeHomeProject.UserAPI.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testGetUsersByProfession_validProfession() {
        List<User> users = userService.getUsersByProfession("worker");
        assertNotNull(users);
        assertTrue(users.size() > 0);
        for (User user : users) {
            assertEquals("worker", user.getProfession());
        }
    }

    @Test
    public void testGetUsersByProfession_invalidProfession() {
        List<User> users = userService.getUsersByProfession("NotValid");
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    public void testGetUsersByDateRange_validRange() {
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 31);
        List<User> users = userService.getUsersByDateRange(startDate, endDate);
        assertNotNull(users);
        assertTrue(users.size() > 0);
        for (User user : users) {
            assertTrue(user.getDateCreated().isAfter(startDate) && user.getDateCreated().isBefore(endDate));
        }
    }

    @Test
    public void testGetUsersByDateRange_invalidRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 31);
        List<User> users = userService.getUsersByDateRange(startDate, endDate);
        assertNotNull(users);
        assertTrue(users.size() == 0);
    }

    @Test
    public void testGetUserByName_validName(){
        User user = userService.getUserByName("Andree", "Flita");

        assertNotNull(user);
        assertTrue(user.getFirstName().equalsIgnoreCase("Andree") && user.getLastName().equalsIgnoreCase("Flita"));
    }

    @Test
    public void testGetUserByName_invalidName(){
        User user = userService.getUserByName("NotValid", "NotValid");
        assertTrue(user == null);
    }

    @Test
    public void testGetUserByCity_validCity(){
        List<User> users = userService.getUsersByCity("Atlanta");
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testGetUserByCity_invalidCity(){
        List<User> users = userService.getUsersByCity("NotValid");
        assertNotNull(users);
        assertTrue(users.size() == 0);
    }

}
