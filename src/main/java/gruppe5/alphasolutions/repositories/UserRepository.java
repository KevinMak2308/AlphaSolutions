package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository implements InterfaceRepository {

    @Override
    public void sendDatatoDatabase(String useremail, String userpassword) {

        try {
            Connection makeUserConnection = DBManager.getConnection();
            PreparedStatement makeUserStatement = makeUserConnection.prepareStatement("INSERT INTO users(useremail, userpassword)" + "VALUES ('" + useremail + "', '" + userpassword + "')");
            makeUserStatement.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
    }

    @Override
    public ArrayList<User> showAllData() {
        ArrayList<User> allUsers = new ArrayList<>();

        try {
            Connection userConnection = DBManager.getConnection();
            PreparedStatement userStatement = userConnection.prepareStatement("SELECT * FROM users");
            ResultSet userRS = userStatement.executeQuery();

            while (userRS.next()) {
                User tmp = new User(userRS.getInt(1), userRS.getString(2), userRS.getString(3));
                allUsers.add(tmp);
            }

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
        return allUsers;
    }

    @Override
    public User getData(String useremail) {
        User tmp = null;
        try {
            Connection accountConnection = DBManager.getConnection();
            PreparedStatement userStatement = accountConnection.prepareStatement("SELECT * FROM users Where useremail = ?");
            userStatement.setString(1, useremail);

            ResultSet accountRS = userStatement.executeQuery();
            if (accountRS.next()) {

                tmp = new User(accountRS.getInt(1), accountRS.getString(2), accountRS.getString(3));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tmp;
    }

    @Override
    public boolean validateData(String useremail, String password) {

        try {
            Connection accountConnection = DBManager.getConnection();
            PreparedStatement accountStatement = accountConnection.prepareStatement
                    ("SELECT USERACCOUNTNAME FROM users WHERE useremail = ? and password = ?");
            accountStatement.setString(1, useremail);
            accountStatement.setString(2, password);

            ResultSet accountRS = accountStatement.executeQuery();
            while (accountRS.next()) {

                if (accountRS.equals(useremail)) {
                }
                return true;
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return false;
    }

    @Override
    public void deleteData(String useremail) {
        try {
            Connection connection = DBManager.getConnection();
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM users WHERE useremail = ?");
            preparedStatement1.setString(1, useremail);
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