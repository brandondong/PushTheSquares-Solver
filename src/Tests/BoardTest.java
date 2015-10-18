package Tests;

import Model.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Brandon on 2015-10-06.
 */
public class BoardTest {
    private Board board1;
    private Board board2;
    private Board board3;

    @Before
    public void setUp() {
        board1 = new Board(5, 5);
        board1.addTile(1, 4, Tile.BLUE);
        board1.addTile(3, 4, Tile.RED);
        board1.addBlock(new Block(BlockColor.BLUE, Direction.DOWN, 1, 0));
        board1.addBlock(new Block(BlockColor.RED, Direction.DOWN, 3, 0));

        board2 = new Board(5, 5);
        board2.addTile(2, 0, Tile.DOWN);
        board2.addTile(1, 1, Tile.RIGHT);
        board2.addTile(3, 2, Tile.RED);
        board2.addTile(2, 3, Tile.BLUE);
        board2.addBlock(new Block(BlockColor.BLUE, Direction.LEFT, 2, 2));
        board2.addBlock(new Block(BlockColor.RED, Direction.UP, 1, 3));

        board3 = new Board(5, 5);
        board3.addTile(0, 3, Tile.RED);
        board3.addTile(1, 4, Tile.YELLOW);
        board3.addTile(3, 4, Tile.BLUE);
        board3.addTP(3, 2, 2, 3);
        board3.addBlock(new Block(BlockColor.BLUE, Direction.DOWN, 3, 3));
        board3.addBlock(new Block(BlockColor.RED, Direction.LEFT, 1, 3));
        board3.addBlock(new Block(BlockColor.YELLOW, Direction.LEFT, 4, 3));
    }

    @Test
    public void testNext() {
        System.out.println("NEXT");
        for (Board next : board1.nextBoards()) {
            System.out.println(next);
        }
        System.out.println("ORIGINAL");
        System.out.println(board1);
        assertFalse(board1.isOver());
    }

    @Test
    public void testSolve() {
        System.out.println(board1.solve());
        System.out.println(board2.solve());
        System.out.println(board3.solve());
    }
}