package gruppe5.alphasolutions.services;

import gruppe5.alphasolutions.models.Task;
import gruppe5.alphasolutions.models.Project;
import gruppe5.alphasolutions.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calculater {

    public void calc() {
        LocalDate date = LocalDate.parse("2020-03-11");
        System.out.print("Date : " + date);
        // Formatting Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String dateLocal = formatter.format(date);
        System.out.println("Date2 : " + dateLocal);
    }


    public void estimatedTime(Project project, Task assignment, User user) {

    }

}

