package fi.cebylfwk.math;

/**
 * Simple container class which represents Point in 2D space.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Point2D {
    public double x,y;
    
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }
}
