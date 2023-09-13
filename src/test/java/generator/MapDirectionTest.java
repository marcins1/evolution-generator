package generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    public void rotateTest(){
        assertEquals(MapDirection.NORTH, MapDirection.NORTH.rotate(0));
        assertEquals(MapDirection.NORTHEAST, MapDirection.NORTH.rotate(1));
        assertEquals(MapDirection.EAST, MapDirection.NORTH.rotate(2));
        assertEquals(MapDirection.SOUTHEAST, MapDirection.NORTH.rotate(3));
        assertEquals(MapDirection.SOUTH, MapDirection.NORTH.rotate(4));
        assertEquals(MapDirection.SOUTHWEST, MapDirection.NORTH.rotate(5));
        assertEquals(MapDirection.WEST, MapDirection.NORTH.rotate(6));
        assertEquals(MapDirection.NORTHWEST, MapDirection.NORTH.rotate(7));
        assertEquals(MapDirection.NORTH, MapDirection.NORTHWEST.rotate(1));
    }

    @Test
    public void toUnitVectorTest(){
        assertEquals(new Vector2d(0, 1), MapDirection.NORTH.toUnitVector());
        assertEquals(new Vector2d(1, 1), MapDirection.NORTHEAST.toUnitVector());
        assertEquals(new Vector2d(1, 0), MapDirection.EAST.toUnitVector());
        assertEquals(new Vector2d(1, -1), MapDirection.SOUTHEAST.toUnitVector());
        assertEquals(new Vector2d(0, -1), MapDirection.SOUTH.toUnitVector());
        assertEquals(new Vector2d(-1, -1), MapDirection.SOUTHWEST.toUnitVector());
        assertEquals(new Vector2d(-1, 0), MapDirection.WEST.toUnitVector());
        assertEquals(new Vector2d(-1, 1), MapDirection.NORTHWEST.toUnitVector());
    }
}
