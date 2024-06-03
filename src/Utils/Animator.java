package Utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
    
    private ArrayList<Animation> _animations = new ArrayList<Animation>();
    private int _currentAnimationIndex = 0;
    private int _animationsCount = 0;

    public Animator(){}

    public int addAnimation(Animation anim){
        this._animations.add(anim);
        return this._animationsCount++;
    }

    public void updateCurrentAnimation(){
        this._animations.get(this._currentAnimationIndex).updateAnimation();
    }

    public void changeCurrentAnimation(int animationIndex){
        this._currentAnimationIndex = animationIndex;
    }

    public void drawCurrentAnimation(Graphics g, Position p){
        BufferedImage sprite = this._animations.get(this._currentAnimationIndex).getAnimationSprite();
        g.drawImage(sprite, p._x * Settings.MapSettings.MAP_TILE_WIDTH, p._y * Settings.MapSettings.MAP_TILE_WIDTH, Settings.ActorSettings.ACTOR_WIDTH, Settings.ActorSettings.ACTOR_WIDTH, null);
    }
}