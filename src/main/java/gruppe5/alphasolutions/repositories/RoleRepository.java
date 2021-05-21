package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.Roles;
import gruppe5.alphasolutions.models.User;


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



    public int checkRole(String useremail) {
        int tmp = 0;

        try {
            Connection userConn = DBManager.getConnection();
            String checkroleQuery = "Select roles.roleID From users Join user_roles ON users.useremail = user_roles.useremail Join roles On roles.roleID = user_roles.roleID Where users.useremail = ? ";
            PreparedStatement userStatement = userConn.prepareStatement(checkroleQuery);
            userStatement.setString(1, useremail);

            ResultSet rolesResult = userStatement.executeQuery();
            while (rolesResult.next())

                tmp = rolesResult.getInt(1);

            userStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tmp;
    }

}
