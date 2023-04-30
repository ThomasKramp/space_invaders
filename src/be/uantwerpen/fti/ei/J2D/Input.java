package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.input.Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends AInput {
    public Input(GraphicsContext gr) {
        gr.getFrame().addKeyListener(new KeyInputAdapter());
    }

    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT   -> keyInputs.add(Inputs.LEFT);
                case KeyEvent.VK_RIGHT  -> keyInputs.add(Inputs.RIGHT);
                case KeyEvent.VK_DOWN   -> keyInputs.add(Inputs.DOWN);
                case KeyEvent.VK_UP     -> keyInputs.add(Inputs.UP);
                case KeyEvent.VK_SPACE  -> keyInputs.add(Inputs.SPACE);
                case KeyEvent.VK_ESCAPE -> keyInputs.add(Inputs.ESCAPE);
            }
        }
    }
}
