package org.example.Model.Class;

import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.util.Date;

public class Task {

    private String title;
    private String description;
    private priorityType priority;
    private statusType status;
    private familyMember whoWillDo;

    public Task(String title, String description, priorityType priority, statusType status, familyMember whoWillDo) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.whoWillDo = whoWillDo;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public priorityType getPriority() {
        return priority;
    }
    public statusType getStatus() {
        return status;
    }
    public String getWhoWillDo() {
        return whoWillDo.getName();
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(priorityType priority) {
        this.priority = priority;
    }
    public void setStatus(statusType status) {
        this.status = status;
    }
    public void setWhoWillDo(familyMember whoWillDo) {
        this.whoWillDo = whoWillDo;
    }
}
