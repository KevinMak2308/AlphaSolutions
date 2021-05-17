package gruppe5.alphasolutions.models;



import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



public class Project {
    private int projectID;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate deadline;


    public Project(int projectID, String title, String description, LocalDate startDate, LocalDate deadline) {
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /*public HashMap getAssignments() {
        return assignments;
    }

    public void setAssignments(HashMap assignments) {
        this.assignments = assignments;
    }*/


    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                //", assignments=" + assignments +
                '}';
    }
}
