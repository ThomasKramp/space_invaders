package be.uantwerpen.fti.ei.J2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsContext {
    private JFrame frame;
    private JPanel panel;
    private BufferedImage g2dimage;     // used for drawing
    private Graphics2D g2d;             // always draw in this one

    public Graphics2D getG2d() {
        return g2d;
    }
    public JFrame getFrame() {
        return frame;
    }

    public GraphicsContext(int width, int height) {

        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };

        frame.setFocusable(true);
        frame.add(panel).setBounds(0, 0, width, height);
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        g2dimage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
        g2d.setBackground(new Color(255,255,255));
        g2d.clearRect(0,0, frame.getWidth(), frame.getHeight());
    }

    public void render() { panel.repaint(); }
    public void close() { frame.dispose();; }

    private void doDrawing(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (g2d != null) g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }
}
