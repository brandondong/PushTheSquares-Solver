package Tests;

import Model.BlockColor;
import Model.Direction;
import Model.Tile;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Brandon on 2015-10-04.
 */
public class TileTest {

    @Test
    public void testToString() {
        assertEquals("BL", Tile.BLUE.toString());
        assertEquals("UP", Tile.UP.toString());
        assertEquals("SO", Tile.SOLID.toString());
    }

    @Test
    public void testGet() {
        assertNull(Tile.EMPTY.color);
        assertEquals(BlockColor.BLUE, Tile.BLUE.color);
        assertEquals(Direction.DOWN, Tile.DOWN.direction);
    }
}