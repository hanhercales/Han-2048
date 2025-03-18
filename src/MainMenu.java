import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenu extends JFrame implements ActionListener {
    private JButton playButton;
    private JButton exitButton;

    public MainMenu() {
        setTitle("2048 Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        playButton = new JButton("Play");
        playButton.addActionListener(this);
        panel.add(playButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            new Game2048();
            dispose(); // Đóng cửa sổ menu
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}