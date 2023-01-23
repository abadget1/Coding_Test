package MagMutual.TakeHomeProject.UserAPI.services;

import MagMutual.TakeHomeProject.UserAPI.model.User;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


@Service
public class UserService {


    private final List<User> users = new ArrayList<>();
    private final String filePath = "src/UserInformation[97].csv";

    public UserService() {
        try (Reader reader = new FileReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] nextLine;
                try {
                    csvReader.readNext();
                    while ((nextLine = csvReader.readNext()) != null) {
                        long id = Long.parseLong(nextLine[0]);
                        String firstName = nextLine[1];
                        String lastName = nextLine[2];
                        String email = nextLine[3];
                        String profession = nextLine[4];
                        LocalDate dateCreated = LocalDate.parse(nextLine[5]);
                        String country = nextLine[6];
                        String city = nextLine[7];
                        User user = new User(id, firstName, lastName, email, profession, dateCreated, country, city);
                        users.add(user);
                    }
                } catch (CsvValidationException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsersByProfession(String profession) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : this.users) {
            if (user.getProfession().equalsIgnoreCase(profession)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;

    }

    public List<User> getUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : this.users) {
            if (user.getDateCreated().isAfter(startDate) && user.getDateCreated().isBefore(endDate)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersByCity(String city){
        List<User> filteredList = new ArrayList<>();
        for (User user : users){
            if(user.getCity().equalsIgnoreCase(city))
                filteredList.add(user);
        }
        return filteredList;
    }

    public User getUserByName(String firstName, String lastName){
        for (User user : users){
            if(user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName))
                return user;
        }
        return null;
    }
}