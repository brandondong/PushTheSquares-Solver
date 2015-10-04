package Model;

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

    // Modifies: this
    // Effects: returns true and moves the block if possible
    public boolean move() {
        return moveInDirection(direction);
    }

    // Modifies: this
    // Effects: returns true and moves the block in the given direction if possible
    public boolean moveInDirection(Direction dir) {
        int nextX = nextX(dir);
        int nextY = nextY(dir);

        if (board.withinBoard(nextX, nextY)) {
            Tile tile = board.getTileAtPos(nextX, nextY);
            if (tile == Tile.SOLID) return false;

            Block next = board.getBlockAtPos(nextX, nextY);
            if (next != null && !next.moveInDirection(dir)) return false;

            x = nextX;
            y = nextY;
            interactWith(tile);
            return true;
        } else {
            return false;
        }
    }

    // Modifies: this
    // Effects: sets the direction to the direction of the tile
    public void interactWith(Tile tile) {
        if (tile.direction != null) {
            direction = tile.direction;
        }
    }

    // Effects: returns the x-position after moving in a given direction
    private int nextX(Direction direction) {
        if (direction == Direction.LEFT) {
            return x - 1;
        } else if (direction == Direction.RIGHT) {
            return x + 1;
        } else {
            return x;
        }
    }

    // Effects: returns the y-position after moving in a given direction
    private int nextY(Direction direction) {
        if (direction == Direction.DOWN) {
            return y + 1;
        } else if (direction == Direction.UP) {
            return y - 1;
        } else {
            return y;
        }
    }

    public Block getCopy() {
        return new Block(color, direction, x, y, board);
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

    @Override
    public String toString() {
        return color.toString().substring(0, 1) +
                direction.toString().substring(0, 1);
    }
}
