package Entities.Ghosts;

import java.awt.Graphics;

import Entities.Pac;
import Playground.Playground;
import Playground.Tile;
import Utils.Position;
import Utils.Settings;

public class Inky extends Blinky {
    Blinky blinky;

    private Position _scatterTarget = new Position(Settings.MapSettings.MAP_HORIZONTAL_TILES, Settings.MapSettings.MAP_VERTICAL_TILES);

    Inky(Playground pg, Pac pac, Position basePosition, Blinky blinky){
        super(pg, pac, basePosition);
        this.blinky = blinky;
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
        Position offset = new Position(
            this._pac._horizontalDirection * 2 + this._pac._position._x,
            this._pac._verticalDirection * 2 + this._pac._position._y
        );

        Position difference = new Position(
            -(offset._x - this.blinky._position._x),
            -(offset._y - this.blinky._position._y)
        );

        return new Position(
            offset._x - difference._x,
            offset._y - difference._y
        );
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
