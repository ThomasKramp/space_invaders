package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.enums.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class J2DInput extends AInput {
    public J2DInput(J2DGraphicsContext gr) {
        gr.getFrame().addKeyListener(new KeyInputAdapter());
    }

    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT   -> keyInputs.add(InputType.LEFT);
                case KeyEvent.VK_RIGHT  -> keyInputs.add(InputType.RIGHT);
                case KeyEvent.VK_DOWN   -> keyInputs.add(InputType.DOWN);
                case KeyEvent.VK_UP     -> keyInputs.add(InputType.UP);
                case KeyEvent.VK_SPACE  -> keyInputs.add(InputType.SPACE);
                case KeyEvent.VK_ESCAPE -> keyInputs.add(InputType.ESCAPE);
                case KeyEvent.VK_ENTER  -> keyInputs.add(InputType.ENTER);
            }
        }
    }
}
