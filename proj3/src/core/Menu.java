package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
/*
public class Menu {
    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 40;
    private static int WIDTH;
    private static int HEIGHT;
    // Text
    private static String TITLE = "Welcome to the World";
    private static String SEED_TXT = "Enter Seed: ";
    private static String START_TXT = "[S]: Start Game";
    private static String EXIT_TXT = "[Q]: Exit Game";

    public Menu(TERenderer ter){
        WIDTH = DEFAULT_WIDTH;
        HEIGHT = DEFAULT_HEIGHT;
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        showMainMenu(ter);
    }
    public Menu(TERenderer ter, int w, int h){
        WIDTH = w;
        HEIGHT = h;
        ter.initialize(w, h);

        showMainMenu(ter);
    }



    public static void showMainMenu(TERenderer ter) {
        TETile[][] menu = new TETile[WIDTH][HEIGHT];

        // initialize map
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                menu[x][y] = Tileset.NOTHING;
            }
        }


        // title & button
        drawCenteredText(menu, TITLE, HEIGHT / 2 + 2);
        drawCenteredText(menu, START_TXT, HEIGHT / 2);
        drawCenteredText(menu, EXIT_TXT, HEIGHT / 2 - 2);
        ter.renderFrame(menu);

        // user input
        while (true) {
            char input = readUserInput();
            if (input == 'S' || input == 's') {
                // start
                startGame(ter);
                break;
            } else if (input == 'Q' || input == 'q') {
                // quit
                System.exit(0);
            }
        }
    }

    public static void drawCenteredText(TETile[][] menu, String text, int yPosition) {
        int xStart = (WIDTH - text.length()) / 2;
        for (int i = 0; i < text.length(); i++) {
            menu[xStart + i][yPosition] = new TETile(text.charAt(i), Color.white, Color.black, "test",2);
        }
    }

    public static char readUserInput() {
        char input = ' ';
        while (input == ' ') {
            if (StdDraw.hasNextKeyTyped()) {
                input = StdDraw.nextKeyTyped();
            }
        }
        return input;
    }
    public static void startGame(TERenderer ter) {
        System.out.println("Game is starting...");
        World world = new World(123456);

        ter.renderFrame(world.currentWorld);
    }

}
*/
