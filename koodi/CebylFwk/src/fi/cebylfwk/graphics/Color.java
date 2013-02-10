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

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public float getAlpha() {
        return a;
    }
}
