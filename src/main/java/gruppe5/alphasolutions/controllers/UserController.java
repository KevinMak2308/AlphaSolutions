package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    UserRepository userRepository = new UserRepository();
    User user;

    public UserController(){
        this.user = new User(1,"simon@mail.dk","");
    }
    @GetMapping("/ListOfAllAccounts")
    public String account(Model model){
        ArrayList<User> userList = userRepository.showAllData();
        model.addAttribute("user",userList);
        return "allAccounts";
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

}
