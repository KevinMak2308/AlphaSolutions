package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
    Connection connection = DBManager.getConnection();

    public void sendData(String userEmail, String userPassword) {

        try {
            PreparedStatement userStatement = connection.prepareStatement("Insert into users(useremail, userpassword)" + "Values ('" + userEmail + "', '" + userPassword + "')");
            userStatement.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }

    }

    public ArrayList<User> showAllData() {
        ArrayList<User> allUsers = new ArrayList<>();

        try {
            PreparedStatement userStatement = connection.prepareStatement("Select * From users");
            ResultSet userResult = userStatement.executeQuery();

            while (userResult.next()) {
                User tmp = new User(userResult.getString(1), userResult.getString(2));
                allUsers.add(tmp);

            }


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return allUsers;
    }

    public User getData(String userEmail) {
        User tmp = null;

        try {
            PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM users Where useremail = ?");
            userStatement.setString(1, userEmail);

            ResultSet userResult = userStatement.executeQuery();
            if (userResult.next()) {

                tmp = new User(userResult.getString(1), userResult.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tmp;
    }

    public ArrayList<User> getAllTaskUsers(int taskID) {

        ArrayList<User> viewOverTaskUsers = new ArrayList<>();
        try {
            String taskQuery = "Select users.useremail, tasks.taskID, tasks.title, tasks.descriptions, tasks.startdate, tasks.deadline, tasks.estimatedtime From tasks Join user_tasks on tasks.taskID = user_tasks.taskID Join users on users.useremail = user_tasks.useremail Where tasks.taskID = ?";
            PreparedStatement taskStatement = connection.prepareStatement(taskQuery);
            taskStatement.setInt(1, taskID);
            ResultSet taskResult = taskStatement.executeQuery();

            while (taskResult.next()) {
                User tmp = new User(taskResult.getString(1), taskResult.getString(2));
                viewOverTaskUsers.add(tmp);
            }


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return viewOverTaskUsers;

    }


    public boolean validateData(String userEmail, String userPassword) {

        try {
            PreparedStatement userStatement = connection.prepareStatement
                    ("SELECT useremail, userpassword  FROM users WHERE useremail = ? and userpassword = ?");
            userStatement.setString(1, userEmail);
            userStatement.setString(2, userPassword);

            ResultSet userResult = userStatement.executeQuery();
            while (userResult.next()) {

                if (userResult.equals(userEmail) && userResult.equals(userPassword)) {
                }
                return true;
            }


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return false;
    }

    public void deleteData(String userEmail) {

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM users WHERE useremail = ?");
            preparedStatement1.setString(1, userEmail);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
    }
}
