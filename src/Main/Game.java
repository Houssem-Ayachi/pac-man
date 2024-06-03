package Main;

import java.awt.Graphics;

import Entities.GameManager;

public class Game implements Runnable {
    
    private GamePanel _gamePanel;
    private Thread _gameThread;
    
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    
    private GameManager _gameManager;
    
    private boolean _isPlaying = true;

    public Game(){
        this._gameManager = new GameManager(this);
        this._gamePanel = new GamePanel(this);
        new GameWindow(this._gamePanel);
        this._gamePanel.requestFocusInWindow();
        this.startGameLoop();
    }

    public void stopGame(){
        this._isPlaying = false;
    }

    private void startGameLoop(){
        this._gameThread = new Thread(this);
        this._gameThread.start();
    }

    private void update(){
        this._gameManager.update();
    }

    public void render(Graphics g){
        this._gameManager.render(g);
    }

    @Override
    public void run(){
        double timePerFrame = 1000000000.0 / this.FPS_SET;
        double timePerUpdate = 1000000000.0 / this.UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        //main game loop
        while(true){
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if(deltaU >= 1 && this._isPlaying){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                this._gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck=System.currentTimeMillis();
                // System.out.println("FPS: "+ frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

}
