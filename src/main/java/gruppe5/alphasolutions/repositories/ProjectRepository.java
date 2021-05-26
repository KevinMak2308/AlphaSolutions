package gruppe5.alphasolutions.repositories;


import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.models.Task;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {


    public void sendData(String title, String descriptions, LocalDate startDate, LocalDate deadline, String useremail) {
        try {
            Connection proConn = DBManager.getConnection();
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
            Connection proConnection = DBManager.getConnection();
            PreparedStatement proStatement = proConnection.prepareStatement("Select * From projects");
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
            Connection proConn = DBManager.getConnection();
            String projectQuery = "Select * From projects Where useremail = ?";
            PreparedStatement projectStatement = proConn.prepareStatement(projectQuery);
            projectStatement.setString(1, useremail);

            ResultSet projectResult = projectStatement.executeQuery();
            while (projectResult.next()) {

                Project tmp = new Project(projectResult.getInt(2), projectResult.getString(3), projectResult.getString(4), projectResult.getDate(5).toLocalDate(), projectResult.getDate(6).toLocalDate(), projectResult.getString(1));
                userProjects.add(tmp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userProjects;
    }

    public void assignProject(int projectID, int taskID) {

        try {
            Connection taskConn = DBManager.getConnection();
            String taskQuery = "Insert Into project_tasks(projectID, taskID) Values(" + projectID + ", " + taskID + ")";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();


            taskStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<Integer> getProjectTaskTime (int projectID) {
        ArrayList<Integer> projectTasks = new ArrayList<>();
        try {
            Connection projectConn = DBManager.getConnection();
            String projectQuery = "Select tasks.estimatedtime From projects Join project_tasks On projects.projectID = project_tasks.projectID Join tasks On tasks.taskID = project_tasks.taskID Where projects.projectID = ?";
            PreparedStatement projectStatement = projectConn.prepareStatement(projectQuery);
            projectStatement.setInt(1, projectID);
            ResultSet proResult = projectStatement.executeQuery();

            while (proResult.next()) {
                int tmp = (proResult.getInt(1));
                projectTasks.add(tmp);
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
                return projectTasks;

        }


    public ArrayList<Task> taskDetails (int projectID) {

        ArrayList<Task> viewOverProjectTasks = new ArrayList<>();
        try {
            Connection projectConn = DBManager.getConnection();
            String projectQuery = "Select projects.projectID, tasks.taskID, tasks.title, tasks.descriptions, tasks.startdate, tasks.deadline, tasks.estimatedtime From projects Join project_tasks On projects.projectID = project_tasks.projectID Join tasks On tasks.taskID = project_tasks.taskID Where projects.projectID = ?";

            PreparedStatement projectStatement = projectConn.prepareStatement(projectQuery);
            projectStatement.setInt(1, projectID);
            ResultSet proResult = projectStatement.executeQuery();

            while (proResult.next()) {
                Task tmp = new Task (proResult.getInt(2), proResult.getString(3), proResult.getString(4), proResult.getDate(5).toLocalDate(), proResult.getDate(6).toLocalDate(), proResult.getInt(7));
                viewOverProjectTasks.add(tmp);
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return viewOverProjectTasks;

    }

    public Project projectDetails (int projectID) {
        Project tmp = null;
        try {
            Connection projectConn = DBManager.getConnection();
            String projectQuery = "Select * From projects Where projectID = ?";
            PreparedStatement projectStatement = projectConn.prepareStatement(projectQuery);
            projectStatement.setInt(1, projectID);
            ResultSet proResult = projectStatement.executeQuery();

            if (proResult.next()) {
                tmp = new Project (proResult.getInt(2), proResult.getString(3), proResult.getString(4), proResult.getDate(5).toLocalDate(), proResult.getDate(6).toLocalDate(), proResult.getString(1));
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return tmp;

    }


    }
