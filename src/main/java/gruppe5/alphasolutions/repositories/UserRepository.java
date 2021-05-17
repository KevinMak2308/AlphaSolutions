package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository implements InterfaceRepository {

    @Override
    public void sendData(String userEmail, String userPassword) {

        try {
            Connection userConnection = DBManager.getConnection();
            PreparedStatement userStatement = userConnection.prepareStatement("Insert into users(useremail, userpassword)" + "Values ('" + userEmail + "', '" + userPassword + "'");
            userStatement.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }

    }


    @Override
    public ArrayList<User> showAllData() {
        ArrayList<User> allUsers = new ArrayList<>();

        try {
            Connection userConnection = DBManager.getConnection();
            PreparedStatement userStatement = userConnection.prepareStatement("Select * From users");
            ResultSet userResult = userStatement.executeQuery();

            while(userResult.next()) {
                User tmp = new User(userResult.getString(1), userResult.getString(2));
                allUsers.add(tmp);
                System.out.println("Does it work? With a new user");

            }


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } return allUsers;
    }

    @Override
    public User getData(String userEmail) {
        User tmp = null;

        try {
            Connection userConn = DBManager.getConnection();
            PreparedStatement userStatement = userConn.prepareStatement("SELECT * FROM users Where useremail = ?");
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

    @Override
    public boolean validateData(String userEmail, String userPassword) {

        try {
            Connection userConn = DBManager.getConnection();
            PreparedStatement userStatement = userConn.prepareStatement
                    ("SELECT useremail, userpassword  FROM users WHERE useremail = ? and userpassword = ?");
            userStatement.setString(1, userEmail);
            userStatement.setString(2, userPassword);

            ResultSet userResult = userStatement.executeQuery();
            while (userResult.next()) {

                if (userResult.equals(userEmail) && userResult.equals(userPassword) ) {
                }
                return true;
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return false;
    }

    @Override
    public void deleteData(String userEmail) {

        try {

            Connection connection = DBManager.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM users WHERE useremail = ?");
            preparedStatement1.setString(1, userEmail);
            preparedStatement1.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
    }

    /*public boolean managerAcess() {

        Connection connToEMP = null;
        try {
            connToEMP = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testschema", "root", "1711771435");
            PreparedStatement accountStatement = connToEMP.prepareStatement
                    ("SELECT roleID FROM roles WHERE roleID = ?");
            accountStatement.setString(1, useremail);

            ResultSet accountRS = accountStatement.executeQuery();
            while (accountRS.next()) {

                if (accountRS.equals(useremail)) {
                }
                return true;
            }
        }
     }*/
    }
