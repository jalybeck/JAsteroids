package fi.cebylfwk.graphics;
/**
 * Simple color container class.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Color {
    private float r,g,b,a;
    
    public Color(float r,float g, float b, float a) {
        setRGBA(r,g,b,a);
    }
    
    public void setRGBA(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }
}
