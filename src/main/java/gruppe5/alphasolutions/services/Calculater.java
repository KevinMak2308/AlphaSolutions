package gruppe5.alphasolutions.services;



import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.repositories.ProjectRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class Calculater {
    ProjectRepository proRepo = new ProjectRepository();
    Task task = new Task(1, "", "", null, null, 1);

    public void calc() {
        LocalDate date = LocalDate.parse("2020-03-11");
        System.out.print("Date : " + date);
        // Formatting Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String dateLocal = formatter.format(date);
        System.out.println("Date2 : " + dateLocal);
    }



    public Map timeCalculator() {
        Task tasktime = new Task(1, "", "", null, null, 1);

        int estimatedtime = tasktime.getEstimatedtime();

        Map<String, Integer> timeOfTask = new HashMap<>();

        timeOfTask.put("Time of task", estimatedtime);

        System.out.println(timeOfTask);

        return timeCalculator();
    }


    public int estimatedProjectTime(String session, ArrayList<Project> list) {
        // All Project for the user logged in //
        Project project = new Project(1, "", "", null, null, "whatevr@gmail.com");

        // Get time for all the project tasks linked to the user logged in //
        int sum = 0;
        int projectID = project.getProjectID();
        for( int i = 0; i < projectID; i++) {

            ArrayList<Integer> listOfProjectTasks = proRepo.getProjectTaskTime(projectID);

            for (int j = 0; j < listOfProjectTasks.size(); j++) {
                 sum += listOfProjectTasks.get(j);

            }
        }

        System.out.println(sum);
        return sum;

    }











}

