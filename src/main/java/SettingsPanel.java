import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPanel extends JPanel {
    public SettingsPanel(MainFrame mainFrame) {

        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        JButton asd = new JButton("asd");
        JButton returnToMainMenu = new JButton("Return To Main Menu");
        setFocusable(true);

        setLayout(null);
        returnToMainMenu.setBounds(0, 0, 170, 30);
        asd.setBounds(180, 350, 100, 30);


        returnToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                MainPanel mainPanel = new MainPanel(mainFrame);
                mainFrame.getContentPane().add(mainPanel);
                mainPanel.requestFocusInWindow();
                mainFrame.revalidate(); //refresh screen
            }
        });
        add(returnToMainMenu);
        add(asd);
    }
}
