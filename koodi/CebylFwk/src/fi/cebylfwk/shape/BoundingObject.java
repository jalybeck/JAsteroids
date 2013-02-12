package fi.cebylfwk.shape;

/**
 * Bounding object interface.
 * Classes which would be used in object collision checks,
 * should implement this interface.
 * 
 * @param <T>
 */
public interface BoundingObject<T extends BoundingObject> {
    public boolean intersects(T bObj);
    public T getBoundingObject();
}
