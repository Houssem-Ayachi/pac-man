package Entities.Ghosts;

import java.awt.Graphics;

import Entities.Pac;
import Playground.Playground;
import Playground.Tile;
import Utils.Position;
import Utils.Settings;

public class Clyde extends Blinky {

    private Position _scatterTarget = new Position(0, Settings.MapSettings.MAP_VERTICAL_TILES);

    Clyde(Playground pg, Pac pac, Position basePosition){
        super(pg, pac, basePosition);
    }

    protected void goToNextTile(){
        switch(this._state){
            case SCATTER:
                this.move(_scatterTarget, true);
            break;
            case CHASE:
                this.move(this.getTargetTile(), true);
            break;
            case EATEN:
                this.move(this._playground._spawns.get(0), true);
            break;
            case FRIGHTENED:
                this.moveIfFrightened();
            break;
        }
        //check if ghost is in a tunnel (needs to teleport to the other side)
        Tile currentTile = this._playground._playground[this._position._y][this._position._x];
        if(currentTile._isTunnel){
            if(this._playground._tunnels.get(0)._x == this._position._x && this._hDirection == -1){//left tunnel
                this._position._x = this._playground._tunnels.get(1)._x;
            }else if(this._playground._tunnels.get(1)._x == this._position._x && this._hDirection == 1){// right tunnel
                this._position._x = this._playground._tunnels.get(0)._x;
            }
        }
    }

    private Position getTargetTile(){
        Position difference = new Position(
            this._position._x - this._pac._position._x,
            this._position._y - this._pac._position._y
        );
        int distanceFromPac = (int)Math.sqrt(Math.pow(Math.abs(difference._x), 2) + Math.pow(Math.abs(difference._y), 2));
        if(distanceFromPac >= 8){
            return this._pac._position;
        }
        return _scatterTarget;
    }

    public void update(){
        long now = System.currentTimeMillis();
        if(now - this.lastCheck >= 170){
            this.goToNextTile();
            this.updateSprite();
            this.lastCheck = now;
        }
    }

    public void render(Graphics g){
        this._animator.drawCurrentAnimation(g, this._position);
    }
}
