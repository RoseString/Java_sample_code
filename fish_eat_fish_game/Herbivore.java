import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the parent class of all Herbivore instances.
 *
 * @author Dan Sun
 * @version 1.0
 */
public abstract class Herbivore extends Fish {

    private int velocity = 10;
    protected int xDirection = 1;

    /**
     * Constructor for Herbivore class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public Herbivore(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Eat Seaweed to entirely increase health.
     */
    public void eatSeaweed() {
        health = 100;
    }

    /**
     * Return whether a fish can reproduce with another fish.
     *
     * @param anotherFish Another fish.
     * @return True if they can reproduce babies.
     */
    public abstract boolean canReproduceWithFish(Fish anotherFish);

    /**
     * Draw fish on the graphics contexts.
     *
     * @param g Contexts that allow users to draw fish on.
     */
    public abstract void draw(Graphics g);

    /**
     * Move fish.
     */
    public void move() {
        Random yRand = new Random();
        int yDirection = yRand.nextInt(3) - 1;

        if (x >= bounds.getWidth() - 100) {
            xDirection = -1;
        } else if (x <= 0) {
            xDirection = 1;
        } else if ((x + velocity * xDirection >= bounds.getWidth() - 100)
            || (x + velocity * xDirection <= 0)) {
            xDirection = -xDirection;
        }

        x = x + velocity * xDirection;

        if (y >= bounds.getHeight() - 70) {
            yDirection = -1;
        } else if (y <= 0) {
            yDirection = 1;
        } else if ((y + velocity * yDirection >= bounds.getHeight() - 70)
            || (y + velocity * yDirection <= 0)) {
            yDirection = -yDirection;
        }
        y = y + velocity * yDirection;

        health--;

        if (health <= 20) {
            this.eatSeaweed();
        }
    }

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
