package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Controller.familyMemberController;
import org.example.Controller.taskController;
import org.example.Controller.userInput;
import org.example.Model.Class.Task;
import org.example.Model.Class.familyMember;
import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.googlecode.lanterna.input.KeyType.Delete;

public class userTextView {

    private static Screen sc;
    private static final ArrayList<String> menuList = new ArrayList<>(Arrays.asList("Zadania", "Produkty", "Lista zakupów", "Wyjscie"));
    private static final ArrayList<String> taskMenuList = new ArrayList<>(Arrays.asList("Dodaj Zadanie", "Wyswietl Zadania", "Wroc"));
    private static final ArrayList<String> productMenuList = new ArrayList<>(Arrays.asList("Dodaj Produkt", "Usun Produkt", "Edytuj Produkt", "Wyswietl produkty", "Wroc"));
    private static final ArrayList<String> shoppingMenuList = new ArrayList<>(Arrays.asList("Stworz Liste Zakupow", "Usun Liste Zakupow", "Dodaj produkt do listy", "Wyswietl Liste", "Wroc"));

    public userTextView(Screen sc) {
        this.sc = sc;
    }

    //    ASCII
//    big, doom, grafitii, slant, soft, standard ||| electronic ||| chunky, ivrit, lean ||| shadow

    public static void whichKeyClickedHomePage(Terminal terminal) throws IOException, InterruptedException {

        boolean isRunning = true;

        while (isRunning) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Enter:
                        printAddFamilyMember(terminal);
                        isRunning = false;
                        break;
                    case Escape:
                        sc.close();
                        System.exit(0);
                        isRunning = false;
                        break;
                }
            }
        }
    }

    public static void whichOptionIsChoosedMenu(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape:
                        sc.close();
                        System.exit(0);
                        isRunningOption = false;
                        break;
                    case ArrowDown:
                        selected = (selected + 1) % menuList.size();
                        printMenu(selected);
                        break;
                    case ArrowUp:
                        selected = (selected - 1 + menuList.size()) % menuList.size();
                        printMenu(selected);
                        break;
                    case Enter:
                        optionChoosedMenu(terminal, selected);
                        isRunningOption= false;
                        break;
                    }
                }
            }
        }


    public static void optionChoosedMenu(Terminal terminal, int selected) throws IOException, InterruptedException {

        switch (selected) {
            case 0 -> {
                sc.clear();
                printTaskMenu(0);
                whichOptionIsChoosedTask(terminal, selected);
            }
            case 1 -> {
                sc.clear();
                printProductMenu(0);
                whichOptionIsChoosedProduct(terminal, selected);
            }
            case 2 -> {
                sc.clear();
                printShoppingMenu(0);
                whichOptionIsChoosedshopping(terminal, selected);
            }
            case 3 -> {
                sc.close();
                System.exit(0);
            }
            default -> {
            }
        }
    }

    public static void whichOptionIsChoosedTask(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.close();
                        System.exit(0);
                        isRunningOption = false;
                    }
                    case ArrowDown -> {
                        if (selected + 1 < taskMenuList.size()) {
                            printTaskMenu(++selected);
                        }
                    }
                    case ArrowUp -> {
                        if (selected - 1 >= 0) {
                            printTaskMenu(--selected);
                        }
                    }
                    case Enter -> {
                        optionChoosedTask(terminal, selected);
                        isRunningOption= false;
                    }
                }
            }
        }
    }
    public static void optionChoosedTask(Terminal terminal, int selected) throws IOException, InterruptedException {

        switch (selected) {
            case 0:
                sc.clear();
                String nameProd = userInput.getUserInput(terminal,sc,"Wprowadź nazwe i naciśnij Enter:");
                String catProd = userInput.getUserInput(terminal,sc,"Wprowadź nazwe i naciśnij Enter:");
                priorityType priority = userInput.chooseEnumValue("Wybierz priorytet: ", priorityType.values(), sc);
                statusType status = userInput.chooseEnumValue("Wybierz status: ", statusType.values(), sc);
                familyMember member = userInput.chooseValueFromList("Wybierz członka rodziny", familyMemberController.getFamilyMembers(), sc);
                if (nameProd != null && catProd != null) {
                    System.out.println("sssssssss");
                    System.out.println(nameProd + " " + catProd);
                    taskController.addTask(new Task(nameProd, catProd, priority, status, member));
                    printTaskMenu(0);
                    whichOptionIsChoosedTask(terminal, 0);
                }
                break;
            case 1:
                sc.clear();
                printAllTasks(0);
                whichTaskIsChoosed(terminal,selected);
                break;
            case 2:
                sc.clear();
                printMenu(0);
                whichOptionIsChoosedMenu(terminal, selected);
            default:
                break;

        }
    }

    public static void printAllTasks(int selected){
        sc.clear();
        TextGraphics printAllTasksGraphics = sc.newTextGraphics();


        ArrayList<Task> list1 = taskController.getTasks();
        for (int i = 0; i < list1.size(); i++) {
            if (i  == selected) {
                printAllTasksGraphics.setForegroundColor(TextColor.ANSI.RED);
            }else{
                printAllTasksGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            }
            printAllTasksGraphics.putString(1, i + 1, list1.get(i).getTitle() + " " + list1.get(i).getDescription() + " " +
                                                           list1.get(i).getStatus() + " " + list1.get(i).getPriority() + " " +
                                                            list1.get(i).getWhoWillDo());
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void whichTaskIsChoosed(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.close();
                        System.exit(0);
                        isRunningOption = false;
                    }
                    case ArrowDown -> {
                        selected = (selected + 1) % taskController.getTasks().size();
                        printAllTasks(selected);
                    }
                    case ArrowUp -> {
                        selected = (selected - 1 + taskController.getTasks().size()) % taskController.getTasks().size();
                        printAllTasks(selected);
                    }
                    case Delete -> {
                        sc.clear();
                        taskController.removeTask(selected);
                        printAllTasks(0);
                    }
                    case Enter -> {
                        sc.clear();
                        Task task = taskController.getTasks().get(selected);
                        System.out.println(selected);
                        whatIsChanged(terminal,taskController.getTasks().get(selected));
                        printAllTasks(0);
                    }
                }
            }
        }
    }

    public static void whatIsChanged(Terminal terminal, Task task) throws IOException, InterruptedException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Tytul", "Opis", "Status", "Priorytet", "Czlonek rodziny"));

        String t = userInput.chooseValueFromListString("Co chcesz zmienic?", list, sc);
        String newText = "";
        switch (t){
            case "Tytul" -> {
                newText = userInput.getUserInput(terminal, sc, "Podaj nowy tytul");
                task.setTitle(newText);
            }
            case "Opis"->{
                newText = userInput.getUserInput(terminal, sc, "Podaj nowy Opis");
                task.setDescription(newText);
            }
            case "Status" ->{
                statusType status = userInput.chooseEnumValue("Wybierz status: ", statusType.values(), sc);
                task.setStatus(status);
            }
            case "Priorytet"->{
                priorityType priority = userInput.chooseEnumValue("Wybierz priorytet: ", priorityType.values(), sc);
                task.setPriority(priority);
            }
            case "Czlonek rodziny"->{
                familyMember member = userInput.chooseValueFromList("Wybierz członka rodziny", familyMemberController.getFamilyMembers(), sc);
                task.setWhoWillDo(member);
            }
        }
    }

    public static void whichOptionIsChoosedProduct(Terminal terminal, int selected) throws IOException {
        boolean isRunningOption = true;
        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.close();
                        System.exit(0);
                        isRunningOption = false;
                    }
                    case ArrowDown -> {
                        if (selected + 1 < shoppingMenuList.size()) {
                            printProductMenu(++selected);
                        }
                    }
                    case ArrowUp -> {
                        if (selected - 1 >= 0) {
                            printProductMenu(--selected);
                        }
                    }
                    case Enter -> {
                        optionChoosedProduct(terminal, selected);
                        isRunningOption= false;
                    }
                }
            }
        }
    }
    public static void optionChoosedProduct(Terminal terminal, int selected) throws IOException {

        switch (selected) {
            case 0:
                sc.clear();

                break;
            case 1:
                sc.clear();

                break;
            case 2:
                sc.clear();

                break;
            case 3:
                sc.close();
                System.exit(0);
                break;
            default:
                break;

        }
    }

    public static void whichOptionIsChoosedshopping(Terminal terminal, int selected) throws IOException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.close();
                        System.exit(0);
                        isRunningOption = false;
                    }
                    case ArrowDown -> {
                        if (selected + 1 < shoppingMenuList.size()) {
                            printShoppingMenu(++selected);
                        }
                    }
                    case ArrowUp -> {
                        if (selected - 1 >= 0) {
                            printShoppingMenu(--selected);
                        }
                    }
                    case Enter -> {
                        optionChoosedShopping(terminal, selected);
                        isRunningOption= false;
                    }
                }
            }
        }
    }
    public static void optionChoosedShopping(Terminal terminal, int selected) throws IOException {

        switch (selected) {
            case 0:
                sc.clear();

                break;
            case 1:
                sc.clear();

                break;
            case 2:
                sc.clear();

                break;
            case 3:
                sc.close();
                System.exit(0);
                break;
            default:
                break;

        }
    }
    public static void printHomePage() {

        TextGraphics homePageGraphics = sc.newTextGraphics();
        int cols = getCols();
        int rows = getRows();

        String welcomeText1 = "Witaj w naszej aplikacji do zarządzania zadaniami domowymi!";
        String welcomeText2 = "Nasza aplikacja została stworzona z myślą o ułatwieniu Ci życia,";
        String welcomeText3 = "umożliwiając sprawną organizację codziennych zadań.";
        String thanksText = "Cieszymy się, że dołączyłeś do naszej społeczności.";

        String infoTextNext = "Kliknij Enter aby przejsc dalej";
        String infoTextBack = "lub Escape aby wyjsc";

        for (int i = 0; i < cols; i++) {
            homePageGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
            homePageGraphics.putString(i, 1, String.valueOf(Symbols.TRIANGLE_UP_POINTING_BLACK));
            homePageGraphics.putString(i, 10, String.valueOf(Symbols.TRIANGLE_DOWN_POINTING_BLACK));
        }

        homePageGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);

        printTitle();

        homePageGraphics.putString((cols - welcomeText1.length()) / 2, 12, welcomeText1, SGR.BOLD);
        homePageGraphics.putString((cols - welcomeText2.length()) / 2, 14, welcomeText2, SGR.BOLD);
        homePageGraphics.putString((cols - welcomeText3.length()) / 2, 15, welcomeText3, SGR.BOLD);

        homePageGraphics.putString((cols - infoTextNext.length()) / 2, 17, infoTextNext, SGR.BOLD);
        homePageGraphics.putString((cols - infoTextBack.length()) / 2, 18, infoTextBack, SGR.BOLD);

        homePageGraphics.putString((cols - thanksText.length()) / 2, rows - 2, thanksText, SGR.BOLD);

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTitle(){

        TextGraphics titleGraphics = sc.newTextGraphics();

        titleGraphics.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
        titleGraphics.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
        titleGraphics.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
        titleGraphics.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
        titleGraphics.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
        titleGraphics.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printAddFamilyMember(Terminal terminal) throws IOException, InterruptedException {

        int howMany = Integer.parseInt(String.valueOf(userInput.getUserInputFM(terminal, sc, "Podaj ilosc czlonkow", "Ilosc: ")));

        for(int i = 0; i < howMany; i++){
            System.out.println("sssssssss");
            String name = userInput.getUserInputFM(terminal, sc, "Podaj imie czlonka rodziny", "Imie: ");
            System.out.println(name);
            familyMemberController.addFamilyMember(new familyMember(name));
        }

        printMenu(0);

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void printMenu(int selected) {

        sc.clear();
        int cols = getCols();


        TextGraphics menuTextGraphics = sc.newTextGraphics();
        menuTextGraphics.setForegroundColor(TextColor.ANSI.GREEN);

        menuTextGraphics.putString(cols / 2 - 13, 4, "  __  __                  ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 5, " |  \\/  |                 ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 6, " | \\  / | ___ _ __  _   _ ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 7, " | |\\/| |/ _ \\ '_ \\| | | |", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 8, " | |  | |  __/ | | | |_| |", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 9, " |_|  |_|\\___|_| |_|\\__,_|", SGR.BOLD);

        for (int i = 0; i < menuList.size(); i++) {
            if (i  == selected) {
                menuTextGraphics.setForegroundColor(TextColor.ANSI.RED);
            }else{
                menuTextGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            }
            menuTextGraphics.putString((cols - menuList.get(i).length()) / 2, i + 11, menuList.get(i));
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTaskMenu(int selected){

        sc.clear();
        int cols = getCols();
        TextGraphics taskMenuGraphics = sc.newTextGraphics();

        taskMenuGraphics.putString(cols / 2 - 26,1, "███████╗ █████╗ ██████╗  █████╗ ███╗   ██╗██╗ █████╗ ");
        taskMenuGraphics.putString(cols / 2 - 26,2, "╚══███╔╝██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║██╔══██╗");
        taskMenuGraphics.putString(cols / 2 - 26,3, "  ███╔╝ ███████║██║  ██║███████║██╔██╗ ██║██║███████║");
        taskMenuGraphics.putString(cols / 2 - 26,4, " ███╔╝  ██╔══██║██║  ██║██╔══██║██║╚██╗██║██║██╔══██║");
        taskMenuGraphics.putString(cols / 2 - 26,5, "███████╗██║  ██║██████╔╝██║  ██║██║ ╚████║██║██║  ██║");
        taskMenuGraphics.putString(cols / 2 - 26,6, "╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═╝");

        for (int i = 0; i < taskMenuList.size(); i++) {
            if (i  == selected) {
                taskMenuGraphics.setForegroundColor(TextColor.ANSI.RED);
            }else{
                taskMenuGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            }
            taskMenuGraphics.putString((cols - taskMenuList.get(i).length()) / 2, i + 9, taskMenuList.get(i));
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printProductMenu(int selected){
        sc.clear();
        TextGraphics productMenuGraphics = sc.newTextGraphics();
        int cols = getCols();

        productMenuGraphics.putString(cols / 2 - 34,1, "██████╗ ██████╗  ██████╗ ██████╗ ██╗   ██╗██╗  ██╗████████╗██╗   ██╗");
        productMenuGraphics.putString(cols / 2 - 34,2, "██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██║   ██║██║ ██╔╝╚══██╔══╝╚██╗ ██╔╝");
        productMenuGraphics.putString(cols / 2 - 34,3, "██████╔╝██████╔╝██║   ██║██║  ██║██║   ██║█████╔╝    ██║    ╚████╔╝ ");
        productMenuGraphics.putString(cols / 2 - 34,4, "██╔═══╝ ██╔══██╗██║   ██║██║  ██║██║   ██║██╔═██╗    ██║     ╚██╔╝  ");
        productMenuGraphics.putString(cols / 2 - 34,5, "██║     ██║  ██║╚██████╔╝██████╔╝╚██████╔╝██║  ██╗   ██║      ██║   ");
        productMenuGraphics.putString(cols / 2 - 34,6, "╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝  ");

        for (int i = 0; i < productMenuList.size(); i++) {
            if (i  == selected) {
                productMenuGraphics.setForegroundColor(TextColor.ANSI.RED);
            }else{
                productMenuGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            }
            productMenuGraphics.putString((cols - productMenuList.get(i).length()) / 2, i + 9, productMenuList.get(i));
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printShoppingMenu(int selected){
        TextGraphics shoppingMenuGraphics = sc.newTextGraphics();
        int cols = getCols();

        shoppingMenuGraphics.putString(cols / 2 - 26,1, "███████╗ █████╗ ██╗  ██╗██╗   ██╗██████╗ ██╗   ██╗");
        shoppingMenuGraphics.putString(cols / 2 - 26,2, "╚══███╔╝██╔══██╗██║ ██╔╝██║   ██║██╔══██╗╚██╗ ██╔╝");
        shoppingMenuGraphics.putString(cols / 2 - 26,3, "  ███╔╝ ███████║█████╔╝ ██║   ██║██████╔╝ ╚████╔╝ ");
        shoppingMenuGraphics.putString(cols / 2 - 26,4, " ███╔╝  ██╔══██║██╔═██╗ ██║   ██║██╔═══╝   ╚██╔╝  ");
        shoppingMenuGraphics.putString(cols / 2 - 26,5, "███████╗██║  ██║██║  ██╗╚██████╔╝██║        ██║   ");
        shoppingMenuGraphics.putString(cols / 2 - 26,6, "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝        ╚═╝   ");

        for (int i = 0; i < shoppingMenuList.size(); i++) {
            if (i  == selected) {
                shoppingMenuGraphics.setForegroundColor(TextColor.ANSI.RED);
            }else{
                shoppingMenuGraphics.setForegroundColor(TextColor.ANSI.GREEN);
            }
            shoppingMenuGraphics.putString((cols - shoppingMenuList.get(i).length()) / 2, i + 9, shoppingMenuList.get(i));
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getCols() {
        return sc.getTerminalSize().getColumns();
    }

    public static int getRows() {
        return sc.getTerminalSize().getRows();
    }


}