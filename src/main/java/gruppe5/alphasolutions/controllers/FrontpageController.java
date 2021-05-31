package gruppe5.alphasolutions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontpageController {


    @GetMapping("/")
    public String frontpage() {
        return "frontpage";
    }
}
