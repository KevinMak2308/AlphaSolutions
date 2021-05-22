package gruppe5.alphasolutions.controllers;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.repositories.DBManager;
import gruppe5.alphasolutions.repositories.TaskRepository;
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
public class TaskController {
    Task task;
    TaskRepository taskRepo;

    public TaskController(){
        this.task = new Task(1, "1st task", "description of 1st task", null, null, "");
        this.taskRepo = new TaskRepository();
    }

    @GetMapping("/task")
    public String task(Model model, HttpServletRequest request) {
        DBManager.getConnection();

        HttpSession session = request.getSession();
        String task = (String) session.getAttribute("useremail");


        ArrayList<Task> allUserTasks = taskRepo.getAllUserTasks(task);
        model.addAttribute("usertasks", allUserTasks);

        return "task";
    }

    @GetMapping("/newTask")
    public String doTask(){
        return "dotask";
    }

    @PostMapping("/makeTask")
    public String makeProjekt(@RequestParam("taskID") int taskID, @RequestParam("useremail") String useremail, @RequestParam("title") String title, @RequestParam("descriptions") String descriptions, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("deadline") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deadline){
        DBManager.getConnection();
        taskRepo.sendData(title, descriptions, startDate, deadline);
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
