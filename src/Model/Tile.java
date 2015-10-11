package Model;

/**
 * Created by Brandon on 2015-09-24.
 */
public enum Tile {
    EMPTY, RED(BlockColor.RED), BLUE(BlockColor.BLUE), YELLOW(BlockColor.YELLOW), GREEN(BlockColor.GREEN),
    SOLID, UP(Direction.UP), DOWN(Direction.DOWN), RIGHT(Direction.RIGHT), LEFT(Direction.LEFT), TP;

    public BlockColor color;
    public Direction direction;

    private Tile() {}

    private Tile(BlockColor color) {
        this.color = color;
    }

    private Tile(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        if (this == EMPTY) {
            return "**";
        }
        return super.toString().substring(0, 2);
    }
}
