package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.RoleRepository;
import gruppe5.alphasolutions.repositories.TaskRepository;
import gruppe5.alphasolutions.repositories.UserRepository;
import gruppe5.alphasolutions.services.Calculater;
import gruppe5.alphasolutions.services.RoleChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserController {

    UserRepository userRepo = new UserRepository();
    RoleRepository roleRepo = new RoleRepository();
    TaskRepository taskRepo = new TaskRepository();
    RoleChecker roleChecker = new RoleChecker();
    User user;

    public UserController() {
        this.user = new User("simon@mail.dk", "1234");
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("useremail");
        User currentUser = userRepo.getData(userEmail);
        roleChecker.roleChecker(model, request);
        if (currentUser == null)
            return "redirect:/login";

        model.addAttribute("user", currentUser);
        return "user";
    }

    @GetMapping("/register")
    public String registerAccount() {
        DBManager.getConnection();
        return "doregister";
    }


    @PostMapping("/makeUser")
    public String makeAccount(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword, HttpServletRequest request) {
        DBManager.getConnection();
        userRepo.sendData(userEmail, userPassword);
        HttpSession session = request.getSession();
        session.setAttribute("useremail", userEmail);

        return "redirect:/user?useremail=" + userEmail;
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        ArrayList<User> allUsers = userRepo.showAllData();
        model.addAttribute("allusers", allUsers);
        roleChecker.roleChecker(model, request);
        return "allusers";
    }

}
