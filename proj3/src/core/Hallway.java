package core;

import tileengine.TETile;
import tileengine.Tileset;

public class Hallway {
    private int x1, y1; // start
    private int x2, y2; // destination

    public Hallway(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }



    public void drawHallway(TETile[][] world) {
        if (x1 != x2) {
            int startX = Math.min(x1, x2);
            int endX = Math.max(x1, x2);
            for (int x = startX; x <= endX; x++) {
                world[x][y1] = Tileset.FLOOR;

                if (Tileset.NOTHING.equals(world[x][y1+1])){
                    world[x][y1+1] = Tileset.WALL;
                }
                if (Tileset.NOTHING.equals(world[x][y1-1])){
                    world[x][y1-1] = Tileset.WALL;
                }
            }
        }
        if (y1 != y2) {
            int startY = Math.min(y1, y2);
            int endY = Math.max(y1, y2);
            for (int y = startY; y <= endY; y++) {
                world[x2][y] = Tileset.FLOOR;
                if (Tileset.NOTHING.equals(world[x2+1][y])){
                    world[x2+1][y] = Tileset.WALL;
                }
                if (Tileset.NOTHING.equals(world[x2-1][y])){
                    world[x2-1][y] = Tileset.WALL;
                }
            }
        }
    }
}
