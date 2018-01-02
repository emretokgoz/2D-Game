
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GamePanel extends JPanel implements KeyListener {

    int BallX;
    int BallY;
    int diameter = 40;
    int move = 40;
    int topMenuHeight = 40;
    int score = 0;
    int firstTargetX;
    int firstTargetY;
    boolean isPaused = false;
    int secondTargetX;
    int secondTargetY;
    int secondTargetGenerate = 1;
    int outOfScreen = 800;

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

        BallX = rand.nextInt((MainFrame.WIDTH - diameter) / move - 1) * move;
        BallY = rand.nextInt((MainFrame.HEIGHT - diameter - topMenuHeight) / move - 1) * move + topMenuHeight;

        newTarget();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (SettingsPanel.x.equals("CYAN")) {
            g.setColor(Color.cyan);
        } else if (SettingsPanel.x.equals("MAGENTA")) {
            g.setColor(Color.magenta);
        } else if (SettingsPanel.x.equals("RED")) {
            g.setColor(Color.red);
        } else if (SettingsPanel.x.equals("YELLOW")) {
            g.setColor(Color.yellow);
        } else if (SettingsPanel.x.equals("BLUE")) {
            g.setColor(Color.blue);
        }

        setBackground(Color.white);
        g.fillOval(BallX, BallY, diameter, diameter);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.setColor(Color.GRAY);
        g.drawString("Score:", 200, 40);
        g.setColor(Color.CYAN);
        if (ScoreColorOrange()) {
            g.setColor(Color.ORANGE);
        }
        if (ScoreColorRed()) {
            g.setColor(Color.RED);
        }
        g.drawString(" " + score, 275, 40);
        g.setColor(Color.GREEN);
        g.fillOval(firstTargetX, firstTargetY, 40, 40);
        if (score == 5 * secondTargetGenerate) {
            g.setColor(Color.ORANGE);
            g.fillOval(secondTargetX, secondTargetY, 40, 40);
        }
        if (isPaused) {
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
            g.setColor(Color.RED);
            g.drawString("PAUSED", 80, 300);
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (BallX == 440) {
                    break;
                }
                if (!isPaused) {
                    BallX += move;
                }
                onMove();
                break;
            case KeyEvent.VK_LEFT:
                if (BallX == 0) {
                    break;
                }
                if (!isPaused) {
                    BallX -= move;
                }
                onMove();
                break;
            case KeyEvent.VK_UP:
                if (BallY == 40) {
                    break;
                }
                if (!isPaused) {
                    BallY -= move;
                }
                onMove();
                break;
            case KeyEvent.VK_DOWN:
                if (BallY == 600) {
                    break;
                }
                if (!isPaused) {
                    BallY += move;
                }
                onMove();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public boolean didEat() {
        if (BallX == firstTargetX && BallY == firstTargetY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean secondTargetEat() {
        if (BallX == secondTargetX && BallY == secondTargetY) {
            return true;
        } else {
            return false;
        }
    }

    public void onMove() {
        if (didEat()) {
            score++;
            if (score != 5 * secondTargetGenerate) {
                newTarget();
            } else {
                firstTargetX = 800;
                firstTargetY = 800;
            }
            secondTarget();
        }
        if (secondTargetEat()) {
            score += 2;
            secondTargetX = outOfScreen;
            secondTargetY = outOfScreen;
            secondTargetGenerate++;
            if (score != 5 * secondTargetGenerate) {
                newTarget();
            } else {
                firstTargetX = outOfScreen;
                firstTargetY = outOfScreen;
            }
        }
        repaint();
    }

    public void newTarget() {
        while (true) {
            firstTargetX = rand.nextInt((MainFrame.WIDTH - diameter) / move - 1) * move;
            firstTargetY = rand.nextInt((MainFrame.HEIGHT - diameter - topMenuHeight) / move - 1) * move + topMenuHeight;
            if (firstTargetX != BallX && firstTargetY != BallY) {
                break;
            }
        }
    }

    public void secondTarget() {
        while (true) {
            secondTargetX = rand.nextInt((MainFrame.WIDTH - diameter) / move - 1) * move;
            secondTargetY = rand.nextInt((MainFrame.HEIGHT - diameter - topMenuHeight) / move - 1) * move + topMenuHeight;
            if ((secondTargetX != BallX && secondTargetY != BallY) && (secondTargetX != firstTargetX && secondTargetY != firstTargetY)) {
                break;
            }
        }
    }

    public boolean ScoreColorOrange() {
        if ((score >= 5 && score <= 9) || (score >= 20 && score <= 24) || (score >= 35 && score <= 39)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ScoreColorRed() {
        if ((score >= 10 && score <= 14) || (score >= 25 && score <= 29) || (score >= 40 && score <= 44)) {
            return true;
        } else {
            return false;
        }
    }
}
