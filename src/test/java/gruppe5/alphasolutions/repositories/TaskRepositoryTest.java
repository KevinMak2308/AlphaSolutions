package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class TaskRepositoryTest {

    TaskRepository taskRepository = new TaskRepository();

    @Test
    @DisplayName("Check if getAllProjectTasks method works")
    void getAllProjectTasks() {
        Task taskTest = new Task(1,"Test Task", "test 1",  LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 2),1);
        ArrayList<Task> viewOverProjectTasksTest = new ArrayList<>();
        viewOverProjectTasksTest.add(taskTest);
        int projectID = 1;
        ArrayList<Task> viewOverProjectTasks = taskRepository.getAllProjectTasks(projectID);
        assertEquals("Do we get the tasks from the projectID it is connected to", viewOverProjectTasksTest.toString(), viewOverProjectTasks.toString());

    }

    @Test
    @DisplayName("Check if taskDetails method works")
    void taskDetails() {
        Task taskTest = new Task(1,"Test Task", "test 1",  LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 2),1);
        int taskIDTest = 1;
        Task task = taskRepository.taskDetails(taskIDTest);
        assertEquals("Do we get the right and all the task attributes", taskTest.toString(), task.toString());

    }
}