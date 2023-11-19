package org.example.Controller;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.View.userTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static String getUserInputFM(Terminal terminal, Screen screen, String message, String mess2) throws IOException, InterruptedException {

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

            userInputTextGraphics.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
            userInputTextGraphics.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
            userInputTextGraphics.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
            userInputTextGraphics.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
            userInputTextGraphics.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
            userInputTextGraphics.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

            userInputTextGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
            if (message.length() > 40)
                userInputTextGraphics.putString(12, 12, message, SGR.BOLD);
            else
                userInputTextGraphics.putString(20, 12, message, SGR.BOLD);

            userInputTextGraphics.putString(25, 14, mess2, SGR.BOLD);
            userInputTextGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
            userInputTextGraphics.putString(31, 14, String.valueOf(userInput), SGR.BOLD);
            userInputTextGraphics.putString(31 + String.valueOf(userInput).length(), 14, " | ", SGR.BOLD, SGR.BLINK);

            screen.refresh();

            Thread.sleep(10);
        }while (!isRunning);


        userInputTextGraphics.putString(31 + String.valueOf(userInput).length(), 14, "  ");
        screen.refresh();

        return userInput.toString();
    }

    public static <T extends Enum<T>> T chooseEnumValue(String prompt, T[] values, Screen screen) throws IOException {

        TextGraphics g = screen.newTextGraphics();
        int selectedIndex = 0;
        int cols = screen.getTerminalSize().getColumns();

        while (true) {
            screen.clear();
            screen.doResizeIfNecessary();
            drawText(prompt, (cols - prompt.length())/2, 7, screen);

            for (int i = 0; i < values.length; i++) {
                if (i == selectedIndex) {
                    g.setForegroundColor(TextColor.ANSI.RED);
                } else {
                    g.setForegroundColor(TextColor.ANSI.GREEN);

                }
                g.putString((cols - values[i].name().length()) / 2, i + 9, values[i].name());
            }

            screen.refresh();

            KeyStroke key;
            try {
                do {
                    key = screen.readInput();
                } while (key == null);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            if (key.getKeyType() == KeyType.Escape) {
                return null;
            } else if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + values.length) % values.length;
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % values.length;
            } else if (key.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        return values[selectedIndex];
    }

    private static void drawColoredText(String text, int x, int y, TextColor foreground, TextColor background, Screen screen) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(foreground);
        textGraphics.setBackgroundColor(background);
        textGraphics.putString(new TerminalPosition(x, y), text);
    }

    private static void drawText(String text, int x, int y, Screen screen) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(new TerminalPosition(x, y), text);
    }
}