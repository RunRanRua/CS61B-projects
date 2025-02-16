package core;

import tileengine.TETile;
import tileengine.Tileset;

public class Room {
    private int x, y;
    private int w, h;


    public Room(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    public boolean overlaps(Room other) {
        return !(this.x + this.w <= other.x ||
                other.x + other.w <= this.x ||
                this.y + this.h <= other.y ||
                other.y + other.h <= this.y);
    }

    public void drawRoom(TETile[][] world) {
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                // if border
                if (i == x || i == x + w - 1 || j == y || j == y + h - 1) {
                    world[i][j] = Tileset.WALL;
                } else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
    }
    public double distantTo(Room r){
        return Math.sqrt( Math.pow(r.getCenterX() - this.getCenterX(),2)
                +
                Math.pow(r.getCenterY() - this.getCenterY(),2));
    }

    public int getCenterX() {
        return x + w / 2;
    }

    public int getCenterY() {
        return y + h / 2;
    }


}
