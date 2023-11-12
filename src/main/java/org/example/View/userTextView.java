package org.example.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class userTextView {

    private static Screen sc;

    public userTextView(Screen sc){
        this.sc = sc;
    }




//    ASCII
//    big, doom, grafitii, slant, soft, standard ||| electronic ||| chunky, ivrit, lean ||| shadow
    public static void printHomePage(){

        TextGraphics titleGraphics = sc.newTextGraphics();
        int col = sc.getTerminalSize().getColumns();

        for (int i = 0; i < col; i++){
            titleGraphics.setForegroundColor(TextColor.ANSI.RED_BRIGHT);
            titleGraphics.putString(i, 0, String.valueOf(Symbols.TRIANGLE_UP_POINTING_BLACK));
            titleGraphics.putString(i, 7, String.valueOf(Symbols.TRIANGLE_DOWN_POINTING_BLACK));
        }

        titleGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
        titleGraphics.putString(1, 1, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
        titleGraphics.putString(1, 2, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
        titleGraphics.putString(1, 3, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
        titleGraphics.putString(1, 4, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
        titleGraphics.putString(1, 5, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
        titleGraphics.putString(1, 6, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

        String welcomeText = "Witaj w naszej aplikacji";
        titleGraphics.putString((col - welcomeText.length())/2, 9, welcomeText, SGR.BOLD);

        titleGraphics.putString(1,1, "");







        try {
            sc.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void printMenu(){

        TextGraphics menuTextGraphics = sc.newTextGraphics();

        menuTextGraphics.putString(1, 10,"  __  __                  ", SGR.BOLD);
        menuTextGraphics.putString(1, 11," |  \\/  |                 ", SGR.BOLD);
        menuTextGraphics.putString(1, 12," | \\  / | ___ _ __  _   _ ");
        menuTextGraphics.putString(1, 13," | |\\/| |/ _ \\ '_ \\| | | |");
        menuTextGraphics.putString(1, 14," | |  | |  __/ | | | |_| |");
        menuTextGraphics.putString(1, 15," |_|  |_|\\___|_| |_|\\__,_|");


    }




}
