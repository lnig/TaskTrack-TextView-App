package org.example.Controller;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.View.userTextView;

import java.io.IOException;

public class userInput {

    public static String getUserInput(Terminal terminal, Screen screen, String message) throws IOException, InterruptedException {

        TextGraphics userInputTextGraphics = screen.newTextGraphics();
        StringBuilder userInput = new StringBuilder();
        KeyStroke key;
        boolean isRunning = false;

        do{
            key = terminal.pollInput();

            if (key != null) {
                if (key.getKeyType() == KeyType.Backspace) {
                    if (userInput.length() > 0)
                        userInput.deleteCharAt(userInput.length() - 1);

                } else if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();

                    if (Character.isLetterOrDigit(c) && userInput.length() < 15)
                        userInput.append(c);
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (userInput.toString().trim().length() > 0) {
                        isRunning = true;
                    }
                } else if (key.getKeyType() == KeyType.Escape) {
                    userTextView.printMenu(0);
                    userTextView.optionChoosedMenu(terminal, 0);
                    return null;
                }
            }

            screen.clear();
//            TextView.printTitle();

            userInputTextGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
            if (message.length() > 40)
                userInputTextGraphics.putString(12, 12, message, SGR.BOLD);
            else
                userInputTextGraphics.putString(20, 12, message, SGR.BOLD);

            userInputTextGraphics.putString(25, 14, "NICK: ", SGR.BOLD);
            userInputTextGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
            userInputTextGraphics.putString(31, 14, String.valueOf(userInput), SGR.BOLD);
            userInputTextGraphics.putString(31 + String.valueOf(userInput).length(), 14, "|  ", SGR.BOLD, SGR.BLINK);

            screen.refresh();

            Thread.sleep(10);
        }while (!isRunning);

        userInputTextGraphics.putString(31 + String.valueOf(userInput).length(), 14, "  ");
        screen.refresh();

        return userInput.toString();

    }




}
