import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a mermaid.
 * Mermaid is a herbivore.
 * Mermaid has a low fertility rate.
 * It can only reproduce one per time.
 * It has a very long lifespan.
 * It can be eaten by shark and giant squid.
 *
 * @author Dan Sun
 * @version 1.0
 */
public class Mermaid extends Herbivore {

    private int lifeSpan = 1000;
    private double fertilityRate = 0.01;

    /**
     * Constructor for Mermaid class.
     *
     * @param x Horizontal coordinate of the clicked point.
     * @param y Vertical coordinate of the clicked point.
     * @param bounds The bounds for the ocean.
     */
    public Mermaid(int x, int y, Rectangle bounds) {
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
            if (xDirection == -1) {
                image = ImageIO.read(new File("mermaid.png"));
            } else {
                image = ImageIO.read(
                    new File("mermaidFlipped.png"));
            }
            g.drawImage(image, x, y, null);
        } catch (IOException e) {
            System.out.println("Cannot find the input image!");
        }
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

        ArrayList<Fish> babies = new ArrayList<>(1);
        Random xRand = new Random();
        Random yRand = new Random();
        babies.add(new Mermaid(x + xRand.nextInt(101) - 50,
                y + yRand.nextInt(101) - 50, bounds));
        return babies;
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
