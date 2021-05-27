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


public class Calculator {
    ProjectRepository proRepo = new ProjectRepository();


    public int estimatedProjectTime(int projectID) {

        int sum = 0;

            ArrayList<Integer> listOfProjectTasks = proRepo.getProjectTaskTime(projectID);

            for (int i = 0; i < listOfProjectTasks.size(); i++) {
                 sum += listOfProjectTasks.get(i);
            }

        System.out.println(sum);
        return sum;

    }











}

