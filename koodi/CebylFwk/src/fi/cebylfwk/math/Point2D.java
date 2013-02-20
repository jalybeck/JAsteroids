package fi.cebylfwk.math;

/**
 * Simple container class which represents Point in 2D space.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Point2D {
    private double x,y;
    
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        reset();
    }

    public void reset() {
        x = 0;
        y = 0;
    }
    
    public void set(Point2D point) {
        this.x = point.getX();
        this.y = point.getY();
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
    
    public void addTo(double x, double y) {
        this.x += x;
        this.y += y;
    }
    
    public String toString() {
        return "("+x+","+y+")";    
    }
}
