package entry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Controller {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double a = 1;
            double b = 3;
            int n = 4;
            String method = "midpoint"; // left, right, midpoint

            RectangularApproximator approximator = new RectangularApproximator(a, b, n, method, x -> x * x + x * 4);

            JFrame frame = new JFrame("Rectangular Approximation Method");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GraphPanel(approximator));
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
