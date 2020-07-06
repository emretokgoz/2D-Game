import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPanel extends JPanel {
    public MainPanel(MainFrame mainFrame) {
        this.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        JButton startButton = new JButton("START");
        setLayout(null);
        startButton.setBounds(175, 310, 120, 30);//245 130
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                GamePanel gamePanel = new GamePanel(mainFrame);
                mainFrame.getContentPane().add(gamePanel);
                gamePanel.requestFocusInWindow();
                mainFrame.revalidate(); //refresh screen
            }
        });
        this.add(startButton);

        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setBounds(175, 350, 120, 30);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                SettingsPanel settingsPanel = new SettingsPanel(mainFrame);
                mainFrame.getContentPane().add(settingsPanel);
                settingsPanel.requestFocusInWindow();
                mainFrame.revalidate(); //refresh screen
            }
        });
        this.add(settingsButton);

        this.setVisible(true);
    }
}
