/*
*Authors: Tamuka Manjemu & Anthon
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BranchingTreeApp extends JPanel {

    private Tree tree;
    private Tree secondTree;
    private Seesaw seesaw;

    public BranchingTreeApp() {
        setBackground(Color.WHITE);
        tree = new Tree();
        secondTree = new Tree();
        seesaw = new Seesaw();

        // Timer for animation (repaints every 50ms)
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seesaw.updateSeesaw();  // Only update the seesaw's state
                repaint();  // Repaint the screen
            }
        });
        timer.start();  // Start the animation timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the ground level (top of the grass)
        int groundLevel = getHeight() / 2 + 100;

        // Draw the nature background elements
        drawSea(g2d);
        drawGrass(g2d);

        // Draw the sun and birds
        drawSun(g2d);
        drawBirds(g2d);

        // Draw the trees standing on the green ground
        tree.drawTree(g2d, 12, new Point(150, groundLevel), 10, 90);  // First tree on the left
        secondTree.drawTree(g2d, 12, new Point(getWidth() - 150, groundLevel), 10, 90);  // Second tree on the right

        // Draw the seesaw, which is positioned relative to the window size
        seesaw.drawSeesaw(g2d, getWidth() / 2, getHeight() / 2 + 100);
    }

    // Method to draw the sea as a half-ellipse
    private void drawSea(Graphics2D g2d) {
        g2d.setColor(new Color(100, 149, 237));  // Cornflower blue color
        g2d.fillArc(-getWidth() / 2, getHeight() / 2, getWidth() * 2, getHeight(), 0, 180);  // Half ellipse for sea
    }

    // Method to draw grass at the bottom of the screen
    private void drawGrass(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34));  // Forest green color for grass
        g2d.fillRect(0, getHeight() / 2 + 100, getWidth(), getHeight() / 2);  // Green rectangle for grass
    }

    // Method to draw the sun above the sea
    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        int sunRadius = 50;
        int sunX = getWidth() / 2 - sunRadius;
        int sunY = getHeight() / 2 - 150;
        g2d.fillOval(sunX, sunY, sunRadius * 2, sunRadius * 2);  // Sun as a yellow circle

        // Draw sun rays
        for (int i = 0; i < 12; i++) {
            double angle = i * Math.PI / 6;  // Angle for each ray
            int rayX = (int) (sunX + sunRadius + Math.cos(angle) * sunRadius * 2);
            int rayY = (int) (sunY + sunRadius + Math.sin(angle) * sunRadius * 2);
            g2d.drawLine(sunX + sunRadius, sunY + sunRadius, rayX, rayY);
        }
    }

    // Method to draw flying birds in the sky
    private void drawBirds(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {  // Draw 5 birds
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight() / 3);  // Birds in the upper third of the screen
            drawBird(g2d, x, y);
        }
    }

    // Method to draw a single bird (simple 'M' shape)
    private void drawBird(Graphics2D g2d, int x, int y) {
        int wingSpan = 20;
        g2d.drawArc(x, y, wingSpan, 10, 0, 180);  // Left wing
        g2d.drawArc(x + wingSpan, y, wingSpan, 10, 0, 180);  // Right wing
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Branching Tree and Seesaw Animation");
        BranchingTreeApp treeApp = new BranchingTreeApp();

        // Resizable window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(treeApp);
        frame.setVisible(true);
    }
}
