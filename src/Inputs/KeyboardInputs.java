package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_U:
                Inputs.UP = true;
            break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                Inputs.DOWN = true;
            break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Inputs.RIGHT = true;
            break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Inputs.LEFT = true;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_U:
                Inputs.UP = false;
            break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                Inputs.DOWN = false;
            break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Inputs.RIGHT = false;
            break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Inputs.LEFT = false;
            break;
        }
    }
    


}
