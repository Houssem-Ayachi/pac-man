package Main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
