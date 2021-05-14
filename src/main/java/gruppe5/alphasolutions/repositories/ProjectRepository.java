package gruppe5.alphasolutions.repositories;


import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class ProjectRepository implements InterfaceRepository {


    @Override
    public void sendDatatoDatabase(String name, String name2) {

    }

    @Override
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


        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } return allProjects;
    }

    @Override
    public Object getData(String Name) {
        return null;
    }

    @Override
    public boolean validateData(String projectID, String title) {
        return false;
    }

    @Override
    public void deleteData(String name) {

    }

}
