package org.example.Controller;

import org.example.Model.Class.Task;

import java.util.ArrayList;

public class taskController {

    private static ArrayList<Task> listOfTask = new ArrayList<>();

    public static void addTask(Task task){
        listOfTask.add(task);
    }

    public static int getSize(){
        return listOfTask.size();
    }

    public static ArrayList<Task> getTasks() {
        return listOfTask;
    }

}
