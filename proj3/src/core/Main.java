package core;

import tileengine.TERenderer;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    public static void runGame(){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        World world = new World(2873123);
        ter.renderFrame(world.currentWorld);
        world.saveBoard();
    }
    public static void loadGame() throws FileNotFoundException {
        String filePath = "proj3/src/save.txt";
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        World world = new World(filePath);
        ter.renderFrame(world.currentWorld);
    }
    public static void main(String[] args) throws FileNotFoundException {

        // build your own world!
        //runGame();

        loadGame();


    }
}
