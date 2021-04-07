package com.example.checklist;

import java.io.Serializable;

/**
 * Class represents a task
 */
public class Task implements Serializable {

    private String title;
    private String description;
    private boolean isChecked;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
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

    public boolean isChecked() { return isChecked; }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
