package gruppe5.alphasolutions.services;

import gruppe5.alphasolutions.repositories.ProjectRepository;

import java.util.ArrayList;


public class Calculator {
    ProjectRepository proRepo = new ProjectRepository();


    public int estimatedProjectTime(int projectID) {

        int sum = 0;

        ArrayList<Integer> listOfProjectTasks = proRepo.getProjectTaskTime(projectID);

        for (int i = 0; i < listOfProjectTasks.size(); i++) {
            sum += listOfProjectTasks.get(i);
        }

        return sum;

    }


}

