package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Roles;
import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.RoleRepository;
import gruppe5.alphasolutions.repositories.UserRepository;
import gruppe5.alphasolutions.services.RoleChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RoleController {
    RoleRepository roleRepo = new RoleRepository();
    UserRepository userRepo = new UserRepository();
    RoleChecker roleChecker = new RoleChecker();

   /* @GetMapping("/roles")
    public String roles(Model model, HttpServletRequest request) {
        ArrayList<Roles> tmproles = roleRepo.getAllRoles();
        model.addAttribute("userroles", tmproles);
        roleChecker.roleChecker(model, request);
        return "roles";
    }*/

    @GetMapping("/newRoles")
    public String doRole(Model model, HttpServletRequest request) {
        ArrayList<User> allUsers = userRepo.showAllData();
        model.addAttribute("allusers", allUsers);
        roleChecker.roleChecker(model, request);
        return "dorole";
    }

    @PostMapping("/assignRoles")
    public String assignRoles(@RequestParam("roleID") int roleID, @RequestParam("useremail") String userEmail) {
        roleRepo.assignRole(roleID, userEmail);
        return "redirect:/allUsers";
    }
}
