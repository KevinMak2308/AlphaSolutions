package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    private Project project;
    ProjectRepository projectRepository;

    public ProjectController() {
        this.project = new Project(1, "New Project", "First", null, null);
        this.projectRepository = new ProjectRepository();
    }

    @GetMapping("/project")
    public String project(Model model, HttpServletRequest request) {
        DBManager.getConnection();

        model.addAttribute("project", project);
        return "project";
    }

    @GetMapping("/newProject")
    public String doProject() {
        return "doproject";
    }

    @PostMapping("/makeProject")
    public String makeProject(@RequestParam("title") String title, @RequestParam("descriptions") String descriptions, @RequestParam("startDate") LocalDate startDate, @RequestParam("deadline") LocalDate deadline, @ModelAttribute Project model) {
        DBManager.getConnection();
        projectRepository.sendData(title, descriptions, startDate, deadline);
        this.project = model;
        return "redirect:/project";
    }

    @GetMapping("/allProjects")
        public String allProjects(Model model) {
        DBManager.getConnection();
        ArrayList<Project> allProjects = projectRepository.showAllData();
        model.addAttribute("allprojects", allProjects);
        return "allprojects";
        }
}
