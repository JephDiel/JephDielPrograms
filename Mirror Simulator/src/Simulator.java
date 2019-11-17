import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Simulator extends JPanel {

    private JSlider a;
    private JSlider b;
    private JSlider c;
    private JSlider xOffset;
    private JSlider yOffset;
    private JSlider xPosition;
    private JSlider yPosition;
    private JSlider scale;
    private JLabel equation;
    private Image image;

    private final int STEPS = 500;
    private final int RESOLUTION = 100;
    private final double PRECISIONA = 100000000;
    private final double PRECISIONB = 100000;
    private final double PRECISIONC = 1000;

    private final int RAYS = 1000;
    private final int FOV = 90;

    public Simulator() {
        SliderListener listener = new SliderListener();
        a = new JSlider(-1000, 1000);
        a.setBorder(BorderFactory.createTitledBorder("A"));
        a.addChangeListener(listener);

        b = new JSlider(-1000, 1000);
        b.setBorder(BorderFactory.createTitledBorder("B"));
        b.addChangeListener(listener);

        c = new JSlider(-1000, 1000);
        c.setBorder(BorderFactory.createTitledBorder("C"));
        c.addChangeListener(listener);

        xOffset = new JSlider(0, 1000);
        xOffset.setBorder(BorderFactory.createTitledBorder("X Offset"));
        xOffset.addChangeListener(listener);

        yOffset = new JSlider(-500, 300);
        yOffset.setBorder(BorderFactory.createTitledBorder("Y Offset"));
        yOffset.addChangeListener(listener);

        xPosition = new JSlider(-1000, 1000);
        xPosition.setBorder(BorderFactory.createTitledBorder("X Position"));
        xPosition.addChangeListener(listener);

        yPosition = new JSlider(-500, 500);
        yPosition.setBorder(BorderFactory.createTitledBorder("Y Position"));
        yPosition.addChangeListener(listener);

        scale = new JSlider(1, 100);
        scale.setBorder(BorderFactory.createTitledBorder("Scale"));
        scale.addChangeListener(listener);

        image = new Image();

        equation = new JLabel("y = " + a.getValue() + "x^3 + " + b.getValue() + "x^2 + " + c.getValue() + "x");
        equation.setFont(new Font("Times New Roman", Font.BOLD, 24));

        JPanel options = new JPanel();
        options.add(a);
        options.add(b);
        options.add(c);
        options.add(xOffset);
        options.add(yOffset);
        options.add(xPosition);
        options.add(yPosition);
        options.add(scale);
        options.add(equation);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(options);
        this.add(equation);
        this.add(image);

    }

    private class Image extends JPanel {

        double[][] points = new double[STEPS][2];

        public Image() {
            this.setPreferredSize(new Dimension(500, 500));
        }

        public void paintComponent(Graphics g) {
            for (int i = 0; i < STEPS; i++) {
                double x = i;// / (double)STEPS;
                double y =  this.getHeight() + ((a.getValue() / PRECISIONA) * x * x * x) + ((b.getValue() / PRECISIONB)  * x * x) + ((c.getValue() / PRECISIONC) * x);
                points[i][0] = x;
                points[i][1] = y;
                g.setColor(Color.BLACK);
                //g.fillRect(0,0,20,20);
                if (i != 0) {
                    /*
                    g.drawLine(
                            (int)((oldPoint.x * (getWidth() / RESOLUTION * 0.8)) + (getWidth() / 0.1)),
                            (int)((oldPoint.y * (getWidth() / RESOLUTION * 0.8)) + (getWidth() / 0.1)),
                            (int)((point.x * (getWidth() / RESOLUTION * 0.8)) + (getWidth() / 0.1)),
                            (int)((point.y * (getWidth() / RESOLUTION * 0.8)) + (getWidth() / 0.1)));

                     */
                    g.setColor(Color.BLACK);
                    drawLine(points[i][0], points[i][1], points[i - 1][0], points[i - 1][1], g);
                    //g.drawLine((int)points[i][0] + xOffset.getValue(), (int)points[i][1] + yOffset.getValue(), (int)points[i - 1][0] + xOffset.getValue(), (int)points[i - 1][1] + yOffset.getValue());

                    g.setColor(Color.YELLOW.darker());
                    //g.drawLine((int)points[i][0] + xOffset.getValue(), (int)points[i][1] + yOffset.getValue(), xPosition.getValue() + xOffset.getValue(), yPosition.getValue() + yOffset.getValue());
                    drawLine(points[i][0], points[i][1], xPosition.getValue(), yPosition.getValue(), g);

                    //double m1 = (points[i][1] - yPosition.getValue()) / (points[i][1] - yPosition.getValue());

                    double incoming = Math.atan2(points[i][1] - yPosition.getValue(), points[i][0] - xPosition.getValue());

                    //double m2 = (points[i][1] - points[i - 1][1]) / (points[i][0] - points[i - 1][0]);

                    double surface = Math.atan2(points[i][1] - points[i - 1][1], points[i][0] - points[i - 1][0]);
                    //System.out.println((points[i][1] - points[i - 1][1]));
                    //m1 *= -1;
                    //m1 = 1 / m1;

                    //m2 *= -1;

                    //double normal = -1 / m2;

                    double normal = surface + (Math.PI  / 2);

                    double delta = normal - incoming;

                    double reflection = normal + delta + Math.PI;
                    //double m3 = ((2 * m1) + (m2 * Math.pow(m1, 2)) - m2) / (2 * m1 * m2 - Math.pow(m1, 2) + 1); //Behold the power of the internet!
                    //m3 = 1 / m3;
                    //m3 *= -1;
                    //System.out.println(m2);
                    //g.drawLine((int)points[i][0] + xOffset.getValue(), (int)points[i][1] + yOffset.getValue(), (int)points[i][0] - 500 + xOffset.getValue(), (int)(points[i][1] + (500 * m3)) + yOffset.getValue());
                    g.setColor(Color.YELLOW.darker());
                    drawLine(points[i][0], points[i][1], points[i][0] + (1000 * Math.cos(reflection)), points[i][1] + (1000 * Math.sin(reflection)), g);
                }
            }
        }

        private void drawLine(double x1, double y1, double x2, double y2, Graphics g) {
            int ix1 = (int)((x1 + xOffset.getValue()) * (scale.getValue() / 50.0));
            int iy1 = (int)((y1 + yOffset.getValue()) * (scale.getValue() / 50.0));
            int ix2 = (int)((x2 + xOffset.getValue()) * (scale.getValue() / 50.0));
            int iy2 = (int)((y2 + yOffset.getValue()) * (scale.getValue() / 50.0));

            g.drawLine(ix1, getHeight() - iy1, ix2, getHeight() - iy2);
        }
    }

    private class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            equation.setText("y = " + (a.getValue() / PRECISIONA) + "x^3 + " + (b.getValue() / PRECISIONB) + "x^2 + " + (c.getValue() / PRECISIONC) + "x");
            Simulator.this.repaint();
        }
    }
}
