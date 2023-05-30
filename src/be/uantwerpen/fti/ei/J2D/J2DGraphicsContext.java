package be.uantwerpen.fti.ei.J2D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Class that draws and renders all graphical assets. */
public class J2DGraphicsContext {
    private final JFrame frame;
    private final JPanel panel;
    private final BufferedImage g2dImage;
    private final Graphics2D g2d;
    private final int scale;

    /**
     * Returns an object that handels all 2-dimensional shape rendering.
     * @return  a Graphics2D that handels all 2-dimensional shape rendering
     */
    public Graphics2D getG2d() { return g2d; }
    /**
     * Returns a frame in which all the game visualisation will be shown.
     * @return  a Frame in which the game will be visualised
     */
    public JFrame getFrame() { return frame; }
    /**
     * Returns a scaling factor compared to the base game logic
     * @return  an integer that represents the scaling factor
     */
    public int getScale() { return scale; }

    /**
     * Class constructor specifying the screen-width and -height, and the scaling factor.
     * @param   width an integer representing the screen-width
     * @param   height an integer representing the screen-height
     * @param   scale an integer representing the scaling factor compared to the base game logic
     */
    public J2DGraphicsContext(int width, int height, int scale) {
        this.scale = scale;

        frame = new JFrame();
        panel = new JPanel(true) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graph2d = (Graphics2D) g;
                Toolkit.getDefaultToolkit().sync();
                graph2d.drawImage(g2dImage, 0, 0, null);   // copy buffered image
                graph2d.dispose();
                if (g2d != null) g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
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

    /** Method to render all the game's graphical assets. */
    public void render() { panel.repaint(); }
    /** Method to dispose all the game's graphical assets. */
    public void close() { frame.dispose(); }

    /**
     * Method to initialise a title in the graphical assets.
     * {@link}  &nbsp;Stack overflow <a href="https://stackoverflow.com/a/32859632">Stack overflow</a>
     * @param   title a string representing a title
     */
    public void setTitle(String title) {
        g2d.setColor(new Color(224,224,224));
        g2d.setFont(new Font("title", Font.BOLD, getScale() * 3));
        g2d.drawString(title, (frame.getWidth() - g2d.getFontMetrics().stringWidth(title)) / 2, frame.getHeight() / 3);
    }

    /**
     * Method to initialise a line of text in the graphical assets.
     * {@link}  &nbsp;Stack overflow <a href="https://stackoverflow.com/a/32859632">Stack overflow</a>
     * @param   text a string representing a line of text
     * @param   x an integer representing the horizontal starting coordinate of the line of text
     * @param   y an integer representing the vertical starting coordinate of the line of text
     */
    public void setText(String text, int x, int y) {
        g2d.setColor(new Color(224,224,224));
        g2d.setFont(new Font("text", Font.PLAIN, 12));
        x = (x == 0 ? (frame.getWidth() - g2d.getFontMetrics().stringWidth(text)) / 2 : x);
        g2d.drawString(text, x, y);
    }
}
