import java.util.concurrent.TimeUnit;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This is the parent class of all Fish classes.
 *
 * @author Dan Sun
 * @version 1.0
 */
public abstract class Fish {

    protected int x, y;
    protected Rectangle bounds;
    protected int health;
    protected long startTime;

    /**
     * Constructor for Fish class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public Fish(int x, int y, Rectangle bounds) {
        this.x = x;
        this.y = y;
        this.bounds = bounds;
        this.startTime = System.nanoTime();
        this.health = 100;
    }

    /**
     * Get the lifetime of a fish.
     * @return The lifetime of a fish.
     */
    public double lifeTime() {
        long timeElapsed = System.nanoTime() - startTime;
        return TimeUnit.NANOSECONDS.toSeconds(timeElapsed);
    }

    /**
     * Return whether a fish can collide with another fish.
     * @return True if they can collide.
     */
    public boolean collidesWithFish(Fish anotherFish) {
        if ((Math.abs(x - anotherFish.x) < 30)
            && (Math.abs(y - anotherFish.y) < 30)) {
            return true;
        }
        return false;
    }

    /**
     * Make the fish die if its health equals to 0;
     */
    public void die() {
        health = 0;
    }

    /**
     * Draw fish on the graphics contexts.
     *
     * @param g Contexts that allow users to draw fish on.
     */
    public abstract void draw(Graphics g);

    /**
     * Move fish.
     */
    public abstract void move();

    /**
     * Return whether a fish can reproduce with another fish.
     *
     * @param anotherFish Another fish.
     * @return True if they can reproduce babies.
     */
    public abstract boolean canReproduceWithFish(Fish anotherFish);

    /**
     * Return a array list that contains babies.
     *
     * @param anotherFish Another fish.
     * @return A array list that contains babies.
     */
    public abstract ArrayList<Fish> reproduceWithFish(Fish anotherFish);

    /**
     * Get lifespan of a fish.
     *
     * @return The lifespan of a fish.
     */
    public abstract int getLifeSpan();
}
