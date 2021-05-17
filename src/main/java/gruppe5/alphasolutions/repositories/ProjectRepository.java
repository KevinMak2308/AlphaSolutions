package gruppe5.alphasolutions.repositories;


import gruppe5.alphasolutions.models.Project;

import java.sql.*;
import java.util.ArrayList;

public class ProjectRepository {



    public void sendData(String name, String name2) {

    }


    public ArrayList<Project> showAllData() {
        ArrayList<Project> allProjects = new ArrayList<>();

        try {
            Connection proConnection = DBManager.getConnection();
            PreparedStatement proStatement = proConnection.prepareStatement("Select * From projects");
            ResultSet proResult = proStatement.executeQuery();

            while(proResult.next()) {
                Project tmp = new Project(proResult.getInt(1), proResult.getString(2), proResult.getString(3), proResult.getDate(4).toLocalDate(), proResult.getDate(5).toLocalDate());
                allProjects.add(tmp);
                System.out.println("Does it work?");

            }

            proStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } return allProjects;
    }


    public Project getData(String title) {
        Project tmp = null;

        try {
            Connection proConn = DBManager.getConnection();
            PreparedStatement projectStatement = proConn.prepareStatement("SELECT * FROM projects Where title = ?");
            projectStatement.setString(1, title);

            ResultSet projectResult = projectStatement.executeQuery();
            if (projectResult.next()) {

                tmp = new Project(projectResult.getInt(1), projectResult.getString(2), projectResult.getString(3), projectResult.getDate(4).toLocalDate(), projectResult.getDate(5).toLocalDate());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tmp;
    }


    public boolean validateData(String projectID, String title) {
        return false;
    }


    public void deleteData(String name) {

    }

}
