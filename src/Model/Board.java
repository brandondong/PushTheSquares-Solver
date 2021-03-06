package Model;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Brandon on 2015-09-25.
 */
public class Board {
    private static final String SPACE_PRINT = spaceLength(7);

    private List<Block> blocks;
    private Tile[][] tiles;
    private List<BlockColor> path;
    private Map<Point, Point> tps;

    public Board(int width, int height) {
        blocks = new ArrayList<>();
        tiles = new Tile[width][height];
        path = new ArrayList<>();
        tps = new HashMap<>();
        fillTileArray(tiles);
    }

    public Board(Tile[][] tiles, List<BlockColor> path, Map<Point, Point> tps) {
        blocks = new ArrayList<>();
        this.tiles = tiles;
        this.path = path;
        this.tps = tps;
    }

    public List<BlockColor> solve() {
        Queue<Board> queue = new ConcurrentLinkedQueue<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Board next = queue.remove();
            if (next.isOver()) {
                return next.getPath();
            }
            queue.addAll(next.nextBoards());
        }
        return null;
    }

    public void addTile(int x, int y, Tile t) {
        tiles[x][y] = t;
    }

    private void fillTileArray(Tile[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                array[x][y] = Tile.EMPTY;
            }
        }
    }

    public void addBlock(Block b) {
        blocks.add(b);
        b.setBoard(this);
    }

    public void addTP(int x, int y, int tpX, int tpY) {
        tiles[x][y] = Tile.TP;
        tiles[tpX][tpY] = Tile.TP;
        tps.put(new Point(x, y), new Point(tpX, tpY));
        tps.put(new Point(tpX, tpY), new Point(x, y));
    }

    public Point getTPPos(Point p) {
        return tps.get(p);
    }

    // Effects: returns a list of boards generated from trying all possible moves
    public List<Board> nextBoards() {
        List<Board> gen = new ArrayList<>();
        for (BlockColor c : BlockColor.values()) {
            Board next = copyBoard();
            if (next.moveBlocksByColor(c)) {
                gen.add(next);
            }
        }
        return gen;
    }

    // Effects: creates a copy of the board
    public Board copyBoard() {
        Board copy = new Board(tiles, new ArrayList<>(path), tps);
        for (Block next : blocks) {
            Block nextCopy = next.getCopy();
            copy.addBlock(nextCopy);
        }
        return copy;
    }

    // Modifies: this
    // Effects: returns true and moves all blocks of given color if possible
    public boolean moveBlocksByColor(BlockColor c) {
        boolean hasMoved = false;
        for (Block next : blocks) {
            if (next.getColor() == c && next.move()) {
                hasMoved = true;
            }
        }
        if (hasMoved) {
            path.add(c);
        }
        return hasMoved;
    }

    // Effects: returns true if x,y point is within the board
    public boolean withinBoard(int x, int y) {
        return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }


    // Effects: returns the block at the x,y point, null if none exists
    public Block getBlockAtPos(int x, int y) {
        for (Block next : blocks) {
            if (next.getX() == x && next.getY() == y) {
                return next;
            }
        }
        return null;
    }

    // Effects: returns the tile at the x,y point
    public Tile getTileAtPos(int x, int y) {
        return tiles[x][y];
    }

    // Effects: returns true if all blocks are on their correctly colored tiles
    public boolean isOver() {
        for (Block next : blocks) {
            Tile nextTile = getTileAtPos(next.getX(), next.getY());
            if (next.getColor() != nextTile.color) {
                return false;
            }
        }
        return true;
    }

    public List<BlockColor> getPath() {
        return path;
    }

    @Override
    public String toString() {
        String tS = stringTiles();
        String tB = stringBlocks();
        StringBuilder builder = new StringBuilder();
        int rowLength = tS.indexOf("\n");

        builder.append("Tiles:");
        builder.append(spaceLength(rowLength - 6));
        builder.append(SPACE_PRINT);
        builder.append("Blocks:\n");

        while (tS.length() > 0) {
            builder.append(tS.substring(0, rowLength));
            builder.append(SPACE_PRINT);
            builder.append(tB.substring(0, rowLength));
            builder.append("\n");
            tS = tS.substring(rowLength + 1);
            tB = tB.substring(rowLength + 1);
        }
        return builder.toString();
    }

    private String stringTiles() {
        int width = tiles.length;
        int height = tiles[0].length;
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(tiles[x][y]);
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private String stringBlocks() {
        int width = tiles.length;
        int height = tiles[0].length;
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Block next = getBlockAtPos(x, y);
                if (next != null) {
                    builder.append(next);
                    builder.append(" ");
                } else {
                    builder.append("** ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static String spaceLength(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }
}
