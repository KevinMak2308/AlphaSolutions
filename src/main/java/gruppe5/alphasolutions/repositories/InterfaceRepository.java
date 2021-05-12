package gruppe5.alphasolutions.repositories;

import java.util.ArrayList;

public interface InterfaceRepository {

    abstract void sendDatatoDatabase(String name, String name2);

    abstract ArrayList showAllData();

    abstract Object getData(String name);

    abstract boolean validateData(String name, String name2);

    abstract void deleteData(String name);

}
