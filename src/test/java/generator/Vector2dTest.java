package generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(1, 1);
        Vector2d v3 = new Vector2d(2, 4);
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    public void toStringTest(){
        Vector2d v = new Vector2d(1, -2);
        assertEquals("(1, -2)", v.toString());
    }

    @Test
    public void addTest(){
        Vector2d v1 = new Vector2d(-1, 1);
        Vector2d v2 = new Vector2d(1, 10);
        Vector2d v3 = new Vector2d(0, 11);
        assertEquals(v3, v1.add(v2));
        assertNotEquals(v1, v2.add(v3));
    }

    @Test
    public void subtractTest(){
        Vector2d v1 = new Vector2d(-1, 1);
        Vector2d v2 = new Vector2d(1, 10);
        Vector2d v3 = new Vector2d(-2, -9);
        assertEquals(v3, v1.subtract(v2));
        assertNotEquals(v1, v2.subtract(v3));
    }
}