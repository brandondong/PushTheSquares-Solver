package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2015-09-25.
 */
public class Board {
    private List<BlockColor> colors;
    private List<Block> blocks;
    private Tile[][] tiles;

    public Board(List<BlockColor> colors, List<Block> blocks, Tile[][] tiles) {
        this.colors = colors;
        this.blocks = blocks;
        this.tiles = tiles;
    }

    public void addBlock(Block b) {
        blocks.add(b);
    }

    public List<Board> nextBoards() {
        List<Board> gen = new ArrayList<>();
        for (BlockColor c : colors) {
            Board next = new Board(colors, copyBlocks(blocks), copyArray(tiles));
            if (next.moveBlocksByColor(c)) {
                gen.add(next);
            }
        }
        return gen;
    }

    // Effects: creates a copy of the board's blocks
    private List<Block> copyBlocks(List<Block> blocks) {
        List<Block> copy = new ArrayList<>();
        for (Block next : blocks) {
            Block nextCopy = next.getCopy();
            copy.add(nextCopy);
        }
        return copy;
    }

    // Effects: creates a copy of the board's tile array
    private Tile[][] copyArray(Tile[][] array) {
        int width = tiles.length;
        int height = tiles[0].length;

        Tile[][] copy = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                copy[i][j] = tiles[i][j];
            }
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
        return hasMoved;
    }

    // Effects: returns true if x,y point is within the board
    public boolean withinBoard(int x, int y) {
        return x >= 0 && x < tiles.length &&
                y >= 0 && y < tiles[0].length;
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

    @Override
    public String toString() {
        return stringTiles() + "\n\n" + stringBlocks();
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
}
