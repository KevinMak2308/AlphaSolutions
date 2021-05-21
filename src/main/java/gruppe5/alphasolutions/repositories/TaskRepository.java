package gruppe5.alphasolutions.repositories;
import gruppe5.alphasolutions.models.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepository {

    public void sendData(String title, String descriptions, LocalDate startDate, LocalDate deadline){
        try {
            Connection taskConn = DBManager.getConnection();
            String taskQuery = "Insert into projects (taskID, title, descriptions, startDate, deadline) values('" + title + "', '" + descriptions + "', '" + startDate + "', '" + deadline + "')";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.executeUpdate();
            taskStatement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> showAllData() {
        ArrayList<Task> allTask = new ArrayList<>();

        try {
            Connection taskConnection = DBManager.getConnection();
            PreparedStatement taskStatement = taskConnection.prepareStatement("Select * From projects");
            ResultSet taskResult = taskStatement.executeQuery();

            while(taskResult.next()) {
                Task tmp = new Task(taskResult.getInt(1), taskResult.getString(2), taskResult.getString(3), taskResult.getDate(4).toLocalDate(), taskResult.getDate(5).toLocalDate());
                allTask.add(tmp);

            }

            taskStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return allTask;
    }

    public Task getData(String useremail) {
        Task tmp = null;

        try {
            Connection taskConn = DBManager.getConnection();
            String taskQuery = "Select * From users Join user_tasks On users.useremail = user_tasks.useremail Join tasks On tasks.taskID = user_tasks.taskID Where users.useremail = ?";
            PreparedStatement taskStatement = taskConn.prepareStatement(taskQuery);
            taskStatement.setString(1, useremail);

            ResultSet taskResult = taskStatement.executeQuery();
            if (taskResult.next()) {

                tmp = new Task(taskResult.getInt(1), taskResult.getString(2), taskResult.getString(3), taskResult.getDate(4).toLocalDate(), taskResult.getDate(5).toLocalDate());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tmp;
    }

    public void deleteData(int taskID) {
        try {

            Connection connection = DBManager.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM projects WHERE taskID = ?");
            preparedStatement1.setInt(1, taskID);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }
    }


}
