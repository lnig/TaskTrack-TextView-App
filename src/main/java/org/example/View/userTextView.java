package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Controller.*;
import org.example.Model.Class.Product;
import org.example.Model.Class.Shopping;
import org.example.Model.Class.Task;
import org.example.Model.Class.familyMember;
import org.example.Model.Type.measureType;
import org.example.Model.Type.priorityType;
import org.example.Model.Type.statusType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class userTextView {

    private static final ArrayList<String> menuList = new ArrayList<>(Arrays.asList("Zadania", "Produkty", "Lista zakupów", "Wyjscie"));
    private static final ArrayList<String> taskMenuList = new ArrayList<>(Arrays.asList("Dodaj Zadanie", "Wyswietl Zadania", "Wroc", "Zamknij aplikacje"));
    private static final ArrayList<String> productMenuList = new ArrayList<>(Arrays.asList("Dodaj Produkt", "Wyswietl produkty", "Wroc", "Zamknij aplikacje"));
    private static final ArrayList<String> shoppingMenuList = new ArrayList<>(Arrays.asList("Dodaj produkt do listy", "Wyswietl liste", "Wroc", "Zamknij aplikacje"));
    private static Screen sc;

    public userTextView(Screen sc) {
        this.sc = sc;
    }

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
                    case ArrowDown -> {
                        selected = (selected + 1) % menuList.size();
                        printMenu(selected);
                    }
                    case ArrowUp -> {
                        selected = (selected - 1 + menuList.size()) % menuList.size();
                        printMenu(selected);
                    }
                    case Enter -> {
                        optionChoosedMenu(terminal, selected);
                        isRunningOption = false;
                    }
                    }
                }
            }
        }
    public static void optionChoosedMenu(Terminal terminal, int selected) throws IOException, InterruptedException {

        switch (selected) {
            case 0 -> {
                sc.clear();
                printTaskMenu(0);
                selected = 0;
                whichOptionIsChoosedTask(terminal, selected);
            }
            case 1 -> {
                sc.clear();
                printProductMenu(0);
                selected = 0;
                whichOptionIsChoosedProduct(terminal, selected);
            }
            case 2 -> {
                sc.clear();
                printShoppingMenu(0);
                selected = 0;
                whichOptionIsChoosedShopping(terminal, selected);
            }
            case 3 -> {
                sc.close();
                System.exit(0);
            }
        }
    }
    public static void whichOptionIsChoosedTask(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case ArrowDown -> {
                        selected = (selected + 1) % taskMenuList.size();
                        printTaskMenu(selected);
                    }
                    case ArrowUp -> {
                        selected = (selected - 1 + taskMenuList.size()) % taskMenuList.size();
                        printTaskMenu(selected);
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
            case 0 -> {
                sc.clear();
                String taskName = userInput.getUserInput(terminal, sc, "Wprowadź tytul i naciśnij Enter:", "Tytul: ");
                String taskDescritpion = userInput.getUserInput(terminal, sc, "Wprowadź opis i naciśnij Enter:", "Opis: ");
                priorityType priority = userInput.chooseEnumValue("Wybierz priorytet i nacisnij Enter: ", priorityType.values(), sc);
                statusType status = userInput.chooseEnumValue("Wybierz status i nacisnij Enter: ", statusType.values(), sc);
                familyMember member = userInput.chooseValueFromList("Wybierz członka rodziny i nacisnij Enter", familyMemberController.getFamilyMembers(), sc);
                taskController.addTask(new Task(taskName, taskDescritpion, priority, status, member));
                printTaskMenu(0);
                selected = 0;
                whichOptionIsChoosedTask(terminal, selected);
            }
            case 1 -> {
                sc.clear();
                printAllTasks(0);
                selected = 0;
                whichTaskIsChoosed(terminal, selected);
            }
            case 2 -> {
                sc.clear();
                printMenu(0);
                selected = 0;
                whichOptionIsChoosedMenu(terminal, selected);
            }
            case 3 ->{
                sc.close();
                System.exit(0);
            }

        }
    }
    public static void printAllTasks(int selected){
        sc.clear();
        int cols = getCols();
        int rows = getRows();
        TextGraphics printAllTasksGraphics = sc.newTextGraphics();

        String infoText1 = "Ponizej wyswietlone sa wszytkie zadania";
        String infoText2 = "Jesli chcesz usunac jakies zadanie kliknij Delete";
        String infoText3 = "lub Enter jesli chcesz cos zedytowac";
        String infoText4 = "Kliknij Escape jesli chcesz sie cofnac";
        String infoText5 = "Tytul | Opis | Status | Priorytet | Przypisany Czlonek";

        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(85, 171, 79));
        printAllTasksGraphics.putString((cols - infoText1.length()) / 2, 1, infoText1, SGR.BOLD);

        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllTasksGraphics.putString((cols - infoText2.length()) / 2, 2, infoText2, SGR.BOLD);
        int indexDel = infoText2.indexOf("Delete");
        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllTasksGraphics.putString(indexDel + 15, 2, "Delete", SGR.BOLD, SGR.BLINK);

        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllTasksGraphics.putString((cols - infoText3.length()) / 2, 3, infoText3, SGR.BOLD);
        int indexEnt = infoText3.indexOf("Enter");
        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllTasksGraphics.putString((cols - "Enter".length()) / 2 - indexEnt - 7, 3, "Enter", SGR.BOLD, SGR.BLINK);

        printAllTasksGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        printAllTasksGraphics.putString(1, 5, infoText5, SGR.BOLD);

        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllTasksGraphics.putString((cols - infoText4.length()) / 2, rows - 2, infoText4, SGR.BOLD);
        int indexEsc = infoText4.indexOf("Escape");
        printAllTasksGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllTasksGraphics.putString((cols - "Escape".length()) / 2 - indexEsc, rows - 2, "Escape", SGR.BOLD, SGR.BLINK);

        ArrayList<Task> list1 = taskController.getTasks();
        for (int i = 0; i < list1.size(); i++) {
            if (i  == selected) {
                printAllTasksGraphics.setForegroundColor(new TextColor.RGB(255, 183, 3));
            }else{
                printAllTasksGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            printAllTasksGraphics.putString(1, i + 7, list1.get(i).getTitle() + " | " + list1.get(i).getDescription() + " | " +
                                                           list1.get(i).getStatus() + " | " + list1.get(i).getPriority() + " | " +
                                                            list1.get(i).getWhoWillDo(), SGR.BOLD);
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
                            sc.clear();
                            printTaskMenu(0);
                            selected = 0;
                            whichOptionIsChoosedTask(terminal, selected);
                        }
                        case ArrowDown -> {
                            if (taskController.getTasks().size() > 0) {
                                selected = (selected + 1) % taskController.getTasks().size();
                                printAllTasks(selected);
                            }
                        }
                        case ArrowUp -> {
                            if (taskController.getTasks().size() > 0) {
                                selected = (selected - 1 + taskController.getTasks().size()) % taskController.getTasks().size();
                                printAllTasks(selected);
                            }
                        }
                        case Delete -> {
                            if (taskController.getTasks().size() > 0) {
                                sc.clear();
                                taskController.removeTask(selected);
                                printAllTasks(0);
                            }
                        }
                        case Enter -> {
                            if (taskController.getTasks().size() > 0) {
                                sc.clear();
                                whatIsChanged(terminal, taskController.getTasks().get(selected));
                                printAllTasks(0);
                            }
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
                newText = userInput.getUserInput(terminal, sc, "Podaj nowy tytul", "Nowy Tytul: ");
                task.setTitle(newText);
            }
            case "Opis"->{
                newText = userInput.getUserInput(terminal, sc, "Podaj nowy Opis", "Nowy Opis: ");
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

        printTitle();

        homePageGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        homePageGraphics.putString((cols - welcomeText1.length()) / 2, 12, welcomeText1, SGR.BOLD);

        homePageGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        homePageGraphics.putString((cols - welcomeText2.length()) / 2, 14, welcomeText2, SGR.BOLD);
        homePageGraphics.putString((cols - welcomeText3.length()) / 2, 15, welcomeText3, SGR.BOLD);

        homePageGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        homePageGraphics.putString((cols - infoTextNext.length()) / 2, 17, infoTextNext, SGR.BOLD);
        int indexEnt = infoTextNext.indexOf("Enter");
        homePageGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        homePageGraphics.putString((cols - "Enter".length()) / 2 - indexEnt + 3, 17, "Enter", SGR.BOLD, SGR.BLINK);

        homePageGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        homePageGraphics.putString((cols - infoTextBack.length()) / 2, 18, infoTextBack, SGR.BOLD);
        int indexEsc = infoTextNext.indexOf("Escape");
        homePageGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        homePageGraphics.putString((cols - "Escape".length()) / 2 - indexEsc - 4, 18, "Escape", SGR.BOLD, SGR.BLINK);

        homePageGraphics.setForegroundColor(new TextColor.RGB(237, 237, 237));
        homePageGraphics.putString((cols - thanksText.length()) / 2, rows - 2, thanksText, SGR.BOLD);

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printTitle(){

        TextGraphics titleGraphics = sc.newTextGraphics();
        int cols = getCols();

        titleGraphics.setForegroundColor(new TextColor.RGB(56, 4, 14));
        for (int i = 0; i < cols; i++) {
            titleGraphics.putString(i, 1, String.valueOf(Symbols.BLOCK_SOLID));
            titleGraphics.putString(i, 10, String.valueOf(Symbols.BLOCK_SOLID));
        }

        titleGraphics.setForegroundColor(new TextColor.RGB(122, 28, 36));
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

        sc.clear();
        int howMany = Integer.parseInt(userInput.getUserInputFM(terminal, sc, "Podaj ilosc czlonkow rodziny i nacisnij Enter", "Ilosc: ", 0));

        for(int i = 0; i < howMany; i++){
            String name = userInput.getUserInputFM(terminal, sc, "Podaj imie czlonka rodziny i nacisnij Enter", "Imie: ", 1);
            familyMemberController.addFamilyMember(new familyMember(name));
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMenu(0);
    }
    public static void printMenu(int selected) {

        sc.clear();
        int cols = getCols();

        TextGraphics menuTextGraphics = sc.newTextGraphics();
        menuTextGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));

        menuTextGraphics.putString(cols / 2 - 13, 4, "  __  __                  ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 5, " |  \\/  |                 ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 6, " | \\  / | ___ _ __  _   _ ", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 7, " | |\\/| |/ _ \\ '_ \\| | | |", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 8, " | |  | |  __/ | | | |_| |", SGR.BOLD);
        menuTextGraphics.putString(cols / 2 - 13, 9, " |_|  |_|\\___|_| |_|\\__,_|", SGR.BOLD);

        for (int i = 0; i < menuList.size(); i++) {
            if (i  == selected) {
                menuTextGraphics.setForegroundColor(new TextColor.RGB(122, 28, 36));
            }else{
                menuTextGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            menuTextGraphics.putString((cols - menuList.get(i).length()) / 2, i + 12, menuList.get(i), SGR.BOLD);
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

        taskMenuGraphics.setForegroundColor(new TextColor.RGB(233, 196, 106));
        taskMenuGraphics.putString(cols / 2 - 26,3, "███████╗ █████╗ ██████╗  █████╗ ███╗   ██╗██╗ █████╗ ");
        taskMenuGraphics.putString(cols / 2 - 26,4, "╚══███╔╝██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║██╔══██╗");
        taskMenuGraphics.putString(cols / 2 - 26,5, "  ███╔╝ ███████║██║  ██║███████║██╔██╗ ██║██║███████║");
        taskMenuGraphics.putString(cols / 2 - 26,6, " ███╔╝  ██╔══██║██║  ██║██╔══██║██║╚██╗██║██║██╔══██║");
        taskMenuGraphics.putString(cols / 2 - 26,7, "███████╗██║  ██║██████╔╝██║  ██║██║ ╚████║██║██║  ██║");
        taskMenuGraphics.putString(cols / 2 - 26,8, "╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═╝");

        for (int i = 0; i < taskMenuList.size(); i++) {
            if (i  == selected) {
                taskMenuGraphics.setForegroundColor(new TextColor.RGB(255, 183, 3));
            }else{
                taskMenuGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            taskMenuGraphics.putString((cols - taskMenuList.get(i).length()) / 2, i + 13, taskMenuList.get(i), SGR.BOLD);
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

        productMenuGraphics.setForegroundColor(new TextColor.RGB(120, 165, 173));
        productMenuGraphics.putString(cols / 2 - 34,3, "██████╗ ██████╗  ██████╗ ██████╗ ██╗   ██╗██╗  ██╗████████╗██╗   ██╗");
        productMenuGraphics.putString(cols / 2 - 34,4, "██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██║   ██║██║ ██╔╝╚══██╔══╝╚██╗ ██╔╝");
        productMenuGraphics.putString(cols / 2 - 34,5, "██████╔╝██████╔╝██║   ██║██║  ██║██║   ██║█████╔╝    ██║    ╚████╔╝ ");
        productMenuGraphics.putString(cols / 2 - 34,6, "██╔═══╝ ██╔══██╗██║   ██║██║  ██║██║   ██║██╔═██╗    ██║     ╚██╔╝  ");
        productMenuGraphics.putString(cols / 2 - 34,7, "██║     ██║  ██║╚██████╔╝██████╔╝╚██████╔╝██║  ██╗   ██║      ██║   ");
        productMenuGraphics.putString(cols / 2 - 34,8, "╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝  ");

        for (int i = 0; i < productMenuList.size(); i++) {
            if (i  == selected) {
                productMenuGraphics.setForegroundColor(new TextColor.RGB(120, 165, 173));
            }else{
                productMenuGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            productMenuGraphics.putString((cols - productMenuList.get(i).length()) / 2, i + 13, productMenuList.get(i), SGR.BOLD);
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void whichOptionIsChoosedProduct(Terminal terminal, int selected) throws IOException, InterruptedException {
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
                        selected = (selected + 1) % productMenuList.size();
                        printProductMenu(selected);
                    }
                    case ArrowUp -> {
                        selected = (selected - 1 + productMenuList.size()) % productMenuList.size();
                        printProductMenu(selected);
                    }
                    case Enter -> {
                        optionChoosedProduct(terminal, selected);
                        isRunningOption= false;
                    }
                }
            }
        }
    }
    public static void optionChoosedProduct(Terminal terminal, int selected) throws IOException, InterruptedException {

        switch (selected) {
            case 0 -> {
                sc.clear();
                String nameProd = userInput.getUserInput(terminal, sc, "Wprowadź nazwe produktu i nacisnij Enter", "Nazwa: ");
                String catProd = userInput.getUserInput(terminal, sc, "Wprowadź kategorie produktu i naciśnij Enter:", "Kategoria: ");
                double price = Double.parseDouble(userInput.getUserInputFM(terminal, sc, "Podaj cene produktu i nacisnij Enter", "Cena: ", 2));
                price*=100;
                price = Math.round(price);
                price/=100;
                measureType type = userInput.chooseEnumValue("Wybierz jednostke miary i nacisnij Enter: ", measureType.values(), sc);
                productController.addProduct(new Product(nameProd, catProd, type, price));
                printProductMenu(0);
                selected = 0;
                whichOptionIsChoosedProduct(terminal, selected);
            }
            case 1 -> {
                sc.clear();
                printAllProducts(0);
                selected = 0;
                whichProductIsChoosed(terminal, selected);
            }
            case 2 -> {
                sc.clear();
                printMenu(0);
                selected = 0;
                whichOptionIsChoosedMenu(terminal, selected);
            }
            case 3 ->{
                sc.close();
                System.exit(0);
            }
        }
    }
    public static void printAllProducts(int selected){

        sc.clear();
        int cols = getCols();
        int rows = getRows();
        TextGraphics printAllProductsGraphics = sc.newTextGraphics();

        String infoText1 = "Ponizej wyswietlone sa wszytkie produkty";
        String infoText2 = "Jesli chcesz usunac jakis produkt kliknij Delete";
        String infoText3 = "lub Enter jesli chcesz cos zedytowac";
        String infoText4 = "Kliknij Escape jesli chcesz sie cofnac";
        String infoText5 = "Nazwa | Kategoria | Cena | Jednostka Miary";

        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(85, 171, 79));
        printAllProductsGraphics.putString((cols - infoText1.length()) / 2, 1, infoText1, SGR.BOLD);

        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllProductsGraphics.putString((cols - infoText2.length()) / 2, 2, infoText2, SGR.BOLD);
        int indexDel = infoText2.indexOf("Delete");
        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllProductsGraphics.putString(indexDel + 16, 2, "Delete", SGR.BOLD, SGR.BLINK);

        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllProductsGraphics.putString((cols - infoText3.length()) / 2, 3, infoText3, SGR.BOLD);
        int indexEnt = infoText3.indexOf("Enter");
        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllProductsGraphics.putString((cols - "Enter".length()) / 2 - indexEnt - 7, 3, "Enter", SGR.BOLD, SGR.BLINK);

        printAllProductsGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        printAllProductsGraphics.putString(1, 5, infoText5, SGR.BOLD);

        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllProductsGraphics.putString((cols - infoText4.length()) / 2, rows - 2, infoText4, SGR.BOLD);
        int indexEsc = infoText4.indexOf("Escape");
        printAllProductsGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllProductsGraphics.putString((cols - "Escape".length()) / 2 - indexEsc, rows - 2, "Escape", SGR.BOLD, SGR.BLINK);

        ArrayList<Product> list1 = productController.getProducts();
        for (int i = 0; i < list1.size(); i++) {
            if (i  == selected) {
                printAllProductsGraphics.setForegroundColor(new TextColor.RGB(120, 165, 173));
            }else{
                printAllProductsGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            printAllProductsGraphics.putString(1, i + 7, list1.get(i).getName() + " | " + list1.get(i).getCategory() + " | " +
                    list1.get(i).getPrice() + " | " + list1.get(i).getMeasureType(), SGR.BOLD);
        }

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void whichProductIsChoosed(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.clear();
                        printProductMenu(0);
                        selected = 0;
                        whichOptionIsChoosedProduct(terminal, selected);
                    }
                    case ArrowDown -> {
                        if (productController.getProducts().size() > 0) {
                            selected = (selected + 1) % productController.getProducts().size();
                            printAllProducts(selected);
                        }
                    }
                    case ArrowUp -> {
                        if (productController.getProducts().size() > 0) {
                            selected = (selected - 1 + productController.getProducts().size()) % productController.getProducts().size();
                            printAllProducts(selected);
                        }
                    }
                    case Delete -> {
                        if (productController.getProducts().size() > 0) {
                            sc.clear();
                            productController.removeProduct(selected);
                            printAllProducts(0);
                        }
                    }
                    case Enter -> {
                        if (productController.getProducts().size() > 0) {
                            sc.clear();
                            whatIsChangedProduct(terminal, productController.getProducts().get(selected));
                            printAllProducts(0);
                        }
                    }
                }
            }
        }
    }
    public static void whatIsChangedProduct(Terminal terminal, Product product) throws IOException, InterruptedException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Nazwa", "Kategoria", "Cena", "Jednostka miary"));

        String t = userInput.chooseValueFromListString("Co chcesz zmienic?", list, sc);
        String newText = "";
        switch (t){
            case "Nazwa" -> {
                newText = userInput.getUserInput(terminal, sc, "Podaj nowa nazwe", "Nowa Nazwa: ");
                product.setName(newText);
            }
            case "Kategoria"->{
                newText = userInput.getUserInput(terminal, sc, "Podaj nowa kategorie", "Nowa Kategoria: ");
                product.setCategory(newText);
            }
            case "Cena" ->{
                double price = Double.parseDouble(userInput.getUserInputFM(terminal, sc, "Podaj nowa cene produktu i nacisnij Enter", " Nowa Cena: ", 2));
                price*=100;
                price = Math.round(price);
                price/=100;
                product.setPrice(price);
            }
            case "Jednostka miary"->{
                measureType newMeasureType = userInput.chooseEnumValue("Wybierz priorytet: ", measureType.values(), sc);
                product.setMeasureType(newMeasureType);
            }
        }
    }
    public static void whichOptionIsChoosedShopping(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case ArrowDown -> {
                        selected = (selected + 1) % shoppingMenuList.size();
                        printShoppingMenu(selected);
                    }
                    case ArrowUp -> {
                        selected = (selected - 1 + shoppingMenuList.size()) % shoppingMenuList.size();
                        printShoppingMenu(selected);
                    }
                    case Enter -> {
                        optionChoosedShopping(terminal, selected);
                        isRunningOption= false;
                    }
                }
            }
        }
    }
    public static void optionChoosedShopping(Terminal terminal, int selected) throws IOException, InterruptedException {

        switch (selected) {
            case 0 -> {
                if (productController.getProducts().size() > 0) {
                    sc.clear();
                    Product product = userInput.chooseValueFromListProd("Wybierz produkt do dodania", productController.getProducts(), sc);
                    int amount = Integer.parseInt(userInput.getUserInputFM(terminal, sc, "Podaj Ilosc i nacisnij Enter", "Ilosc: ", 0));
                    shoppingController.addShopping(new Shopping(product, amount));
                    printShoppingMenu(0);
                    selected = 0;
                    whichOptionIsChoosedShopping(terminal, selected);
                }
                whichOptionIsChoosedShopping(terminal, selected);
            }
            case 1 -> {
                sc.clear();
                printAllProductsToBuy(0);
                selected = 0;
                whichProductIsChoosedToBuy(terminal, selected);
            }
            case 2 -> {
                sc.clear();
                printMenu(0);
                selected = 0;
                whichOptionIsChoosedMenu(terminal, selected);
            }
            case 3 -> {
                sc.close();
                System.exit(0);
            }
        }
    }

    public static void whichProductIsChoosedToBuy(Terminal terminal, int selected) throws IOException, InterruptedException {

        boolean isRunningOption = true;

        while (isRunningOption) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Escape -> {
                        sc.clear();
                        printShoppingMenu(0);
                        selected = 0;
                        whichOptionIsChoosedShopping(terminal, selected);
                    }
                    case ArrowDown -> {
                        if (shoppingController.getShopping().size() > 0) {
                            selected = (selected + 1) % shoppingController.getShopping().size();
                            printAllProductsToBuy(0);
                        }
                    }
                    case ArrowUp -> {
                        if (shoppingController.getShopping().size() > 0) {
                            selected = (selected - 1 + shoppingController.getShopping().size()) % shoppingController.getShopping().size();
                            printAllProductsToBuy(0);
                        }
                    }
                    case Delete -> {
                        if (shoppingController.getShopping().size() > 0) {
                            sc.clear();
                            shoppingController.removeShopping(selected);
                            printAllProductsToBuy(0);
                        }
                    }
                }
            }
        }
    }

    public static void printAllProductsToBuy(int selected){

        sc.clear();
        int cols = getCols();
        int rows = getRows();
        double sum = 0;
        TextGraphics printAllProductsTBGraphics = sc.newTextGraphics();

        String infoText1 = "Ponizej wyswietlone sa wszytkie produkty do kupienia";
        String infoText2 = "Jesli chcesz usunac jakis produkt kliknij Delete";
        String infoText3 = "Kliknij Escape jesli chcesz sie cofnac";
        String infoText4 = "Nazwa Produktu | Cena | Ilosc";

        printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(85, 171, 79));
        printAllProductsTBGraphics.putString((cols - infoText1.length()) / 2, 1, infoText1, SGR.BOLD);

        printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllProductsTBGraphics.putString((cols - infoText2.length()) / 2, 2, infoText2, SGR.BOLD);
        int indexDel = infoText2.indexOf("Delete");
        printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllProductsTBGraphics.putString(indexDel + 16, 2, "Delete", SGR.BOLD, SGR.BLINK);

        printAllProductsTBGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        printAllProductsTBGraphics.putString(1, 5, infoText4, SGR.BOLD);

        printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
        printAllProductsTBGraphics.putString((cols - infoText3.length()) / 2, rows - 2, infoText3, SGR.BOLD);
        int indexEsc = infoText3.indexOf("Escape");
        printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(62, 214, 49));
        printAllProductsTBGraphics.putString((cols - "Escape".length()) / 2 - indexEsc, rows - 2, "Escape", SGR.BOLD, SGR.BLINK);

        ArrayList<Shopping> list1 = shoppingController.getShopping();

        for (int i = 0; i < list1.size(); i++) {
            if (i  == selected) {
                printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(120, 165, 173));
            }else{
                printAllProductsTBGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            printAllProductsTBGraphics.putString(1, i + 7, list1.get(i).getProduct().getName() + " | " + list1.get(i).getProduct().getPrice()+ " | " +
                    list1.get(i).getAmount() , SGR.BOLD);
            sum += list1.get(i).getProduct().getPrice() * list1.get(i).getAmount();
        }
        sum *= 100;
        sum = Math.round(sum);
        sum /= 100;

        printAllProductsTBGraphics.putString(2, rows - 4, "Suma: " + sum, SGR.BOLD);

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void printShoppingMenu(int selected){

        sc.clear();
        TextGraphics shoppingMenuGraphics = sc.newTextGraphics();
        int cols = getCols();

        shoppingMenuGraphics.setForegroundColor(new TextColor.RGB(145, 95, 150));
        shoppingMenuGraphics.putString(cols / 2 - 26,3, "███████╗ █████╗ ██╗  ██╗██╗   ██╗██████╗ ██╗   ██╗");
        shoppingMenuGraphics.putString(cols / 2 - 26,4, "╚══███╔╝██╔══██╗██║ ██╔╝██║   ██║██╔══██╗╚██╗ ██╔╝");
        shoppingMenuGraphics.putString(cols / 2 - 26,5, "  ███╔╝ ███████║█████╔╝ ██║   ██║██████╔╝ ╚████╔╝ ");
        shoppingMenuGraphics.putString(cols / 2 - 26,6, " ███╔╝  ██╔══██║██╔═██╗ ██║   ██║██╔═══╝   ╚██╔╝  ");
        shoppingMenuGraphics.putString(cols / 2 - 26,7, "███████╗██║  ██║██║  ██╗╚██████╔╝██║        ██║   ");
        shoppingMenuGraphics.putString(cols / 2 - 26,8, "╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝        ╚═╝   ");

        for (int i = 0; i < shoppingMenuList.size(); i++) {
            if (i  == selected) {
                shoppingMenuGraphics.setForegroundColor(new TextColor.RGB(145, 95, 150));
            }else{
                shoppingMenuGraphics.setForegroundColor(new TextColor.RGB(55, 97, 52));
            }
            shoppingMenuGraphics.putString((cols - shoppingMenuList.get(i).length()) / 2, i + 12, shoppingMenuList.get(i), SGR.BOLD);
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