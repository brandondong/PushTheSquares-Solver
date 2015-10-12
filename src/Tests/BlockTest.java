package Tests;

import Model.Block;
import Model.BlockColor;
import Model.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Brandon on 2015-10-02.
 */
public class BlockTest {
    private Block test;

    @Before
    public void setUp() {
        test = new Block(BlockColor.BLUE, Direction.DOWN, 0, 0);
    }

    @Test
    public void testCopy() {
        Block copy = test.getCopy();
        assertEquals(copy.getX(), 0);
        assertNotEquals(copy, test);
    }

    @Test
    public void testString() {
        assertEquals("BD", test.toString());
    }
}