import java.util.List;

/**
 * Created by Brandon on 2015-09-25.
 */
public class Board {
    private int width;
    private int height;
    private List<BlockColor> colors;
    private List<Block> blocks;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
