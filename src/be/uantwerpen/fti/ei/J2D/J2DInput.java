package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.input.AInput;
import be.uantwerpen.fti.ei.enums.InputType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** Input class that uses keyboard events as inputs */
public class J2DInput extends AInput {

    /**
     * Class constructor specifying the width and height of the screen.
     * @param   gr an integer representing the width of the screen
     */
    public J2DInput(J2DGraphicsContext gr) { gr.getFrame().addKeyListener(new KeyInputAdapter()); }

    /** Keyboard adapter class that interrupts when a key is pressed */
    class KeyInputAdapter extends KeyAdapter {
        /**
         * Methods that translates a keyboard keypress to an input type.
         * @param   e a keyboard event that represents the pressed key
         * @see     InputType
         */
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
