package Tests;

import Model.Block;
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
        test = new Block(null, null, 0, 0, null);
    }

    @Test
    public void testCopy() {
        Block copy = test.getCopy();
        assertEquals(copy.getX(), 0);
        assertNotEquals(copy, test);
    }
}