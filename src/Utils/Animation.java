package Utils;

import java.awt.image.BufferedImage;

public class Animation {
    
    private int _animationSpritesNumber;
    private int _tick = 0;
    private boolean _repeat;

    private long lastCheck = System.currentTimeMillis();

    private BufferedImage _animationSprites;

    public Animation(String animationPath, int animationTilesNumber, boolean repeat){
        this._animationSpritesNumber = animationTilesNumber;
        this._repeat = repeat;
        this.loadAnimationSprite(animationPath);
    }

    private void loadAnimationSprite(String animationPath){
        this._animationSprites = LoadSave.GetSpriteAtlas(animationPath);
    }

    public boolean updateAnimation(){
        long currentTime = System.currentTimeMillis();
        boolean animationEnded = false;
        if(currentTime - this.lastCheck >= 125){// 125 = time to refresh image
            animationEnded =this._tick == this._animationSpritesNumber-1;
            this._tick++;
            if(this._repeat && animationEnded){
                this._tick = 0;
            }
            lastCheck = currentTime;
        }
        return animationEnded;
    }

    public BufferedImage getAnimationSprite(){
        try{
            return this._animationSprites.getSubimage(this._tick * Settings.ActorSettings.ACTOR_SIZE, 0,Settings.ActorSettings.ACTOR_SIZE, Settings.ActorSettings.ACTOR_SIZE);
        }catch(Exception e){
        }
        return null;
    }
}
