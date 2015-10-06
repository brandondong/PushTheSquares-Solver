package Tests;

import Model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Brandon on 2015-10-06.
 */
public class BoardTest {
    private Tile[][] level1;
    private Board board1;

    @Before
    public void setUp() {
        level1 = new Tile[5][5];
        fillTileArray(level1);
        level1[1][4] = Tile.BLUE;
        level1[3][4] = Tile.RED;

        List<BlockColor> colors = new ArrayList<>();
        colors.add(BlockColor.BLUE);
        colors.add(BlockColor.RED);

        board1 = new Board(colors, new ArrayList<Block>(), level1);
        board1.addBlock(new Block(BlockColor.BLUE, Direction.DOWN, 1, 0, board1));
        board1.addBlock(new Block(BlockColor.RED, Direction.DOWN, 3, 0, board1));
    }

    private void fillTileArray(Tile[][] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                level1[x][y] = Tile.EMPTY;
            }
        }
    }

    @Test
    public void print() {
        System.out.println(board1);
    }
}