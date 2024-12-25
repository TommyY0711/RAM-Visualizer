package entry;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

public class GraphPanel extends JPanel {
	private final List<Rectangle> rectangles;
	private final double a, b;
	private final Function<Double, Double> function;

	public GraphPanel(RectangularApproximator approximator) {
		this.rectangles = approximator.getRectangles();
		this.a = approximator.getA();
		this.b = approximator.getB();
		this.function = approximator.getFunction();
	}

	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int margin = 50;

        double xRange = b - a;
        double yMax = getMaxY();
        double yMin = getMinY();

        drawAxes(g2d, margin, width, height, xRange, yMax, yMin);

        drawLine(g2d, margin, width, height, xRange, yMax, yMin);

        drawRectangles(g2d, margin, width, height, xRange, yMax, yMin);
    }

    private void drawAxes(Graphics2D g2d, int margin, int width, int height, double xRange, double yMax, double yMin) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margin, height - margin, width - margin, height - margin);
        g2d.drawLine(margin, margin, margin, height - margin);
    }

	private void drawLine(Graphics2D g2d, int margin, int width, int height, double xRange, double yMax, double yMin) {
        g2d.setColor(Color.BLUE);

        for (int i = 0; i < width - 2 * margin; i++) {
            double x1 = a + i * xRange / (width - 2 * margin);
            double y1 = function.apply(x1);

            double x2 = a + (i + 1) * xRange / (width - 2 * margin);
            double y2 = function.apply(x2);

            int screenX1 = mapToScreenX(x1, a, b, margin, width - margin);
            int screenY1 = mapToScreenY(y1, yMin, yMax, height - margin, margin);

            int screenX2 = mapToScreenX(x2, a, b, margin, width - margin);
            int screenY2 = mapToScreenY(y2, yMin, yMax, height - margin, margin);

            g2d.drawLine(screenX1, screenY1, screenX2, screenY2);
        }
    }

	private void drawRectangles(Graphics2D g2d, int margin, int width, int height, double xRange, double yMax,
			double yMin) {
		g2d.setColor(new Color(255, 0, 0, 100));

		for (Rectangle rect : rectangles) {
			int rectX = mapToScreenX(rect.getX(), a, b, margin, width - margin);
			int rectWidth = (int) ((rect.getWidth() / xRange) * (width - 2 * margin));
			int rectHeight = Math.abs(mapToScreenY(rect.getHeight(), yMin, yMax, height - margin, margin)
					- mapToScreenY(0, yMin, yMax, height - margin, margin));

			g2d.fillRect(rectX, mapToScreenY(rect.getHeight(), yMin, yMax, height - margin, margin), rectWidth,
					rectHeight);
		}
	}

	private int mapToScreenX(double x, double a, double b, int minX, int maxX) {
		return (int) ((x - a) / (b - a) * (maxX - minX) + minX);
	}

	// Do not switch minY and maxY
	private int mapToScreenY(double y, double minY, double maxY, int minYScreen, int maxYScreen) {
		return maxYScreen - (int) ((y - maxY) / (minY - maxY) * (maxYScreen - minYScreen));
	}

	private double getMaxY() {
		double maxY = Double.MIN_VALUE;
		for (Rectangle rect : rectangles) {
			maxY = Math.max(maxY, rect.getHeight());
		}
		return maxY + 1;
	}

	private double getMinY() {
		return 0;
	}
}
