
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartMenuFrame startMenuFrame = new StartMenuFrame();
                startMenuFrame.setVisible(true);
            }
        });
    }

    static class StartMenuFrame extends JFrame {
        public StartMenuFrame() {
            setTitle("Flappy Bird");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

            JButton startButton = new JButton("Start Game");
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Menutup StartMenuFrame
                    openGameFrame(); // Membuka JFrame game FlappyBird
                }
            });

            panel.add(startButton);
            add(panel, BorderLayout.CENTER);
        }

        private void openGameFrame() {
            JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add (flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
        }
    }
}