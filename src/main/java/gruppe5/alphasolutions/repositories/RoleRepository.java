package gruppe5.alphasolutions.repositories;

import gruppe5.alphasolutions.models.Roles;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RoleRepository {

    public ArrayList<Roles> getAllRoles(String userEmail) {
        ArrayList<Roles> getRoles = new ArrayList<>();
        try {
            Connection roleConn = DBManager.getConnection();
            PreparedStatement  roleStatement = roleConn.prepareStatement("Select roleID From users Inner Join user_roles ON users.useremail = user_roles.useremail Where users.useremail = ?");
            roleStatement.setString(1, userEmail);
            ResultSet roleResult = roleStatement.executeQuery();

            while (roleResult.next()) {
                Roles tmp = new Roles(roleResult.getInt(1), roleResult.getString(2));
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
            PreparedStatement roleStatement = roleConn.prepareStatement("INSERT INTO user_roles(roleID, useremail)" + "VALUES (" + roleID + "', '" + userEmail + "')");
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


}
