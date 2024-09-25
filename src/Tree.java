import java.awt.*;
import java.awt.geom.Line2D;

public class Tree {

    // Recursive branching function
    public void drawTree(Graphics2D g2d, int level, Point start, double angle, double length) {
        if (level <= 0) return;

        Point end = drawLine(g2d, start, angle, length);

        if (Math.random() < 0.8)
            drawTree(g2d, level - 1, end, angle - 25 + randomizeAngle(), length * 0.60 * randomizeLen());
        drawTree(g2d, level - 1, end, angle + 15 + randomizeAngle(), length * 0.80 * randomizeLen());

        if (Math.random() < 0.7)
            drawTree(g2d, level - 2, end, angle + 45 + randomizeAngle(), length * 0.40 * randomizeLen());
    }

    private Point drawLine(Graphics2D g2d, Point start, double direction, double length) {
        double dir_rad = Math.toRadians(direction);
        int endX = (int) (start.x + length * Math.sin(dir_rad));
        int endY = (int) (start.y - length * Math.cos(dir_rad));
        Point end = new Point(endX, endY);

        int colorVal = Math.min(90, (int) (60 + (90 - length * 1.5)));
        int alpha = (int) Math.min(255, Math.max(0, 255 * (0.2 + length * 0.04)));  // Clamp alpha between 0 and 255

        g2d.setColor(new Color(60, colorVal, 60, alpha));
        g2d.setStroke(new BasicStroke((float) Math.max(1, length * 0.07), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Draw the line
        g2d.draw(new Line2D.Float(start.x, start.y, end.x, end.y));
        return end;
    }

    // Angle randomization
    private double randomizeAngle() {
        return (-0.5 + Math.random()) * 25;
    }

    // Length randomization
    private double randomizeLen() {
        return 0.9 + (Math.random() * 0.2);
    }
}
