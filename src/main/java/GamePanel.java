import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener {

    int x = 0;
    int y = 0;
    int diameter = 40;

    public GamePanel(MainFrame mainFrame) {
        this.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        this.setFocusable(true);
        this.setVisible(true);
        this.addKeyListener(this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.cyan);
        setBackground(Color.white);
        g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (x == 450) {
                    break;
                }
                x += 10;
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                if (x == 0) {
                    break;
                }
                x -= 10;
                repaint();
                break;
            case KeyEvent.VK_UP:
                if (y == 0) {
                    break;
                }
                y -= 10;
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                if (y == 630) {
                    break;
                }
                y += 10;
                repaint();
                break;

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}