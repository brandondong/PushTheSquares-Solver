/**
 * Created by Brandon on 2015-09-24.
 */
public class Block {
    private BlockColor color;
    private Direction direction;
    private int x;
    private int y;

    public Block(BlockColor color, Direction direction, int x, int y) {
        this.color = color;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
