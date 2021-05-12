package gruppe5.alphasolutions.repositories;

import java.util.ArrayList;

public interface InterfaceRepository {

    abstract void sendDatatoDatabase();

    abstract ArrayList showAllData();

    abstract Object getData(String name);

    abstract boolean validateData();

    abstract void deleteData();

}
