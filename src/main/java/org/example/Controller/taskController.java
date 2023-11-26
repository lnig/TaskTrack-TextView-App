package org.example.Controller;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Model.Class.Task;
import org.example.Model.Class.familyMember;
import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.util.ArrayList;

public class taskController {

    private static ArrayList<Task> listOfTask = new ArrayList<>();

    public static void addTask(Task task){
        listOfTask.add(task);
    }


    public static void removeTask(int selected){
            listOfTask.remove(selected);
    }

    public static ArrayList<Task> getTasks() {
        return listOfTask;
    }

}
