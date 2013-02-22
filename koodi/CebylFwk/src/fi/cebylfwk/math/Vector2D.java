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
    private double x,y;
    
    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vec) {
        this(vec.x,vec.y);
    }
    
    /**
     * Vector addition.
     * 
     * @param v2
     * @return Current vector added by parameter vector.
     */
    public Vector2D add(Vector2D v2) {
        this.x += v2.x;
        this.y += v2.y;
        
        return this;
    }
    
    /**
     * Vector substitution.
     * 
     * @param v2
     * @return Current vector substituted by given parameter vector.
     */
    public Vector2D sub(Vector2D v2) {
        this.x -= v2.x;
        this.y -= v2.y;
        
        return this;
    }
    
    /**
     * Vector multiplication by scalar.
     * 
     * @param s
     * @return Current vector multiplied by scalar.
     */
    public Vector2D mul(double s) {
        this.x *= s;
        this.y *= s;
        
        return this;
    }
    
    /**
     * Calculates the dot product of the two vectors.
     * 
     * @param vec
     * @return Dot production of two vectors.
     */
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
    
    /**
     * Vector negation.
     * Changes sign of the vector x and y components.
     * 
     * @return Current negated vector.
     */
    public Vector2D negate() {
        this.x = -this.x;
        this.y = -this.y;
        
        return this;
    }
    
    /**
     * Projects 2D vector given as parameter on to this vector.
     * 
     * @param vec
     * @return Projected vector.
     */
    public Vector2D createProjectionVector(Vector2D vec) {
        Vector2D projVec = new Vector2D(0,0);
        
        Vector2D a = new Vector2D(this);
        Vector2D b = new Vector2D(vec);
        double divider = b.x * b.x + b.y * b.y;
        if(divider == 0.0) {
            return projVec;
        }
        double dotProd = a.dot(b);
        double dotPerDiv = dotProd / divider;
        projVec.x = dotPerDiv * b.x;
        projVec.y = dotPerDiv * b.y;
        
        return projVec;
    }

    /**
     * Create unit length direction vector
     *
     * @param angle as degrees
     */
    public void setDirectionByAngle(double angle) {
        double radians = Math.toRadians(angle);
        
        double xComp =  Math.sin(radians);
        double yComp =  Math.cos(radians);
        
        this.x = xComp;
        this.y = yComp;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
