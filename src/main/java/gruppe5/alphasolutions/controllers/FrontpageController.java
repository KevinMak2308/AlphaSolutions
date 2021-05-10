package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.repositories.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontpageController {


    @GetMapping("/")
    public String frontpage() {
        //DBManager.getConnection();
        return "frontpage";
    }
}
