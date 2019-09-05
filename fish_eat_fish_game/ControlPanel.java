import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the ControlPanel for the Ocean. It allows the
 * user to pick which Fish it would like to add next.
 *
 * @author Doug
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private JButton surgeonfish, parrotfish, shark,
                giantSquid, fugu, mermaid, cleared, exit;
    private JLabel current;

    private String fishType;

    public ControlPanel() {
        setPreferredSize(new Dimension(150, OceanPanel.HEIGHT));

        surgeonfish = new JButton("SurgeonFish");
        surgeonfish.addActionListener(new ButtonListener("SurgeonFish"));

        add(surgeonfish);

        parrotfish = new JButton("ParrotFish");
        parrotfish.addActionListener(new ButtonListener("ParrotFish"));
        add(parrotfish);

        shark = new JButton("Shark");
        shark.addActionListener(new ButtonListener("Shark"));
        add(shark);

        giantSquid = new JButton("GiantSquid");
        giantSquid.addActionListener(new ButtonListener("GiantSquid"));
        add(giantSquid);

        fugu = new JButton("Fugu");
        fugu.addActionListener(new ButtonListener("Fugu"));
        add(fugu);

        mermaid = new JButton("Mermaid");
        mermaid.addActionListener(new ButtonListener("Mermaid"));
        add(mermaid);

        cleared = new JButton("Clear");
        cleared.addActionListener(new ButtonListener("Cleared"));
        add(cleared);

        exit = new JButton("Exit");
        exit.addActionListener(new ExitListener());
        add(exit);

        //default starting fish
        fishType = "SurgeonFish";
        add(new JLabel("Current Fish"));
        current = new JLabel("SurgeonFish");
        add(current);

        //implement timer speed control if you feel adventurous
    }

    /**
     * Invoked by OceanPanel to determine which Fish
     * was chosen.
     *
     * @return The currently selected Fish type
     */
    public String getFishType() {
        return fishType;
    }

    public class ButtonListener implements ActionListener {
        private String name;

        public ButtonListener(String className) {
            name = className;
        }

        public void actionPerformed(ActionEvent e) {
            fishType = name;
            current.setText(name);
        }
    }

    public class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
