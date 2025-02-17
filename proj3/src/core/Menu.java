package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.io.FileNotFoundException;

public class Menu {
    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 40;
    private static int WIDTH;
    private static int HEIGHT;
    // Text
    private static String TITLE = "CS61B: BYOW";
    private static String NEW_GAME_TXT = "[N] New Game";
    private static String LOAD_GAME_TXT = "[L] Load Game";
    private static String QUIT_GAME_TXT = "[Q] Quit Game";
    private static String SEED_TXT = "Enter seed followed by [S]";
    private static String SEED = "";
    private static final String SAVE_FILE = "proj3/src/save.txt";
    private static World world;
    private final TERenderer ter;


    public Menu(TERenderer ter){
        WIDTH = DEFAULT_WIDTH;
        HEIGHT = DEFAULT_HEIGHT;
        this.ter =ter;
    }
    public Menu(TERenderer ter, int w, int h){
        WIDTH = w;
        HEIGHT = h;
        this.ter =ter;
    }


    public void renderMenu(){
        StdDraw.setFont(new Font("Arial", Font.BOLD, 36));
        StdDraw.setPenColor(255, 255, 255);

        // position
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT*3/4), TITLE);
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT*3/5), NEW_GAME_TXT);
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT*3/5) -5, LOAD_GAME_TXT);
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT*3/5) -10, QUIT_GAME_TXT);
        // draw
        StdDraw.show();
    }


    public void runGame() throws FileNotFoundException {
        renderMenu();
        while (true){
            if (StdDraw.hasNextKeyTyped()){
                switch (StdDraw.nextKeyTyped()){
                    case 'N','n'-> newGame();
                    case 'L', 'l' -> loadGame();
                    case'Q','q' ->System.exit(0);
                }
            }
        }
    }
    private void renderNewGame(){
        StdDraw.clear(Color.black);

        StdDraw.setFont(new Font("Arial", Font.BOLD, 36));
        StdDraw.setPenColor(255, 255, 255);

        // txt
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT*3/4), TITLE);
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT/2), SEED_TXT);

        StdDraw.setPenColor(Color.yellow);
        StdDraw.text((int) (WIDTH/2), (int) (HEIGHT/3), SEED);

        StdDraw.show();
    }

    public void newGame() throws FileNotFoundException {
        while (true){
            renderNewGame();

            if (StdDraw.hasNextKeyTyped()){
                char input = StdDraw.nextKeyTyped();
                if (Character.isDigit(input)){
                    SEED += input;
                }
                else if (input == 'S' || input =='s'){
                    break;
                }
            }
        }
        world = new World(Long.parseLong(SEED));
        world.runWorld(ter);
    }
    private void loadGame() throws FileNotFoundException {
        world = new World(SAVE_FILE);
        world.runWorld(ter);
    }

    public static void main(String[] args) throws FileNotFoundException {
        TERenderer ter = new TERenderer();
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Menu menu = new Menu(ter);
        menu.runGame();
    }
}
