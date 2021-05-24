package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.models.User;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.TaskRepository;
import gruppe5.alphasolutions.repositories.UserRepository;
import gruppe5.alphasolutions.services.RoleChecker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class TaskController {
    Task task;
    TaskRepository taskRepo;
    UserRepository userRepo;
    RoleChecker roleChecker = new RoleChecker();

    public TaskController() {
        this.task = new Task(1, "1st task", "description of 1st task", null, null);
        this.taskRepo = new TaskRepository();
        this.userRepo = new UserRepository();
    }

    @GetMapping("/task")
    public String task(Model model, HttpServletRequest request) {
        DBManager.getConnection();
        HttpSession session = request.getSession();
        String task = (String) session.getAttribute("useremail");
        ArrayList<Task> allUserTasks = taskRepo.getAllUserTasks(task);
        model.addAttribute("usertasks", allUserTasks);
        roleChecker.roleChecker(model, request);

        return "task";
    }

    @GetMapping("/newTask")
    public String doTask(Model model, HttpServletRequest request) {
        roleChecker.roleChecker(model, request);
        return "dotask";
    }

    @PostMapping("/makeTask")
    public String makeTask(@RequestParam("title") String title, @RequestParam("descriptions") String descriptions, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deadline) {
        DBManager.getConnection();
        taskRepo.sendData(title, descriptions, startDate, deadline);
        return "redirect:/task";
    }


    @GetMapping("/selectTask")
    public String selectTask(Model model,HttpServletRequest request) {
        ArrayList<User> allUsers = userRepo.showAllData();
        model.addAttribute("allusers", allUsers);

        ArrayList<Task> allTasks = taskRepo.showAllData();
        model.addAttribute("alltasks", allTasks);
        roleChecker.roleChecker(model, request);
        return "assigntask";
    }

    @PostMapping("/assignTask")
    public String assignTask(@RequestParam("taskID") int taskID, @RequestParam("useremail") String useremail) {
        DBManager.getConnection();
        taskRepo.assignTask(taskID, useremail);
        return "redirect:/task";
    }


    /*@GetMapping("/allTasks")
    public String allProjects(Model model) {
        DBManager.getConnection();
        ArrayList<Task> allTasks = taskRepo.getAllUserTasks();
        model.addAttribute("allTasks", allTasks);
        return "alltasks";
    }*/

}
