package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class UserRepositoryTest {

    @Test
    void sendDatatoDatabase(String userEmail) {
    }

    @Test
    void showAllData() {
    }

    @Test
    @DisplayName("Check if getData method works")
    void getData() {
        String useremail = "gg@gmail.com";
        User user1 = new User("gg@gmail.com","1234");
        UserRepository userRepository = new UserRepository();
        User user2 = userRepository.getData(useremail);
        assertEquals("does email = new User", user1.getUserEmail(), user2.getUserEmail());

    }

    @Test
    void validateData() {
    }

    @Test
    void deleteData() {
    }
}