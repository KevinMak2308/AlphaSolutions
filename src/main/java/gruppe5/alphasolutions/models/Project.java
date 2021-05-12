package gruppe5.alphasolutions.models;



import java.time.LocalDate;



public class Project {
    private String title;
    private String description;
    private String startDate;
    private String deadline;
    //private Map assignment;


    public Project(String title, String description, String startDate, String deadline) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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
