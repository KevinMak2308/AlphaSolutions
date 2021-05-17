package gruppe5.alphasolutions.repositories;

import java.util.ArrayList;

public interface InterfaceRepository {

    void sendData(String s, String d);

    ArrayList showAllData();

    Object getData(String s);

    boolean validateData(String s, String d);

    void deleteData(String s);

}
