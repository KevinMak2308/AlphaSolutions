package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.ProjectRepository;
import gruppe5.alphasolutions.repositories.RoleRepository;
import gruppe5.alphasolutions.repositories.TaskRepository;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class TaskController {
    Task task;
    TaskRepository taskRepository;

    public TaskController(){
        this.task = new Task(1,"1st task","description of 1st task",null,null);
        this.taskRepository = new TaskRepository();
    }

    @GetMapping("/task")
    public String task(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String useremail = (String) session.getAttribute("useremail");
        Task userTask = taskRepository.getData(useremail);
        model.addAttribute("task",userTask);

        return "task";
    }

    @GetMapping("newTask")
    public String doTask(){
        return "dotask";
    }

    @PostMapping("/makeTask")
    public String makeProjekt(@RequestParam("title") String title, @RequestParam("descriptions") String descriptions, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deadline){
        DBManager.getConnection();
        taskRepository.sendData(title, descriptions, startDate, deadline);
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
