package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @Test
    @DisplayName("Check if sendData method works")
    void sendData() {
        String userEmail = "simon@mail.dk";
        String userPassword = "1234";
        userRepository.sendData(userEmail, userPassword);
        User user1 = userRepository.getData(userEmail);
        assertEquals("Does the sendData method create a User in the database", userEmail, user1.getUserEmail());
    }

    @Test
    @DisplayName("Check if showAllData method works")
    void showAllData() {
        User user1 = new User("admin@gmail.com", "246");
        User user2 = new User("manager@mail.com", "147");
        User user3 = new User("simon@mail.dk", "1234");

        ArrayList<User> allUsers1 = new ArrayList<>();
        allUsers1.add(user1);
        allUsers1.add(user2);
        allUsers1.add(user3);

        ArrayList allUsers2 = userRepository.showAllData();

        assertEquals("Does allUsers1 = allUsers2", allUsers1.toString(), allUsers2.toString());

    }

    @Test
    @DisplayName("Check if getData method works")
    void getData() {
        String userEmail = "admin@gmail.com";
        User user1 = new User("admin@gmail.com", "246");
        User user2 = userRepository.getData(userEmail);
        assertEquals("Does email = new User", user1.getUserEmail(), user2.getUserEmail());
    }

    @Test
    @DisplayName("Check if getAllTaskUsers method works")
    void getAllTaskUsers() {
        User userTest = new User("manager@mail.com", "147");
        ArrayList<User> viewOverTaskUsersTest = new ArrayList<>();
        viewOverTaskUsersTest.add(userTest);
        int taskID = 1;
        ArrayList<User> viewOverTaskUsers = userRepository.getAllTaskUsers(taskID);
        assertEquals("Does the user come up when searching users to a task he is assigned to", viewOverTaskUsersTest.toString().contains("manager@mail.com"),viewOverTaskUsers.toString().contains("manager@mail.com"));
    }

    @Test
    @DisplayName("Check if validateData method works")
    void validateData() {
        String userEmail = "simon@mail.dk";
        String userPassword = "1234";
        boolean isItInTheDatabase = userRepository.validateData(userEmail, userPassword);
        assertEquals("Does the validateData = true", true, isItInTheDatabase);
    }

    @Test
    @DisplayName("Check if deleteData method works")
    void deleteData() {
        String userEmail = "simon@mail.dk";
        String userPassword = "1234";
        userRepository.deleteData(userEmail);
        boolean isItInTheDatabase = userRepository.validateData(userEmail, userPassword);
        assertEquals("Does the user exist in the database after deletion", false, isItInTheDatabase);
    }
}