package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String project(Model model) {
        model.addAttribute("project", project);
        return "project";
    }

    @GetMapping("/newProject")
    public String doProject() {
        return "doproject";
    }

    @PostMapping("/makeProject")
    public String makeProject(@ModelAttribute Project model) {
        this.project = model;
        return "redirect:/project";
    }
    // Purely used to check all projects in the database
    @GetMapping("/allProjects")
        public String allProjects(Model model) {
        DBManager.getConnection();
        ArrayList<Project> allProjects = projectRepository.showAllData();
        model.addAttribute("allprojects", allProjects);
        return "allprojects";
        }
}
