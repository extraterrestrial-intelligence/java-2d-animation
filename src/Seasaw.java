import javax.swing.*;
import java.awt.*;

class Seesaw {
    private double angle = 0;  // Angle for the seesaw tilt
    private boolean goingUp = true;  // Direction control for the motion

    public void drawSeesaw(Graphics2D g2d, int centerX, int centerY) {
        // Rotate around the center of the seesaw for the tilting effect
        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(angle));

        // Draw the seesaw base (triangle)
        int[] xPoints = {-50, 0, 50};
        int[] yPoints = {50, 0, 50};
        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw the seesaw plank (line)
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(-150, 0, 150, 0);

        // Draw child 1 (left side)
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-175, -70, 50, 50); // Head
        g2d.drawLine(-150, -20, -150, 30); // Body
        g2d.drawLine(-150, 30, -170, 60); // Left leg
        g2d.drawLine(-150, 30, -130, 60); // Right leg
        g2d.drawLine(-150, 0, -170, -10); // Left arm (holding handle)
        g2d.drawLine(-150, 0, -130, -10); // Right arm (waving)

        // Draw child 2 (right side)
        g2d.fillOval(125, -70, 50, 50); // Head
        g2d.drawLine(150, -20, 150, 30); // Body
        g2d.drawLine(150, 30, 130, 60); // Left leg
        g2d.drawLine(150, 30, 170, 60); // Right leg
        g2d.drawLine(150, 0, 130, -10); // Left arm (holding handle)
        g2d.drawLine(150, 0, 170, -10); // Right arm (holding handle)

        // Reset transformation
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-centerX, -centerY);
    }

    // Method to update the angle of the seesaw for animation
    public void updateSeesaw() {
        if (goingUp) {
            angle += 2;  // Increase angle
            if (angle >= 30) {
                goingUp = false;  // Reverse direction
            }
        } else {
            angle -= 2;  // Decrease angle
            if (angle <= -30) {
                goingUp = true;  // Reverse direction
            }
        }
    }
}
