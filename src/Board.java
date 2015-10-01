import java.util.List;

/**
 * Created by Brandon on 2015-09-25.
 */
public class Board {
    private int width;
    private int height;
    private List<BlockColor> colors;
    private List<Block> blocks;
    private Tile[][] tiles;

    public Board(int width, int height, List<BlockColor> colors, List<Block> blocks, Tile[][] tiles) {
        this.width = width;
        this.height = height;
        this.colors = colors;
        this.blocks = blocks;
        this.tiles = tiles;
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
        return x >= 0 && x < width &&
                y >= 0 && y < height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
