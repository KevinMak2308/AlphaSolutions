package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.Roles;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RoleRepository {

    public ArrayList<Roles> getAllRoles() {
        ArrayList<Roles> getRoles = new ArrayList<>();
        try {
            Connection roleConn = DBManager.getConnection();
            PreparedStatement  roleStatement = roleConn.prepareStatement("Select users.useremail, roles.roleID, roles.rolename From users Join user_roles ON users.useremail = user_roles.useremail Join roles On roles.roleID = user_roles.roleID");
            ResultSet roleResult = roleStatement.executeQuery();

            while (roleResult.next()) {
                Roles tmp = new Roles(roleResult.getInt(2), roleResult.getString(3),roleResult.getString(1));
                getRoles.add(tmp);
            }

            roleStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());

        }
        return getRoles;
    }

    public void assignRole(int roleID, String userEmail) {

        try {
            Connection roleConn = DBManager.getConnection();
            String query = "INSERT INTO user_roles(roleID, useremail) VALUES (" + roleID + ", '" + userEmail + "')";
            PreparedStatement roleStatement = roleConn.prepareStatement(query);
            roleStatement.executeUpdate();

        } catch (SQLException error) {
            System.out.printf(error.getMessage());
        }
    }


    public ArrayList<Roles> getUserRole(String userEmail) {
        ArrayList<Roles> allRoles;
        ArrayList<Roles> userRoles = new ArrayList<>();
        allRoles = getAllRoles(userEmail);
        for (int i = 0; i < allRoles.size(); i++) {
            userRoles.add(allRoles.get(i));
        }
        return userRoles;
    }

    public boolean isManager(int roleID) {

        try {
            Connection userConn = DBManager.getConnection();
            PreparedStatement userStatement = userConn.prepareStatement
                    ("SELECT roleID  FROM roles WHERE roleID = ?");
            userStatement.setInt(1, roleID);

            ResultSet userResult = userStatement.executeQuery();
            while (userResult.next()) {

                if (userResult.equals(1)) {
                }
                userStatement.close();
                return true;
            }

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }

        return false;
    }

}
