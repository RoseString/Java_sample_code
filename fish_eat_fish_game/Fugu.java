import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a fugu.
 * Fugu is a carnivore.
 * Fugu has a high fertility rate,
 * and can reproduce 1-2 children per time.
 * Any fish that eats fugu will die of toxin.
 * Fugu can only eat parrot fish
 * and surgeon fish.
 *
 * @author Dan Sun
 * @version 1.0
 */
public class Fugu extends Carnivore {

    private int lifeSpan = 60;
    private double fertilityRate = 0.03;
    private int velocity = 20;
    private int xDirection = -1;

    /**
     * Constructor for Fugu class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public Fugu(int x, int y, Rectangle bounds) {
        super(x, y, bounds);
    }

    /**
     * Draw fish on the graphics contexts.
     *
     * @param g Contexts that allow users to draw fish on.
     */
    public void draw(Graphics g) {
        Image image = null;
        try {
            if (xDirection == 1) {
                image = ImageIO.read(
                    new File("fugu.png"));
            } else {
                image = ImageIO.read(
                    new File("fuguFlipped.png"));
            }
            g.drawImage(image, x, y, null);
        } catch (IOException e) {
            System.out.println("Cannot find the input image!");
        }
    }

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

        health -= 0.1;
    }

    /**
     * Return whether a fish can reproduce with another fish.
     *
     * @param anotherFish Another fish.
     * @return True if they can reproduce babies.
     */
    public boolean canReproduceWithFish(Fish anotherFish) {
        double r = Math.random();
        if (anotherFish.getClass().equals(getClass())
            && r <= fertilityRate) {
            return true;
        }
        return false;
    }

    /**
     * Return a array list that contains babies.
     *
     * @param anotherFish Another fish.
     * @return A array list that contains babies.
     */
    public ArrayList<Fish> reproduceWithFish(Fish anotherFish) {

        ArrayList<Fish> babies = new ArrayList<>(2);

        Random rand = new Random();
        int babyLimit = rand.nextInt(2) + 1;
        for (int i = 0; i < babyLimit; i++) {
            Random xRand = new Random();
            Random yRand = new Random();
            babies.add(new Fugu(x + xRand.nextInt(101) - 50,
                y + yRand.nextInt(101) - 50, bounds));
        }
        return babies;
    }

    /**
     * Return whether the fish can eat another fish.
     *
     * @return True if it can.
     */
    public boolean canEatFish(Fish anotherFish) {

        if (anotherFish.health >= 50
            || anotherFish instanceof GiantSquid
            || anotherFish instanceof Shark
            || anotherFish instanceof Fugu
            || anotherFish instanceof Mermaid) {
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
