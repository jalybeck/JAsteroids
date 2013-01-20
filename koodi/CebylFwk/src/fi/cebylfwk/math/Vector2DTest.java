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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Test
    public void testDefaultConstructor() {
        
        Vector2D v = null;
        
        try {
            v = new Vector2D();
        } catch (Exception e) {
            fail("new Vector2D() failed!");
        }
        
        assertNotNull("new Vector2D() returned null!",v);
    }
    
    @Test
    public void testAssignmentConstructorWithPrimitiveValues() {
        Vector2D v = new Vector2D(1.0,2.0);
        
        assertEquals("x != 1.0",1.0,v.x,0.001);
        assertEquals("y != 2.0",2.0,v.y,0.001);
    }
    
    @Test
    public void testAddition() {
        Vector2D v1 = new Vector2D(1.0,2.0);
        Vector2D v2 = new Vector2D(-1.0,-2.0);
        v1.add(v2);
        
        assertEquals("x != 0.0",0.0,v1.x,0.001);
        assertEquals("y != 0.0",0.0,v1.y,0.001);
        
        v1.add(v2).add(new Vector2D(20.0,10.0));
        
        assertEquals("x != ",19.0,v1.x,0.001);
        assertEquals("y != ",8.0,v1.y,0.001);
    }
    
    @Test
    public void testSubtraction() {
        
       Vector2D v1 = new Vector2D(100.2,200.5);
       Vector2D v2 = new Vector2D(50.1,10);
       Vector2D v3 = new Vector2D(10.1,15.5);
       
       v1.sub(v2).sub(v3);
       
       assertEquals("x != 40.0",40.0,v1.x,0.001);
       assertEquals("y != 175.0",175.0,v1.y,0.001);
       
    }
    
    @Test
     public void testMultiplicationByScalar() {
        Vector2D v = new Vector2D(10.0,10.0);
        
        v.mul(2.5);
        
        assertEquals("x != 25.0",25.0,v.x,0.001);
        assertEquals("y != 25.0",25.0,v.y,0.001);
     }
    
    @Test
    public void testDotProduction() {
        Vector2D v1 = new Vector2D(1.0,0.0);
        Vector2D v2 = new Vector2D(0.0,10.0);
        
        //Dot product of two perpendicular vectors is zero
        double dot = v1.dot(v2);
        assertEquals("dot != 0.0",0.0,dot,0.001);     
    }
    
    @Test
    public void testVectorMagnitude() {
       Vector2D v = new Vector2D(10,5);
       
       // mag = sqrt(dot(v)) = sqrt(10*10+5*5) = sqrt(100+25) = sqrt(125) = 11.180
       double mag = v.mag();
       assertEquals("mag != 11.180",11.180,mag,0.001);
    }
    
    @Test
    public void testVectorNormalization() {
       Vector2D v= new Vector2D(100,100); 
       
       //After normalization length of the vector should not be larger than 1
       v.normalize();
       double mag = v.mag();
       assertTrue("mag > 1.0",mag <= 1.0 ? true : false);
    }
    
    @Test
    public void testVectorNegation() {
       assertTrue("Not implemented!",false);
    }
    
    @Test
    public void testAngleBetweenTwoVectors() {
       assertTrue("Not implemented!",false);
    }
    
    @Test
    public void testVectorProjection() {
       assertTrue("Not implemented!",false);
    }
    
}
