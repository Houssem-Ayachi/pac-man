package Main;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;

import java.awt.Dimension;

import Utils.Settings;

public class GamePanel extends JPanel {
    
    private Game _game;

    public GamePanel(Game game){
        super();
        this.addKeyListener(new KeyboardInputs());
        this._game = game;
        this.setPanelSize();
    }

    protected void setPanelSize(){
        Dimension size = new Dimension((int) (Settings.MapSettings.MAP_WIDTH * Settings.TILE_SCALE),(int) ((Settings.MapSettings.MAP_HEIGHT + Settings.UISettings.UI_BOX_HEIGHT) * Settings.TILE_SCALE));
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        this._game.render(g);
    }

}
