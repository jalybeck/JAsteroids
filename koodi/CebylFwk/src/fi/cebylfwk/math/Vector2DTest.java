package fi.cebylfwk.math;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Vector2DTest {
    public Vector2DTest() {
    }

    @Test
    public void testDefaultConstructor() {

        Vector2D v = null;

        try {
            v = new Vector2D();
        } catch (Exception e) {
            fail("new Vector2D() failed!");
        }

        assertNotNull("new Vector2D() returned null!", v);
    }

    @Test
    public void testAssignmentConstructorWithPrimitiveValues() {
        Vector2D v = new Vector2D(1.0, 2.0);

        assertEquals("x != 1.0", 1.0, v.x, 0.001);
        assertEquals("y != 2.0", 2.0, v.y, 0.001);
    }

    @Test
    public void testAddition() {
        Vector2D v1 = new Vector2D(1.0, 2.0);
        Vector2D v2 = new Vector2D(-1.0, -2.0);
        v1.add(v2);

        assertEquals("x != 0.0", 0.0, v1.x, 0.001);
        assertEquals("y != 0.0", 0.0, v1.y, 0.001);

        v1.add(v2).add(new Vector2D(20.0, 10.0));

        assertEquals("x != ", 19.0, v1.x, 0.001);
        assertEquals("y != ", 8.0, v1.y, 0.001);
    }

    @Test
    public void testSubtraction() {

        Vector2D v1 = new Vector2D(100.2, 200.5);
        Vector2D v2 = new Vector2D(50.1, 10);
        Vector2D v3 = new Vector2D(10.1, 15.5);

        v1.sub(v2).sub(v3);

        assertEquals("x != 40.0", 40.0, v1.x, 0.001);
        assertEquals("y != 175.0", 175.0, v1.y, 0.001);

    }

    @Test
    public void testMultiplicationByScalar() {
        Vector2D v = new Vector2D(10.0, 10.0);

        v.mul(2.5);

        assertEquals("x != 25.0", 25.0, v.x, 0.001);
        assertEquals("y != 25.0", 25.0, v.y, 0.001);
    }

    @Test
    public void testDotProduction() {
        Vector2D v1 = new Vector2D(1.0, 0.0);
        Vector2D v2 = new Vector2D(0.0, 10.0);

        //Dot product of two perpendicular vectors is zero
        double dot = v1.dot(v2);
        assertEquals("dot != 0.0", 0.0, dot, 0.001);
    }

    @Test
    public void testVectorMagnitude() {
        Vector2D v = new Vector2D(10, 5);

        // mag = sqrt(dot(v)) = sqrt(10*10+5*5) = sqrt(100+25) = sqrt(125) = 11.180
        double mag = v.mag();
        assertEquals("mag != 11.180", 11.180, mag, 0.001);
    }

    @Test
    public void testVectorNormalization() {
        Vector2D v = new Vector2D(100, 100);

        //After normalization length of the vector should not be larger than 1
        v.normalize();
        double mag = v.mag();
        assertTrue("mag > 1.0", mag <= 1.0 ? true : false);
    }

    @Test
    public void testVectorNegation() {
        Vector2D v = new Vector2D(-10.232, -11.241);

        v.negate();

        assertEquals("x != 10.232", 10.232, v.x, 0.001);
        assertEquals("y != 11.241", 11.241, v.y, 0.001);
    }

    @Test
    public void testDirectionVector() {
        Vector2D vecUp = Vector2D.createDirectionVector(0);
        Vector2D vecRight = Vector2D.createDirectionVector(90);
        Vector2D vecDown = Vector2D.createDirectionVector(180);
        Vector2D vecLeft = Vector2D.createDirectionVector(270);

        //Assert vecUp
        assertEquals("vecUp.x != 0.0", 0.0, vecUp.x, 0.001);
        assertEquals("vecUp.y != 1.0", 1.0, vecUp.y, 0.001);

        //Assert vecRight
        assertEquals("vecRight.x != 1.0", 1.0, vecRight.x, 0.001);
        assertEquals("vecRight.y != 0.0", 0.0, vecRight.y, 0.001);
        
        //Assert vecDown
        assertEquals("vecDown.x !=  0.0", 0.0, vecDown.x, 0.001);
        assertEquals("vecDown.y != -1.0", -1.0, vecDown.y, 0.001);
        
        //Assert vecDown
        assertEquals("vecLeft.x != -1.0", -1.0, vecLeft.x, 0.001);
        assertEquals("vecLeft.y !=  0.0", 0.0, vecLeft.y, 0.001);        
    }

    @Test
    public void testVectorProjection() {
        Vector2D vec1 = new Vector2D(1.0, 1.0);
        Vector2D vec2 = new Vector2D(1.0, 0.0);

        //Projection of vec1 on vec2 should be vec2 in this situation.
        Vector2D vecProj = vec1.projection(vec2);

        assertEquals("x component of projection vector is not vec2.x(1.0)!", vec2.x, vecProj.x, 0.001);
        assertEquals("y component of projection vector is not vec2.y(0.0)!", vec2.y, vecProj.y, 0.001);

        vec1 = new Vector2D(-1.0, -1.0);
        vec2 = new Vector2D(-1.0, 0.0);

        //Projection of vec1 on vec2 should be vec2 in this situation.
        vecProj = vec1.projection(vec2);

        assertEquals("x component of projection vector is not vec2.x(-1.0)!", vec2.x, vecProj.x, 0.001);
        assertEquals("y component of projection vector is not vec2.y(0.0)!", vec2.y, vecProj.y, 0.001);

    }

}
