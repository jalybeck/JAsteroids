package fi.cebylfwk.shape;

/**
 * Bounding object interface.
 * Classes which would be used in object collision checks,
 * should implement this interface.
 * 
 * @param <T>
 */
public interface BoundingObject<T extends BoundingObject> {
    /**
     * Tests interection between parameter object and this object.
     * 
     * @param bObj Object which extends BoundingObject interface
     * @return true if intersection happens
     */
    public boolean intersects(T bObj);
}
