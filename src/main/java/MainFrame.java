import javax.swing.*;


public class MainFrame extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;

    public MainFrame() {
        this.setTitle("2D Game");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null); // put the window into center
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //show MainPanel
        MainPanel mainPanel = new MainPanel(this);

        this.getContentPane().add(mainPanel);

        this.setVisible(true);
    }
}
