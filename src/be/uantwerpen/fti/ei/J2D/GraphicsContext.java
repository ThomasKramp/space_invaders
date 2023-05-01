package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.interfaces.IHotBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsContext implements IHotBar {
    private final JFrame frame;
    private final JPanel panel;
    private final BufferedImage g2dImage;
    private final Graphics2D g2d;
    private final int scale;

    public Graphics2D getG2d() {
        return g2d;
    }
    public JFrame getFrame() {
        return frame;
    }
    public int getScale() { return scale; }

    public GraphicsContext(int width, int height, int scale) {
        this.scale = scale;

        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };

        frame.setFocusable(true);
        frame.add(panel).setBounds(0, 0, width * getScale(), height * getScale());
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width * getScale(), height * getScale() + 32);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        g2dImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dImage.createGraphics();
        g2d.setBackground(new Color(0,0,0));
        g2d.clearRect(0,0, frame.getWidth(), frame.getHeight());
    }

    public void render() { panel.repaint(); }
    public void close() { frame.dispose(); }

    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dImage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null) g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void updateScore(int score) {
        g2d.drawString("Score: " + score, 0, frame.getHeight() - 64);
    }
    public void updateHealth(int health) {
        g2d.drawString("Health: " + health, 64, frame.getHeight() - 64);
    }

}
