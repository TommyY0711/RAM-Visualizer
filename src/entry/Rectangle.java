package entry;

public class Rectangle {
    private final double x;      // X-coordinate of the left edge
    private final double width;  // Width of the rectangle
    private final double height; // Height of the rectangle

    public Rectangle(double x, double width, double height) {
        this.x = x;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
