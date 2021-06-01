package gruppe5.alphasolutions.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class RoleRepositoryTest {

    RoleRepository roleRepository = new RoleRepository();

    @Test
    @DisplayName("Check if checkRole method works")
    void checkRole() {
        String userEmail = "admin@gmail.com";
        int roleIDTest = 3;
        int roleID = roleRepository.checkRole(userEmail);
        assertEquals("Do we get the right roleID from the user email", roleIDTest, roleID);
    }

    @Test
    @DisplayName("Check if assignRole method works")
    void assignRole() {
        int roleIDTest = 2;
        String userEmail = "manager@mail.com";
        roleRepository.assignRole(roleIDTest, userEmail);
        int roleID = roleRepository.checkRole(userEmail);
        assertEquals("Do the user get the roleID after assigning it", roleIDTest, roleID);

    }
}