import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a giant squid.
 * Giant squid is a carnivore.
 * Giant squid can never have baby.
 * It can eat any fish.
 * It moves vertically
 * and it has a relatively long lifespan.
 *
 * @author Dan Sun
 * @version 1.0
 */
public class GiantSquid extends Carnivore {

    private int lifeSpan = 150;
    private int velocity = 10;
    private int yDirection = -1;

    /**
     * Constructor for GiantSquid class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public GiantSquid(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Draw fish on the graphics contexts.
     *
     * @param g Contexts that allow users to draw fish on.
     */
    public void draw(Graphics g) {
        try {
            Image image = ImageIO.read(
                new File("giantSquid.png"));
            g.drawImage(image, x, y, null);
        } catch (IOException e) {
            System.out.println("Cannot find the input image!");
        }
    }

    /**
     * Move fish.
     */
    public void move() {
        Random xRand = new Random();
        int xDirection = xRand.nextInt(3) - 1;

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

        health -= 0.1;
    }

    /**
     * Return whether a fish can reproduce with another fish.
     *
     * @param anotherFish Another fish.
     * @return True if they can reproduce babies.
     */
    public boolean canReproduceWithFish(Fish anotherFish) {
        return false;
    }

    /**
     * Return a array list that contains babies.
     *
     * @param anotherFish Another fish.
     * @return A array list that contains babies.
     */
    public ArrayList<Fish> reproduceWithFish(Fish anotherFish) {
        return null;
    }

    /**
     * Return whether the fish can eat another fish.
     *
     * @return True if it can.
     */
    public boolean canEatFish(Fish anotherFish) {
        if (anotherFish.health >= 50
            || anotherFish instanceof GiantSquid) {
            return false;
        }
        return true;
    }

    /**
     * Get lifespan of a fish.
     *
     * @return The lifespan of a fish.
     */
    public int getLifeSpan() {
        return lifeSpan;
    }
}
