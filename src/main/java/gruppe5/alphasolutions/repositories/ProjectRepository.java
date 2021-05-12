package gruppe5.alphasolutions.repositories;


import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.models.User;

import java.sql.*;
import java.util.ArrayList;

public class ProjectRepository implements InterfaceRepository {


    @Override
    public void sendDatatoDatabase() {

    }

    @Override
    public ArrayList<Project> showAllData() {
        ArrayList<Project> allProjects = new ArrayList<>();

        try {
            Connection proConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphasolutioons", "root", "124578");
            PreparedStatement proStatement = proConnection.prepareStatement("Select * From projects");
            ResultSet proResult = proStatement.executeQuery();

            while(proResult.next()) {
                Project tmp = new Project(proResult.getString(1), proResult.getString(2), proResult.getString(3), proResult.getString(4));
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
    public boolean validateData() {
        return false;
    }

    @Override
    public void deleteData() {

    }
}
