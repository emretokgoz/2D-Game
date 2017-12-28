import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GamePanel extends JPanel implements KeyListener {

    int x = 0;
    int y = 40;
    int diameter = 40;
    int move = 40;
    int topMenuHeight = 40;
    int score = 0;
    int a;
    int b;
    int c;
    int v;
    boolean isPaused = false;

    Random rand = new Random();

    public GamePanel(MainFrame mainFrame) {
        this.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
        this.addKeyListener(this);
        JButton returnToMainMenu = new JButton("Main Menu");
        setFocusable(true);

        setLayout(null);

        returnToMainMenu.setBounds(0, 0, 150, topMenuHeight);

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

        JButton pauseOrPlay = new JButton("PAUSE");

        pauseOrPlay.setBounds(340, 0, 150, topMenuHeight);
        pauseOrPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPaused) {
                    isPaused = true;
                    pauseOrPlay.setText("PLAY");
                    
                } else {
                    isPaused = false;
                    pauseOrPlay.setText("PAUSE");
                }
                requestFocus();
            }
        });
        add(returnToMainMenu);
        add(pauseOrPlay);

        x = rand.nextInt((MainFrame.WIDTH - diameter) / move - 1) * move;
        y = rand.nextInt((MainFrame.HEIGHT - diameter - topMenuHeight) / move - 1) * move + topMenuHeight;

        newTarget();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.cyan);
        setBackground(Color.white);
        g.fillOval(x, y, diameter, diameter);
        Font myFont = new Font("Courier New", Font.BOLD, 20);
        g.setFont(myFont);

        g.setColor(Color.GRAY);
        g.drawString("Score: " + score, 200, 40);

        g.setColor(Color.GREEN);
        g.fillOval(c, v, 40, 40);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (x == 440) {
                    break;
                }
                if (!isPaused) {
                    x += 40;
                }
                onMove();
                break;
            case KeyEvent.VK_LEFT:
                if (x == 0) {
                    break;
                }
                if (!isPaused) {
                    x -= 40;
                }
                onMove();
                break;
            case KeyEvent.VK_UP:
                if (y == 40) {
                    break;
                }
                if (!isPaused) {
                    y -= 40;
                }
                onMove();
                break;
            case KeyEvent.VK_DOWN:
                if (y == 600) {
                    break;
                }
                if (!isPaused) {
                    y += 40;
                }
                onMove();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean didEat() {
        if (x == c && y == v) {
            return true;
        } else {
            return false;
        }
    }

    public void onMove() {
        if (didEat()) {
            score++;
            newTarget();
        }
        repaint();
    }

    public void newTarget() {
        //int[] a = new int[]{40, 80, 120, 160, 200, 240, 280, 320, 360, 400};
        //int[] b = new int[]{40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560};

        //c = (a[rand.nextInt(a.length)]);
        //v = (b[rand.nextInt(b.length)]);

        while (true) {
            c = rand.nextInt((MainFrame.WIDTH - diameter) / move - 1) * move;
            v = rand.nextInt((MainFrame.HEIGHT - diameter - topMenuHeight) / move - 1) * move + topMenuHeight;
            if (c != x && v != y) {
                break;
            }
        }
    }
}
