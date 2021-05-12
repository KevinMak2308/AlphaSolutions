package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class ProjectController {
    private Project project;

    public ProjectController() {
        this.project = new Project("New Project", "First", null, null);
        project.setStartDate(LocalDate.of(2021,05,12));
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
}
