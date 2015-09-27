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
