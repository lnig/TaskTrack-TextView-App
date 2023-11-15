package org.example;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.View.userTextView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        new userTextView(screen);

        userTextView.printHomePage();
        userTextView.whichKeyClickedHomePage(terminal);
        userTextView.whichOptionIsChoosedMenu(terminal, 0);

//        userTextView.whichOptionIsChoosedProduct(terminal, 0);
//
//        userTextView.whichOptionIsChoosedTask(terminal, 0);
//
//        userTextView.whichOptionIsChoosedshopping(terminal, 0);

    }
}