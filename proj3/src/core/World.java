package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

public class World {
    // WORLD PART
    private static final int DEFAULT_WIDTH = 80;
    private static final int DEFAULT_HEIGHT = 40;
    private static int WIDTH;
    private static int HEIGHT;
    private static Random RANDOM;
    TETile[][] currentWorld;
    private static final String SAVE_FILE = "proj3/src/save.txt";

    // ROOM PART
    private static final int MAX_ROOMS = 10;
    private static final int MIN_ROOM_SIZE = 10;
    private static final int MAX_ROOM_SIZE = 18;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private Avatar avatar;



    public World(String filePath) throws FileNotFoundException {
        currentWorld = loadBoard(filePath);
    }
    public World(long seed) {
        WIDTH = DEFAULT_WIDTH;
        HEIGHT = DEFAULT_HEIGHT;
        currentWorld = new TETile[WIDTH][HEIGHT];
        RANDOM = new Random(seed);

        // Initialize the world
        fillWorldWithNothing();
        // generate rooms
        generateRooms();
        // generate hallways
        connectRooms();
        // generate avatar
        createAvatar();
    }

    private boolean checkQuit(){
        while (true) {
            if (StdDraw.hasNextKeyTyped()){
                switch (StdDraw.nextKeyTyped()) {
                    case 'Q','q' -> {
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
        }
    }
    private boolean updateWorld() throws FileNotFoundException {
        currentWorld[avatar.getX()][avatar.getY()] = Tileset.FLOOR;
        boolean isExist = false;
        while (StdDraw.hasNextKeyTyped() ) {
            switch (StdDraw.nextKeyTyped()){
                case 'W','w'->{
                    avatar.tryMoveW(currentWorld);
                    isExist = false;
                } case 'S','s'->{
                    avatar.tryMoveS(currentWorld);
                    isExist = false;
                } case 'A','a'->{
                    avatar.tryMoveA(currentWorld);
                    isExist = false;
                } case 'D','d'->{
                    avatar.tryMoveD(currentWorld);
                    isExist = false;
                }case 'L','l'->{
                    currentWorld = loadBoard(SAVE_FILE);
                    isExist = false;
                }case ':'->isExist = checkQuit();
            }
        }
        // generate new position
        currentWorld[avatar.getX()][avatar.getY()] = Tileset.AVATAR;
        return isExist;
    }


    public void createAvatar(){
        while (true){
            int x = RANDOM.nextInt(WIDTH);
            int y = RANDOM.nextInt(HEIGHT);

            if ( Tileset.FLOOR.equals(currentWorld[x][y])){
                avatar = new Avatar(x,y);
                currentWorld[x][y] = Tileset.AVATAR;
                break;
            }
        }
    }

    private void fillWorldWithNothing() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                currentWorld[x][y] = Tileset.NOTHING;
            }
        }
    }

    private void generateRooms() {
        int attempts = 0;  // limit number of tentative generation
        while (rooms.size() < MAX_ROOMS && attempts < 100) {
            int roomWidth = RANDOM.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int roomHeight = RANDOM.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int x = RANDOM.nextInt(WIDTH - roomWidth - 1);
            int y = RANDOM.nextInt(HEIGHT - roomHeight - 1);

            Room newRoom = new Room(x, y, roomWidth, roomHeight);

            // Check overlap
            boolean overlaps = false;
            for (Room r : rooms) {
                if (newRoom.overlaps(r)) {
                    overlaps = true;
                    break;
                }
            }

            // if not
            if (!overlaps) {
                rooms.add(newRoom);
                newRoom.drawRoom(currentWorld);
            }
            attempts++;
        }
    }

    private void connectRooms() {
        HashSet<Room> remainingRooms = new HashSet<>(rooms);
        remainingRooms.remove(rooms.getFirst());

        Room sRoom = rooms.getFirst();
        double rDistance;
        while (!remainingRooms.isEmpty()){
            double distance = Double.MAX_VALUE;
            Room dRoom = null;
            // calculate distance for each remaining rooms => find the closest one
            for(Room r : remainingRooms){
                rDistance = r.distantTo(sRoom);
                if(rDistance < distance){
                    distance = rDistance;
                    dRoom = r;
                }
            }

            if (dRoom == null) {
                break;
            }

            // generate hallway for these 2 rooms
            Hallway hallway = new Hallway(sRoom.getCenterX(),
                                    sRoom.getCenterY(),
                                    dRoom.getCenterX(),
                                    dRoom.getCenterY());
            hallway.drawHallway(currentWorld);

            // next room
            remainingRooms.remove(dRoom);
            sRoom = dRoom;
        }
    }

    public void saveBoard() {
        StringBuilder content = new StringBuilder();

        content.append(WIDTH).append(" ").append(HEIGHT).append("\n");
        for (int y = HEIGHT-1; y>=0; y--){
            for (int x =0 ; x<WIDTH ; x++){
                if(Tileset.FLOOR.equals(currentWorld[x][y])){
                    content.append(1);
                }else if(Tileset.WALL.equals(currentWorld[x][y])){
                    content.append(2);
                } else if (Tileset.AVATAR.equals(currentWorld[x][y])) {
                    content.append(3);
                } else{
                    content.append(0);
                }
            }
            content.append("\n");
        }
        FileUtils.writeFile(SAVE_FILE, content.toString());
    }
    private TETile[][] loadBoard(String filename) throws FileNotFoundException {
        if(!FileUtils.fileExists(filename)){
            throw new FileNotFoundException(filename);
        }
        String content = FileUtils.readFile(filename);
        String[] lines = content.split("\n");
        String[] size = lines[0].split(" ");
        int w = Integer.parseInt(size[0]);
        int h = Integer.parseInt(size[1]);

        TETile[][] loadTiles = new TETile[w][h];


        for (int r = 1; r< lines.length; r++){
            for (int j=0; j<lines[r].length(); j++){
                if (lines[r].charAt(j)== '3'){
                    avatar = new Avatar(j,h-r);
                }
                switch (lines[r].charAt(j)){
                    case '1'-> loadTiles[j][h-r] = Tileset.FLOOR;
                    case '2'-> loadTiles[j][h-r] = Tileset.WALL;
                    case '3' -> loadTiles[j][h-r] = Tileset.AVATAR;
                    default -> loadTiles[j][h-r] = Tileset.NOTHING;
                }
            }
        }

        return loadTiles;
    }


    public void runWorld(TERenderer ter) throws FileNotFoundException {
        ter.resetFont();
        boolean isExit = false;
        while (!isExit){
            ter.renderFrame(currentWorld);
            isExit = updateWorld();
        }
        saveBoard();
        System.exit(0);

    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        World world = new World(2873123);

        ter.renderFrame(world.currentWorld);
    }

}
