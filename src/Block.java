/**
 * Created by Brandon on 2015-09-24.
 */
public class Block {
    private BlockColor color;
    private Direction direction;
    private int x;
    private int y;
    private Board board;

    public Block(BlockColor color, Direction direction, int x, int y, Board board) {
        this.color = color;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Effects: returns the x-position after moving once
    private int nextX() {
        if (direction == Direction.LEFT) {
            return x - 1;
        } else if (direction == Direction.RIGHT) {
            return x + 1;
        } else {
            return x;
        }
    }

    // Effects: returns the y-position after moving once
    private int nextY() {
        if (direction == Direction.DOWN) {
            return y + 1;
        } else if (direction == Direction.UP) {
            return y - 1;
        } else {
            return y;
        }
    }

    public BlockColor getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
