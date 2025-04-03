import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        JButton b1 = new JButton("Hello, World!");
        JTextArea bal = new JTextArea("Hello");

        window.setSize(400, 400);
        window.setVisible(true);
        window.setTitle("Sigma Game Hatsune Miku");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.setSize(200,200);
        b1.setVisible(true);

    }
}