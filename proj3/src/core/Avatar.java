package core;

import tileengine.TETile;
import tileengine.Tileset;

public class Avatar {
    private int x;
    private int y;

    public Avatar(int x, int y){
        this.x = x;
        this.y = y;
    }


    public void tryMoveW(TETile[][] tiles){
        if(!Tileset.WALL.equals(tiles[x][y+1])){
            y++;
        }
    }
    public void tryMoveS(TETile[][] tiles){
        if(!Tileset.WALL.equals(tiles[x][y-1])){
            y--;
        }
    }
    public void tryMoveA(TETile[][] tiles){
        if(!Tileset.WALL.equals(tiles[x-1][y])){
            x--;
        }
    }
    public void tryMoveD(TETile[][] tiles){
        if(!Tileset.WALL.equals(tiles[x+1][y])){
            x++;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
