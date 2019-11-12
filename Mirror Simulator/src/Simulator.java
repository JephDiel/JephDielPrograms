import com.sun.org.apache.bcel.internal.generic.JsrInstruction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Simulator extends JPanel {

    private JSlider a;
    private JSlider b;
    private JSlider c;
    private JSlider xOffset;
    private JSlider yOffset;
    private JLabel equation;
    private Image image;

    private final int STEPS = 500;
    private final int RESOLUTION = 100;
    private final double PRECISION = 10000;

    public Simulator() {
        SliderListener listener = new SliderListener();
        a = new JSlider(-100, 100);
        a.setBorder(BorderFactory.createTitledBorder("A"));
        a.addChangeListener(listener);

        b = new JSlider(-100, 100);
        b.setBorder(BorderFactory.createTitledBorder("B"));
        b.addChangeListener(listener);

        c = new JSlider(-100, 100);
        c.setBorder(BorderFactory.createTitledBorder("C"));
        c.addChangeListener(listener);

        xOffset = new JSlider(0, 1000);
        xOffset.setBorder(BorderFactory.createTitledBorder("X Offset"));
        xOffset.addChangeListener(listener);

        yOffset = new JSlider(-1000, 1000);
        yOffset.setBorder(BorderFactory.createTitledBorder("Y Offset"));
        yOffset.addChangeListener(listener);

        image = new Image();

        equation = new JLabel("y = " + a.getValue() + "x^3 + " + b.getValue() + "x^2 + " + c.getValue() + "x");
        equation.setFont(new Font("Times New Roman", Font.BOLD, 24));

        JPanel options = new JPanel();
        options.add(a);
        options.add(b);
        options.add(c);
        options.add(xOffset);
        options.add(yOffset);
        //options.add(equation);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(options);
        this.add(equation);
        this.add(image);

    }

    private class Image extends JPanel {

        Point[] points = new Point[STEPS];

        public Image() {
            this.setPreferredSize(new Dimension(1000, 1000));
        }

        public void paintComponent(Graphics g) {
            for (int i = 0; i < STEPS; i++) {
                double x = i;// / (double)STEPS;
                double y =  this.getHeight() - ((a.getValue() / (PRECISION * PRECISION) * x * x * x) + (b.getValue() / PRECISION  * x * x) + (c.getValue() * x));
                points[i] = new Point((int)(x), (int)(y));
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
                    g.drawLine(points[i - 1].x + xOffset.getValue(), points[i - 1].y + yOffset.getValue(), points[i].x + xOffset.getValue(), points[i].y + yOffset.getValue());
                }
            }
        }
    }

    private class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            equation.setText("y = " + a.getValue() + "x^3 + " + b.getValue() + "x^2 + " + c.getValue() + "x");
            Simulator.this.repaint();
        }
    }
}
