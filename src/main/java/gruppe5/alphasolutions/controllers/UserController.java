package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class UserController {

    UserRepository userRepository = new UserRepository();
    User user;

    public UserController(){
        this.user = new User(1,"simon@mail.dk","");
    }
    @GetMapping("/ListOfAllUsers")
    public String account(Model model){
        DBManager.getConnection();
        ArrayList<User> allUsers = userRepository.showAllData();
        model.addAttribute("allusers",allUsers);
        return "allusers";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        DBManager.getConnection();
        ArrayList<User> allUsers = userRepository.showAllData();
        model.addAttribute("allusers", allUsers);
        return "allusers";
    }

    @GetMapping("/registerAccount")
    public String registerAccount(){
        return "doregister";
    }


    @PostMapping("/makeAccount")
    public String makeAccount(@ModelAttribute User model){
        this.user = model;
        return "redirect:/account";
    }

    @PostMapping("/deleteAccount")
    public String deleteWish(@RequestParam("useremail")String useremail){
        userRepository.deleteData(useremail);
        return "redirect:/account";
    }
}
