package org.example.Model.Class;

import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.util.Date;

public class Task {

    private String title;

    private String description;

    private priorityType priority;

    private statusType status;

    private Date whenNeedsToBeDone;

    private familyMember whoWillDo;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, priorityType priority, statusType status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
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
}
