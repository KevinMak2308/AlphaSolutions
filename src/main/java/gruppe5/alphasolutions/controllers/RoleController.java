package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Roles;
import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.RoleRepository;
import gruppe5.alphasolutions.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RoleController {
    RoleRepository roleRepo = new RoleRepository();
    UserRepository userRepo = new UserRepository();


    @GetMapping("/roles")
    public String roles(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute("useremail");
        ArrayList<Roles> userRoles = roleRepo.getUserRole(currentUser);
        model.addAttribute("userroles", userRoles);
        return "roles";
    }


    @GetMapping("/newRoles")
    public String doRole(Model model) {
        DBManager.getConnection();
        ArrayList<User> allUsers = userRepo.showAllData();
        model.addAttribute("allusers", allUsers);
        return "dorole";
    }

    @PostMapping("/assignRoles")
    public String assignRoles(@RequestParam("roleID") int roleID, @RequestParam("userEmail") String userEmail, Model model) {
        DBManager.getConnection();
        //HttpSession session = request.getSession();
        //session.getAttribute("useremail");
        roleRepo.assignRole(roleID, userEmail);
        return "redirect:/roles";
    }
}
