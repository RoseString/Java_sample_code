import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This is the parent class of all Carnivore instances.
 *
 * @author Dan Sun
 * @version 1.0
 */
public abstract class Carnivore extends Fish {

    /**
     * Constructor for Carnivore class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public Carnivore(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Eat another fish. Make the other fish die.
     * Increase its health if another fish is not fugu.
     * Set its health to 0 if another fish is fugu.
     *
     * @param Another fish.
     */
    public void eatFish(Fish anotherFish) {
        anotherFish.die();
        if (anotherFish instanceof Fugu) {
            health = 0;
        } else if (health + 20 <= 100) {
            health += 20;
        } else {
            health = 100;
        }
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
     * Return whether the fish can eat another fish.
     *
     * @return True if it can.
     */
    public abstract boolean canEatFish(Fish anotherFish);

    /**
     * Get lifespan of a fish.
     *
     * @return The lifespan of a fish.
     */
    public abstract int getLifeSpan();

}
