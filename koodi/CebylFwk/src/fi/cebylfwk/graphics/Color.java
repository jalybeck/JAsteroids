package fi.cebylfwk.graphics;
/**
 * Simple color container class.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Color {
    /**
     * Red color component.
     */
    private float red;
    
    /**
     * Green color component.
     */
    private float green;
    
    /**
     * Blue color component.
     */
    private float blue;
    
    /**
     * Color alpha component.
     */
    private float alpha;
    
    public Color(float r,float g, float b, float a) {
        setRGBA(red,green,blue,alpha);
    }
    
    public void setRGBA(float r, float g, float b, float a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }
}
