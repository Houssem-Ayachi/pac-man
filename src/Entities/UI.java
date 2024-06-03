package Entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Utils.Settings;

public class UI {
    private int _score = 0;
    private int _level = 1;

    private int _positionY = (int)((Settings.MapSettings.MAP_HEIGHT+ 10) * Settings.TILE_SCALE);
    private int _positionX = 0;

    public void setScore(int score){
        _score = score;
    }

    public void setLevel(int level){
        _level = level;
    }

    public void render(Graphics g){
        char[] levelChars = ("level: " + _level).toCharArray();
        char[] scoreChars = ("score: " + _score).toCharArray();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.black);
        g.drawChars(levelChars, 0, levelChars.length, _positionX, _positionY);
        g.drawChars(scoreChars, 0, scoreChars.length, _positionX+100, _positionY);
    }
}
