import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPanel extends JPanel {

    public static String ballColor = "CYAN";

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
        ColorList.setBounds(155, 150, 175, 40);

        ColorList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ballColor = String.valueOf(ColorList.getSelectedItem());
                if (ballColor.equals("CYAN")) {
                    ColorList.setForeground(Color.CYAN);
                } else if (ballColor.equals("MAGENTA")) {
                    ColorList.setForeground(Color.MAGENTA);
                } else if (ballColor.equals("RED")) {
                    ColorList.setForeground(Color.RED);
                } else if (ballColor.equals("YELLOW")) {
                    ColorList.setForeground(Color.YELLOW);
                } else if (ballColor.equals("BLUE")) {
                    ColorList.setForeground(Color.BLUE);
                }
            }
        });
        ColorList.setForeground(Color.CYAN);
        add(ColorList);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (ballColor.equals("CYAN")) {
            g.setColor(Color.cyan);
        } else if (ballColor.equals("MAGENTA")) {
            g.setColor(Color.magenta);
        } else if (ballColor.equals("RED")) {
            g.setColor(Color.red);
        } else if (ballColor.equals("YELLOW")) {
            g.setColor(Color.yellow);
        } else if (ballColor.equals("BLUE")) {
            g.setColor(Color.blue);
        }

        g.fillOval(80, 150, 40, 40);
        g.fillOval(80, 210, 40, 40);
        g.fillOval(80, 270, 40, 40);
        g.fillOval(370, 150, 40, 40);
        g.fillOval(370, 210, 40, 40);
        g.fillOval(370, 270, 40, 40);
        repaint();
    }
}
