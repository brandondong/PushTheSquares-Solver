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

    @Before
    public void setUp() {
        board1 = new Board(5, 5);
        board1.addColor(BlockColor.BLUE);
        board1.addColor(BlockColor.RED);
        board1.addTile(1, 4, Tile.BLUE);
        board1.addTile(3, 4, Tile.RED);
        board1.addBlock(new Block(BlockColor.BLUE, Direction.DOWN, 1, 0));
        board1.addBlock(new Block(BlockColor.RED, Direction.DOWN, 3, 0));
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
}