package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository implements InterfaceRepository{
    @Override
    public void sendDatatoDatabase() {
/*
        try {
            Connection makeUserConnection = DBManager.getConnection();
            PreparedStatement makeUserStatement = makeUserConnection.prepareStatement("INSERT INTO betaUsers(USERACCOUNTNAME, USERNAME, USERMAIL)" + "VALUES ('" + accountName + "', '" + name + "', '" + email + "')");
            makeUserStatement.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
*/
    }

    @Override
    public ArrayList<User> showAllData() {
        ArrayList<User> allUsers = new ArrayList<>();
        Connection connToEMP = null;
        try {
            connToEMP = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/testschema", "root", "1711771435");
            //Connection userConnection = DBManager.getConnection();
            PreparedStatement userStatement = connToEMP.prepareStatement("SELECT * FROM users");
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
        Connection connToEMP = null;
        try {
            connToEMP = DriverManager.getConnection
                    ("jdbc:mysql//localhost:3306/testschema", "root", "1234");
        //Connection accountConnection = DBManager.getConnection();
            PreparedStatement userStatement = connToEMP.prepareStatement("SELECT * FROM users Where useremail = ?");
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
    public boolean validateData() {
        return false;
    }

    @Override
    public void deleteData() {

    }
}
