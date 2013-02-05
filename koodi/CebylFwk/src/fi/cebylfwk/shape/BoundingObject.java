package fi.cebylfwk.shape;

public interface BoundingObject<T extends BoundingObject> {
    public boolean intersects(T bObj);
    public T getBoundingObject();
}
