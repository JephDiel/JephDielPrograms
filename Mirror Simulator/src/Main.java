import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mirror Simulator");
        Simulator simulator = new Simulator();
        frame.add(simulator);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
