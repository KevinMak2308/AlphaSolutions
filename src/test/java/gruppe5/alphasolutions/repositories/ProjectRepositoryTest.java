package gruppe5.alphasolutions.repositories;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class ProjectRepositoryTest {

    ProjectRepository projectRepository = new ProjectRepository();

    @Test
    @DisplayName("Check if getProjectTaskTime works")
    void getProjectTaskTime() {
        ArrayList<Integer> getProjectTaskTimeTest = new ArrayList<>();
        getProjectTaskTimeTest.add(1);
        int projectID = 1;
        ArrayList<Integer> getProjectTaskTime = projectRepository.getProjectTaskTime(projectID);
        assertEquals("Do we get the right task time on a given project", getProjectTaskTime.toString(), getProjectTaskTimeTest.toString());

    }
}