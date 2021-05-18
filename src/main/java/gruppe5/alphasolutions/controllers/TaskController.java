package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.ProjectRepository;
import gruppe5.alphasolutions.repositories.TaskRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;

@Controller
public class TaskController {
    private Task task;
    TaskRepository taskRepository;

    public TaskController(){
        this.task = new Task(1,"1st task","description of 1st task",null,null);
        this.taskRepository = new TaskRepository();
    }

    @GetMapping("/task")
    public String task(Model model) {
        model.addAttribute("task",task);
        return "task";
    }

    @GetMapping("newTask")
    public String doTask(){
        return "dotask";
    }

    @PostMapping("/makeTask")
    public String makeProjekt(@ModelAttribute Task model){
        this.task = model;
         return "redirect:/task";
    }

    @GetMapping("/allTasks")
    public String allProjects(Model model) {
        DBManager.getConnection();
        ArrayList<Task> allTasks = taskRepository.showAllData();
        model.addAttribute("allTasks", allTasks);
        return "alltasks";
    }

}
