package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.ProjectRepository;
import gruppe5.alphasolutions.services.RoleChecker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {

    ProjectRepository proRepo = new ProjectRepository();
    RoleChecker roleChecker = new RoleChecker();


    @GetMapping("/project")
    public String project(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute("useremail");

        ArrayList<Project> userProjects = proRepo.getUserProjects(currentUser);
        model.addAttribute("userprojects", userProjects);

        roleChecker.roleChecker(model, request);
        return "project";
    }

    @GetMapping("/newProject")
    public String doProject(Model model, HttpServletRequest request) {
        roleChecker.roleChecker(model, request);
        return "doproject";
    }

    @PostMapping("/makeProject")
    public String makeProject(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("descriptions") String descriptions, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deadline) {
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String useremail = (String) session.getAttribute("useremail");
        proRepo.sendData(title, descriptions, startDate, deadline, useremail);
        return "redirect:/project";
    }

    @GetMapping("/allProjects")
    public String allProjects(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        ArrayList<Project> allProjects = proRepo.showAllData();
        model.addAttribute("allprojects", allProjects);
        roleChecker.roleChecker(model, request);

        return "allprojects";
    }
}
