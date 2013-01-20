package fi.cebylfwk.math;

/**
 * 2D vector math
 * Vectors are assumed always to be starting from origin(0,0) in Cartesian coordinate system.
 * Reference used: http://en.wikipedia.org/wiki/Euclidean_vector
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Vector2D {
    double x,y;
    
    public Vector2D() {
        x = 0;
        y = 0;
    }

    Vector2D(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public Vector2D add(Vector2D v2) {
        this.x += v2.x;
        this.y += v2.y;
        
        return this;
    }

    public Vector2D sub(Vector2D v2) {
        this.x -= v2.x;
        this.y -= v2.y;
        
        return this;
    }

    public Vector2D mul(double s) {
        this.x *= s;
        this.y *= s;
        
        return this;
        
    }

    public double dot(Vector2D vec) {
        return this.x * vec.x + this.y * vec.y;
    }
    
    /**
     * Vector magnitude can be expressed as |v| = sqrt(dot(v))
     * 
     * @return lenght or magnitude of this vector
     */
    public double mag() {
        return Math.sqrt(dot(this));
    }
    
    /**
     * Makes vector to be unit length
     * 
     * @return unit length vector
     */
    public Vector2D normalize() {
        double mag = mag();
        if(mag != 0.0) {
            this.x = this.x / mag;
            this.y = this.y / mag;
        }
        
        return this;
    }
}
