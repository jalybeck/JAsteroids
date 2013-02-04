package test.fi.cebylfwk.shape;

import fi.cebylfwk.shape.Rectangle;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RectangleTest {
    public RectangleTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testIntersects() {
        Rectangle rect1 = new Rectangle(8,6,4,4);
        Rectangle rect2 = new Rectangle(40,40,10,10);
        Rectangle rect3 = new Rectangle(22,22,40,10);
        System.out.println("Rect1");
        System.out.println(rect1);
        System.out.println("Rect2");
        System.out.println(rect2);
        System.out.println("Rect3");
        System.out.println(rect3);
        //Should not intersect
        boolean intersects = rect1.intersects(rect2);
        assertFalse("rect1 should not intersect rect2!",intersects);
        intersects = rect3.intersects(rect1);
        assertFalse("rect3 should not intersect rect1!",intersects);
        intersects = rect2.intersects(rect3);
        assertTrue("rect2 should intersect rect3!",intersects);
        
    }
}
