package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class userTextView {

    private static Screen sc;

    public userTextView(Screen sc) {
        this.sc = sc;
    }

    //    ASCII
//    big, doom, grafitii, slant, soft, standard ||| electronic ||| chunky, ivrit, lean ||| shadow
    public static void printHomePage() {

        TextGraphics titleGraphics = sc.newTextGraphics();
        int cols = getCols();
        int rows = getRows();

        String welcomeText1 = "Witaj w naszej aplikacji do zarządzania zadaniami domowymi!";
        String welcomeText2 = "Nasza aplikacja została stworzona z myślą o ułatwieniu Ci życia,";
        String welcomeText3 = "umożliwiając sprawną organizację codziennych zadań.";
        String thanksText = "Cieszymy się, że dołączyłeś do naszej społeczności.";

        String infoTextNext = "Kliknij Enter aby przejsc dalej";
        String infoTextBack = "lub Escape aby wyjsc";

        for (int i = 0; i < cols; i++) {
            titleGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
            titleGraphics.putString(i, 1, String.valueOf(Symbols.TRIANGLE_UP_POINTING_BLACK));
            titleGraphics.putString(i, 10, String.valueOf(Symbols.TRIANGLE_DOWN_POINTING_BLACK));
        }

        titleGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
        titleGraphics.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
        titleGraphics.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
        titleGraphics.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
        titleGraphics.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
        titleGraphics.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
        titleGraphics.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

        titleGraphics.putString((cols - welcomeText1.length()) / 2, 12, welcomeText1, SGR.BOLD);
        titleGraphics.putString((cols - welcomeText2.length()) / 2, 14, welcomeText2, SGR.BOLD);
        titleGraphics.putString((cols - welcomeText3.length()) / 2, 15, welcomeText3, SGR.BOLD);

        titleGraphics.putString((cols - infoTextNext.length()) / 2, 17, infoTextNext, SGR.BOLD);
        titleGraphics.putString((cols - infoTextBack.length()) / 2, 18, infoTextBack, SGR.BOLD);

        titleGraphics.putString((cols - thanksText.length()) / 2, rows - 2, thanksText, SGR.BOLD);

        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printMenu() {

        sc.clear();

        ArrayList<String> menuList = new ArrayList<>();
        menuList.addAll(Arrays.asList("Zadania", "Produkty","Wyjscie"));

        int cols = getCols();

        TextGraphics menuTextGraphics = sc.newTextGraphics();

        menuTextGraphics.putString(cols/2 - 13, 4, "  __  __                  ", SGR.BOLD);
        menuTextGraphics.putString(cols/2 - 13, 5, " |  \\/  |                 ", SGR.BOLD);
        menuTextGraphics.putString(cols/2 - 13, 6, " | \\  / | ___ _ __  _   _ ", SGR.BOLD);
        menuTextGraphics.putString(cols/2 - 13, 7, " | |\\/| |/ _ \\ '_ \\| | | |", SGR.BOLD);
        menuTextGraphics.putString(cols/2 - 13, 8, " | |  | |  __/ | | | |_| |", SGR.BOLD);
        menuTextGraphics.putString(cols/2 - 13, 9, " |_|  |_|\\___|_| |_|\\__,_|", SGR.BOLD);

//        menuTextGraphics.putString();


        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clickKey(Terminal terminal) throws IOException {

        Boolean isRunning = true;

        while (isRunning) {
            KeyStroke pressedKey = terminal.pollInput();
            if (pressedKey != null) {
                switch (pressedKey.getKeyType()) {
                    case Enter -> {
                        printMenu();
                        isRunning = false;
                    }
                    case Escape -> {
                        sc.close();
                        System.exit(0);
                        isRunning = false;
                    }
                }
            }
        }
    }


    public static int getCols() {
        return sc.getTerminalSize().getColumns();
    }

    public static int getRows() {
        return sc.getTerminalSize().getRows();
    }


}