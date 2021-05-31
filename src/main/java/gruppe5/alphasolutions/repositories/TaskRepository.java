package gruppe5.alphasolutions.repositories;
import gruppe5.alphasolutions.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepository {

    Connection connection = DBManager.getConnection();

    public void sendData(String title, String descriptions, LocalDate startDate, LocalDate deadline, int estimatedtime){
        try {
            Connection taskConn = connection;
            String taskQuery = "Insert into tasks (title, descriptions, startdate, deadline, estimatedtime) values('" + title + "', '" + descriptions + "', '" + startDate + "', '" + deadline + "', " + estimatedtime +")";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getAllUserTasks(String useremail) {
        ArrayList<Task> allTask = new ArrayList<>();

        try {
            Connection taskConnection = connection;
            String taskQuery = "Select users.useremail, tasks.taskID, tasks.title, tasks.descriptions, tasks.startdate, tasks.deadline, tasks.estimatedtime From users Join user_tasks On users.useremail = user_tasks.useremail Join tasks On tasks.taskID = user_tasks.taskID Where users.useremail = ?";
            PreparedStatement taskStatement = taskConnection.prepareStatement(taskQuery);
            taskStatement.setString(1, useremail);
            ResultSet taskResult = taskStatement.executeQuery();

            while(taskResult.next()) {
                Task tmp = new Task(taskResult.getInt(2), taskResult.getString(3), taskResult.getString(4), taskResult.getDate(5).toLocalDate(), taskResult.getDate(6).toLocalDate(), taskResult.getInt(7));
                allTask.add(tmp);

            }

            taskStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return allTask;
    }

    public ArrayList<Task> showAllData() {
        ArrayList<Task> allTasks = new ArrayList<>();

        try {
            Connection userConnection = connection;
            PreparedStatement taskStatement = userConnection.prepareStatement("Select * From tasks");
            ResultSet taskResult = taskStatement.executeQuery();

            while(taskResult.next()) {
                Task tmp = new Task(taskResult.getInt(1), taskResult.getString(2), taskResult.getString(3), taskResult.getDate(4).toLocalDate(), taskResult.getDate(5).toLocalDate(), taskResult.getInt(6));
                allTasks.add(tmp);

            }
            taskStatement.close();


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } return allTasks;
    }



    public void assignTask(int taskID, String useremail) {

        try {
            Connection taskConn = connection;
            String taskQuery = "Insert Into user_tasks(taskID, useremail) Values(" + taskID + ", '" + useremail + "')";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();



            taskStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public ArrayList<Task> getAllProjectTasks(int projectID) {

        ArrayList<Task> viewOverProjectTasks = new ArrayList<>();
        try {
            Connection projectConn = connection;
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

    public Task taskDetails(int taskID) {
        Task tmp = null;
        try {
            Connection taskConn = connection;
            String taskQuery = "Select * From tasks Where taskID = ?";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.setInt(1, taskID);
            ResultSet taskResult = taskStatement.executeQuery();

            if(taskResult.next()) {
                tmp = new Task(taskResult.getInt(1), taskResult.getString(2), taskResult.getString(3), taskResult.getDate(4).toLocalDate(), taskResult.getDate(5).toLocalDate(), taskResult.getInt(6));
            }

            } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return tmp;
    }

    public void deleteData(int taskID) {
        try {

            Connection taskConn = connection;
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM projects WHERE taskID = ?");
            preparedStatement1.setInt(1, taskID);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
    }

}
