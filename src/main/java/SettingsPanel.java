import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPanel extends JPanel {
    public static String x = "CYAN";

    public SettingsPanel(MainFrame mainFrame) {
        setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        JButton returnToMainMenu = new JButton("Return To Main Menu");
        setFocusable(true);
        setLayout(null);
        returnToMainMenu.setBounds(0, 0, 170, 30);
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

        String[] ColorStrings = {"CYAN", "MAGENTA", "RED", "YELLOW", "BLUE"};
        JComboBox ColorList = new JComboBox(ColorStrings);
        ColorList.setBounds(160, 150, 170, 30);

        ColorList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                x = String.valueOf(ColorList.getSelectedItem());
            }
        });
        add(ColorList);

    }
}
