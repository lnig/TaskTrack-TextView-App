package org.example.Controller;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.Model.Class.Product;
import org.example.Model.Class.familyMember;
import org.example.View.userTextView;

import java.io.IOException;
import java.util.ArrayList;

public class userInput {
    public static String getUserInput(Terminal terminal, Screen screen, String mess1, String mess2) throws IOException, InterruptedException {

        TextGraphics userInputTextGraphics = screen.newTextGraphics();
        StringBuilder userInput = new StringBuilder();
        KeyStroke key;
        boolean isRunning = false;
        int cols = screen.getTerminalSize().getColumns();

        do{
            key = terminal.pollInput();

            if (key != null) {
                if (key.getKeyType() == KeyType.Backspace) {
                    if (userInput.length() > 0)
                        userInput.deleteCharAt(userInput.length() - 1);
                } else if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();
                    if (Character.isLetter(c) && userInput.length() < 25)
                        userInput.append(c);
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (userInput.toString().trim().length() > 0) {
                        isRunning = true;
                    }
                }
            }

            screen.clear();
            userInputTextGraphics.setForegroundColor(new TextColor.RGB(56, 4, 14));
            for (int i = 0; i < cols; i++) {
                userInputTextGraphics.putString(i, 1, String.valueOf(Symbols.BLOCK_SOLID));
                userInputTextGraphics.putString(i, 10, String.valueOf(Symbols.BLOCK_SOLID));
            }

            userInputTextGraphics.setForegroundColor(new TextColor.RGB(100, 13, 20));
            userInputTextGraphics.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
            userInputTextGraphics.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
            userInputTextGraphics.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
            userInputTextGraphics.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
            userInputTextGraphics.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
            userInputTextGraphics.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");


            userInputTextGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
            if (mess1.length() > 40)
                userInputTextGraphics.putString((cols - mess1.length())/2 , 14, mess1, SGR.BOLD);
            else
                userInputTextGraphics.putString((cols - mess1.length())/2 , 14, mess1, SGR.BOLD);

            userInputTextGraphics.putString((cols - mess2.length())/2 - 4, 16, mess2, SGR.BOLD);
            userInputTextGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
            userInputTextGraphics.putString((cols - mess2.length())/2 + mess2.length() - 4, 16, String.valueOf(userInput), SGR.BOLD);
            userInputTextGraphics.putString((cols - mess2.length())/2 + mess2.length() - 4 + String.valueOf(userInput).length(), 16, "|", SGR.BOLD, SGR.BLINK);

            screen.refresh();

            Thread.sleep(10);
        }while (!isRunning);

        userInputTextGraphics.putString(31 + String.valueOf(userInput).length(), 14, "  ");
        screen.refresh();

        return userInput.toString();
    }
    public static String getUserInputFM(Terminal terminal, Screen screen, String mess1, String mess2, int t) throws IOException, InterruptedException {

        TextGraphics userInputTextGraphics = screen.newTextGraphics();
        StringBuilder userInput = new StringBuilder();
        KeyStroke key;
        boolean isRunning = false;
        int cols = screen.getTerminalSize().getColumns();

        do{
            key = terminal.pollInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Backspace) {
                    if (userInput.length() > 0)
                        userInput.deleteCharAt(userInput.length() - 1);
                } else if (key.getKeyType() == KeyType.Character) {
                    char c = key.getCharacter();
                    if (t == 0) {
                        if (Character.isDigit(c) && userInput.length() < 15)
                            userInput.append(c);
                    }else if(t == 1){
                        if (Character.isLetter(c) && userInput.length() < 15)
                            userInput.append(c);
                    } else if (t == 2) {
                        if ((Character.isDigit(c) || c == '.') && userInput.length() < 10)
                            userInput.append(c);
                    }
                } else if (key.getKeyType() == KeyType.Enter) {
                    if (userInput.toString().trim().length() > 0) {
                        isRunning = true;
                    }
                }
            }

            screen.clear();

            userInputTextGraphics.setForegroundColor(new TextColor.RGB(56, 4, 14));
            for (int i = 0; i < cols; i++) {
                userInputTextGraphics.putString(i, 1, String.valueOf(Symbols.BLOCK_SOLID));
                userInputTextGraphics.putString(i, 10, String.valueOf(Symbols.BLOCK_SOLID));
            }

            userInputTextGraphics.setForegroundColor(new TextColor.RGB(100, 13, 20));
            userInputTextGraphics.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
            userInputTextGraphics.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
            userInputTextGraphics.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
            userInputTextGraphics.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
            userInputTextGraphics.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
            userInputTextGraphics.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

            userInputTextGraphics.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
            if (mess1.length() > 40)
                userInputTextGraphics.putString((cols - mess1.length())/2 , 14, mess1, SGR.BOLD);
            else
                userInputTextGraphics.putString((cols - mess1.length())/2, 14, mess1, SGR.BOLD);

            userInputTextGraphics.putString((cols - mess2.length())/2 - 4, 16, mess2, SGR.BOLD);
            userInputTextGraphics.setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
            userInputTextGraphics.putString((cols - mess2.length())/2 + mess2.length() - 4, 16, String.valueOf(userInput), SGR.BOLD);
            userInputTextGraphics.putString((cols - mess2.length())/2 + mess2.length() - 4 + String.valueOf(userInput).length(), 16, "|", SGR.BOLD, SGR.BLINK);

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
            drawText(prompt, (cols - prompt.length())/2, 13, screen);

            for (int i = 0; i < values.length; i++) {
                if (i == selectedIndex) {
                    g.setForegroundColor(new TextColor.RGB(255, 183, 3));
                } else {
                    g.setForegroundColor(new TextColor.RGB(55, 97, 52));

                }
                g.putString((cols - values[i].name().length()) / 2, i + 15, values[i].name(), SGR.BOLD);
            }

            g.setForegroundColor(new TextColor.RGB(56, 4, 14));
            for (int i = 0; i < cols; i++) {
                g.putString(i, 1, String.valueOf(Symbols.BLOCK_SOLID));
                g.putString(i, 10, String.valueOf(Symbols.BLOCK_SOLID));
            }

            g.setForegroundColor(new TextColor.RGB(100, 13, 20));
            g.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
            g.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
            g.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
            g.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
            g.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
            g.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");

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

            if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + values.length) % values.length;
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % values.length;
            } else if (key.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        return values[selectedIndex];
    }

    public static <T> T chooseValueFromList(String prompt, ArrayList<T> values, Screen screen) throws IOException {
        TextGraphics g = screen.newTextGraphics();
        int selectedIndex = 0;
        int cols = screen.getTerminalSize().getColumns();

        while (true) {
            screen.clear();
            screen.doResizeIfNecessary();
            drawText(prompt, (cols - prompt.length()) / 2, 13, screen);

            for (int i = 0; i < values.size(); i++) {
                if (i == selectedIndex) {
                    g.setForegroundColor(new TextColor.RGB(255, 183, 3));
                } else {
                    g.setForegroundColor(new TextColor.RGB(55, 97, 52));
                }
                int x = ((familyMember) values.get(i)).getName().length();
                g.putString((cols - x) / 2, i + 15, ((familyMember) values.get(i)).getName(),SGR.BOLD);
            }

            g.setForegroundColor(new TextColor.RGB(56, 4, 14));
            for (int i = 0; i < cols; i++) {
                g.putString(i, 1, String.valueOf(Symbols.BLOCK_SOLID));
                g.putString(i, 10, String.valueOf(Symbols.BLOCK_SOLID));
            }

            g.setForegroundColor(new TextColor.RGB(100, 13, 20));
            g.putString(1, 3, "████████╗ █████╗ ███████╗██╗  ██╗    ████████╗██████╗  █████╗  ██████╗██╗  ██╗");
            g.putString(1, 4, "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝    ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝");
            g.putString(1, 5, "   ██║   ███████║███████╗█████╔╝        ██║   ██████╔╝███████║██║     █████╔╝ ");
            g.putString(1, 6, "   ██║   ██╔══██║╚════██║██╔═██╗        ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ");
            g.putString(1, 7, "   ██║   ██║  ██║███████║██║  ██╗       ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗");
            g.putString(1, 8, "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝       ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");


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

            if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + values.size()) % values.size();
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % values.size();
            } else if (key.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        return values.get(selectedIndex);
    }

    public static Product chooseValueFromListProd(String prompt, ArrayList<Product> values, Screen screen) throws IOException {
        TextGraphics g = screen.newTextGraphics();
        int selectedIndex = 0;
        int cols = screen.getTerminalSize().getColumns();

        while (true) {
            screen.clear();
            screen.doResizeIfNecessary();
            drawText(prompt, (cols - prompt.length()) / 2, 2, screen);

            for (int i = 0; i < values.size(); i++) {
                if (i == selectedIndex) {
                    g.setForegroundColor(new TextColor.RGB(255, 183, 3));
                } else {
                    g.setForegroundColor(new TextColor.RGB(55, 97, 52));
                }
                int x = values.get(i).getName().length() + values.get(i).getCategory().length() + values.get(i).getMeasureType().toString().length();
                g.putString((cols - x) / 2 - 4, i + 5, values.get(i).getName() + " " + values.get(i).getCategory() + " " +
                        values.get(i).getPrice() + values.get(i).getMeasureType() ,SGR.BOLD);
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

            if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + values.size()) % values.size();
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % values.size();
            } else if (key.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        return values.get(selectedIndex);
    }


    public static String chooseValueFromListString(String prompt, ArrayList<String> values, Screen screen) throws IOException {
        TextGraphics g = screen.newTextGraphics();
        int selectedIndex = 0;
        int cols = screen.getTerminalSize().getColumns();

        while (true) {
            screen.clear();
            screen.doResizeIfNecessary();
            drawText(prompt, (cols - prompt.length()) / 2, 7, screen);

            for (int i = 0; i < values.size(); i++) {
                if (i == selectedIndex) {
                    g.setForegroundColor(new TextColor.RGB(255, 183, 3));
                } else {
                    g.setForegroundColor(new TextColor.RGB(55, 97, 52));
                }
                g.putString((cols - values.get(i).toString().length()) / 2, i + 9, values.get(i), SGR.BOLD);
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

            if (key.getKeyType() == KeyType.ArrowUp) {
                selectedIndex = (selectedIndex - 1 + values.size()) % values.size();
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                selectedIndex = (selectedIndex + 1) % values.size();
            } else if (key.getKeyType() == KeyType.Enter) {
                break;
            }
        }
        return values.get(selectedIndex);
    }


    private static void drawText(String text, int x, int y, Screen screen) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(new TerminalPosition(x, y), text, SGR.BOLD);
    }
}