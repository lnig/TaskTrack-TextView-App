package org.example.Model.Class;

import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.util.Date;

public class Task {

    private String title;

    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //    private priorityType priority;
//
//    private statusType status;
//
//    private Date whenNeedsToBeDone;
//
//    private User whoWillDo;


}
