package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    UserRepository userRepository = new UserRepository();

    @PostMapping("/login")
    public String submitLogin(@RequestParam(name = "useremail") String useremail, @RequestParam(name = "password") String password, HttpServletRequest request) {
        //DBManager.getConnection();
        if (userRepository.validateData(useremail, password) == true) {
            HttpSession session = request.getSession();
            session.setAttribute("useremail", useremail);
            session.setAttribute("password", password);

            return "redirect:/account?useremail=" + useremail;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "frontPage";
    }
}
