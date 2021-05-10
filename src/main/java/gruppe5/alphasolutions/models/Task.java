package gruppe5.alphasolutions.models;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;

    public Task(String title, String description, LocalDate startDate, LocalDate deadline) {
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
}
