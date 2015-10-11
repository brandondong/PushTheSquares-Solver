package Model;

import java.awt.*;

/**
 * Created by Brandon on 2015-09-24.
 */
public enum Direction {
    UP, DOWN, RIGHT, LEFT;

    public static Point move(Point p, Direction direction) {
        int nextX = p.x;
        int nextY = p.y;
        if (direction == Direction.LEFT) {
            nextX--;
        } else if (direction == Direction.RIGHT) {
            nextX++;
        } else if (direction == Direction.UP) {
            nextY--;
        } else if (direction == Direction.DOWN) {
            nextY++;
        }
        return new Point(nextX, nextY);
    }
}
