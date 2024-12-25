package entry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RectangularApproximator {
	
	private final double a, b;
    private final int n;
    private final String method;
    private final Function<Double, Double> function;
    private final List<Rectangle> rectangles;

    public RectangularApproximator(double a, double b, int n, String method, Function<Double, Double> function) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.method = method.toLowerCase();
        this.function = function;
        this.rectangles = new ArrayList<>();
        createRectangles();
    }

    private void createRectangles() {
        double deltaX = (b - a) / n;

        for (int i = 0; i < n; i++) {
            double x = 0.0;

            switch (method) {
                case "left":
                    x = a + i * deltaX;
                    break;
                case "right":
                    x = a + (i + 1) * deltaX;
                    break;
                case "midpoint":
                    x = a + (i + 0.5) * deltaX;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid method. Choose left, right, or midpoint.");
            }

            double height = function.apply(x);
            rectangles.add(new Rectangle(a + i * deltaX, deltaX, height));
        }
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }
}
