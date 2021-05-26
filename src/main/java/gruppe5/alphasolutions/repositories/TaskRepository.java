package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepository {

    public void sendData(String title, String descriptions, LocalDate startDate, LocalDate deadline) {
        try {
            Connection taskConn = DBManager.getConnection();
            String taskQuery = "Insert into tasks (title, descriptions, startdate, deadline) values('" + title + "', '" + descriptions + "', '" + startDate + "', '" + deadline + "')";
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
            Connection taskConnection = DBManager.getConnection();
            String taskQuery = "Select users.useremail, tasks.taskID, tasks.title, tasks.descriptions, tasks.startdate, tasks.deadline From users Join user_tasks On users.useremail = user_tasks.useremail Join tasks On tasks.taskID = user_tasks.taskID Where users.useremail = ?";
            PreparedStatement taskStatement = taskConnection.prepareStatement(taskQuery);
            taskStatement.setString(1, useremail);
            ResultSet taskResult = taskStatement.executeQuery();

            while (taskResult.next()) {
                Task tmp = new Task(taskResult.getInt(2), taskResult.getString(3), taskResult.getString(4), taskResult.getDate(5).toLocalDate(), taskResult.getDate(6).toLocalDate());
                allTask.add(tmp);

            }

            taskStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allTask;
    }

    public ArrayList<Task> showAllData() {
        ArrayList<Task> allTasks = new ArrayList<>();

        try {
            Connection userConnection = DBManager.getConnection();
            PreparedStatement taskStatement = userConnection.prepareStatement("Select * From tasks");
            ResultSet taskResult = taskStatement.executeQuery();

            while (taskResult.next()) {
                Task tmp = new Task(taskResult.getInt(1), taskResult.getString(2), taskResult.getString(3), taskResult.getDate(4).toLocalDate(), taskResult.getDate(5).toLocalDate());
                allTasks.add(tmp);

            }
            taskStatement.close();


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return allTasks;
    }


    public void assignTask(int taskID, String useremail) {

        try {
            Connection taskConn = DBManager.getConnection();
            String taskQuery = "Insert Into user_tasks(taskID, useremail) Values(" + taskID + ", '" + useremail + "')";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();


            taskStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}