package gruppe5.alphasolutions.repositories;


import gruppe5.alphasolutions.models.Project;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class ProjectRepository {

    Connection connection = DBManager.getConnection();


    public void sendData(String title, String descriptions, LocalDate startDate, LocalDate deadline, String useremail) {
        try {
            Connection proConn = connection;
            String projectQuery = "Insert into projects (title, descriptions, startdate, deadline, useremail) values('" + title + "', '" + descriptions + "', '" + startDate + "', '" + deadline + "', '" + useremail + "')";
            PreparedStatement proStatement = proConn.prepareStatement(projectQuery);

            proStatement.executeUpdate();
            proStatement.close();

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public ArrayList<Project> showAllData() {
        ArrayList<Project> allProjects = new ArrayList<>();

        try {
            Connection proConn = connection;
            PreparedStatement proStatement = proConn.prepareStatement("Select * From projects");
            ResultSet proResult = proStatement.executeQuery();

            while (proResult.next()) {
                Project tmp = new Project(proResult.getInt(2), proResult.getString(3), proResult.getString(4), proResult.getDate(5).toLocalDate(), proResult.getDate(6).toLocalDate(), proResult.getString(1));
                allProjects.add(tmp);

            }

            proStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return allProjects;
    }


    public ArrayList<Project> getUserProjects(String useremail) {
        ArrayList<Project> userProjects = new ArrayList<>();

        try {
            Connection proConn = connection;
            String projectQuery = "Select * From projects Where useremail = ?";
            PreparedStatement projectStatement = proConn.prepareStatement(projectQuery);
            projectStatement.setString(1, useremail);

            ResultSet projectResult = projectStatement.executeQuery();
            while (projectResult.next()) {

                Project tmp = new Project(projectResult.getInt(2), projectResult.getString(3), projectResult.getString(4), projectResult.getDate(5).toLocalDate(), projectResult.getDate(6).toLocalDate(), projectResult.getString(1));
                userProjects.add(tmp);
            }

            projectStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userProjects;
    }

    public void assignProject(int projectID, int taskID) {

        try {
            Connection proConn = connection;
            String taskQuery = "Insert Into project_tasks(projectID, taskID) Values(" + projectID + ", " + taskID + ")";
            PreparedStatement taskStatement = proConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<Integer> getProjectTaskTime(int projectID) {
        ArrayList<Integer> projectTasks = new ArrayList<>();
        try {
            Connection proConn = connection;
            String projectQuery = "Select tasks.estimatedtime From projects Join project_tasks On projects.projectID = project_tasks.projectID Join tasks On tasks.taskID = project_tasks.taskID Where projects.projectID = ?";
            PreparedStatement projectStatement = proConn.prepareStatement(projectQuery);
            projectStatement.setInt(1, projectID);
            ResultSet proResult = projectStatement.executeQuery();


            while (proResult.next()) {
                int tmp = (proResult.getInt(1));
                projectTasks.add(tmp);
            }

            projectStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return projectTasks;

    }


    public Project projectDetails(int projectID) {
        Project tmp = null;
        try {
            Connection proConn = connection;
            String projectQuery = "Select * From projects Where projectID = ?";
            PreparedStatement projectStatement = proConn.prepareStatement(projectQuery);
            projectStatement.setInt(1, projectID);
            ResultSet proResult = projectStatement.executeQuery();

            if (proResult.next()) {
                tmp = new Project(proResult.getInt(2), proResult.getString(3), proResult.getString(4), proResult.getDate(5).toLocalDate(), proResult.getDate(6).toLocalDate(), proResult.getString(1));
            }

            projectStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return tmp;

    }


}
