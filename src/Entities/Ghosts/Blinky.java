package Entities.Ghosts;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

import Entities.Pac;
import Playground.Playground;
import Playground.Tile;
import Utils.Animation;
import Utils.Animator;
import Utils.Position;
import Utils.Settings;

public class Blinky {
    
    public Position _position;

    protected Animator _animator;
    protected Playground _playground;
    protected Pac _pac;
    public GhostStates _state = GhostStates.SCATTER;

    private Position _scatterTarget = new Position(Settings.MapSettings.MAP_VERTICAL_TILES, 0);

    protected long lastCheck = System.currentTimeMillis();

    protected short _hDirection = 0; // right = 1 || left = -1
    protected short _vDirection = 0; // up = -1 || down = 1;

    protected int _ghostRight;
    protected int _ghostLeft;
    protected int _ghostUp;
    protected int _ghostDown;

    protected int _deadRight;
    protected int _deadLeft;
    protected int _deadUp;
    protected int _deadDown;

    protected int _frightened;
    // used to change the ghosts direction to the opposite once when entering frightened mode
    // naming could be better hehe
    protected boolean _justEnteredFrightenedState = false;

    public Blinky(Playground pg, Pac pac, Position basePosition){
        this._position = basePosition;
        this._animator = new Animator();
        this._playground = pg;
        this._pac = pac;
    }

    public void loadAnimations(String rightPath, String leftPath, String upPath, String downPath){
        this._frightened = this._animator.addAnimation(new Animation(Settings.ActorSettings.SCARED_GHOST_PATH, 1, false));

        this._ghostRight = this._animator.addAnimation(new Animation(rightPath, Settings.ActorSettings.GHOST_SPRITE_NUMBER, false));
        this._ghostLeft = this._animator.addAnimation(new Animation(leftPath, Settings.ActorSettings.GHOST_SPRITE_NUMBER, false));
        this._ghostUp = this._animator.addAnimation(new Animation(upPath, Settings.ActorSettings.GHOST_SPRITE_NUMBER, false));
        this._ghostDown = this._animator.addAnimation(new Animation(downPath, Settings.ActorSettings.GHOST_SPRITE_NUMBER, false));

        this._deadRight = this._animator.addAnimation(new Animation(Settings.ActorSettings.DEAD_RIGHT, _deadDown, false));
        this._deadLeft = this._animator.addAnimation(new Animation(Settings.ActorSettings.DEAD_LEFT, _deadDown, false));
        this._deadUp = this._animator.addAnimation(new Animation(Settings.ActorSettings.DEAD_UP, _deadDown, false));
        this._deadDown = this._animator.addAnimation(new Animation(Settings.ActorSettings.DEAD_DOWN, _deadDown, false));
    }

    protected void goToNextTile(){
        switch(this._state){
            case SCATTER:
                this.move(this._scatterTarget, true);
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

    //TODO: target tile should change depending on which state the ghost is in
    private Position getTargetTile(){
        return this._pac._position;
    }

    protected void move(Position target, boolean noGoBack){
        short hDirection = this._hDirection;
        short vDirection = this._vDirection;
        ArrayList<Position> neighbours = this._playground.getNeighbouringTiles(this._position);
        float minDistance = -1;
        Position nextTile = this._position;
        for(Position pos : neighbours){
            if(noGoBack){
                hDirection = (short)(pos._x - this._position._x);
                vDirection = (short)(pos._y - this._position._y);
                if(hDirection != this._hDirection && hDirection == -this._hDirection || vDirection!=this._vDirection && vDirection == -this._vDirection){
                    continue;
                }
            }
            float distance = Position.minNeighborDistance(this._position,this._playground.getNeighbouringTiles(pos), target, true);
            if(minDistance == -1){
                minDistance = distance;
                nextTile = pos;
            }else if(distance < minDistance){
                minDistance = distance;
                nextTile = pos;
            }
        }
        this.updatePosition(nextTile);
    }

    protected void moveIfFrightened(){
        if(!this._justEnteredFrightenedState){
            this._justEnteredFrightenedState = true;
            this._hDirection *= -1;
            this._vDirection *= -1;
        }
        ArrayList<Position> neighbours = this._playground.getNeighbouringTiles(this._position);
        ArrayList<Position> validNeighbours = new ArrayList<Position>();
        int hDirection = this._hDirection;
        int vDirection = this._vDirection;
        //remove last position from neighbors
        for(Position pos : neighbours){
            hDirection = pos._x - this._position._x;
            vDirection =  pos._y - this._position._y;
            if(hDirection != this._hDirection && hDirection == -this._hDirection || vDirection!=this._vDirection && vDirection == -this._vDirection){
                continue;
            }
            validNeighbours.add(pos);
        }
        Random rand = new Random();
        int chosenNeighborIDX = rand.nextInt(validNeighbours.size());
        Position nextTile = validNeighbours.get(chosenNeighborIDX);
        this.updatePosition(nextTile);
    }

    private void updatePosition(Position nextTile){
        this._hDirection = (short)(nextTile._x - this._position._x);
        this._vDirection = (short)(nextTile._y - this._position._y);
        this._position = nextTile;
        if(this._state == GhostStates.EATEN && this._position._x == this._playground._spawns.get(0)._x && this._position._y == this._playground._spawns.get(0)._y){
            this._state = GhostStates.CHASE;
        }
    }

    public void changeState(GhostStates state){
        if(this._state == GhostStates.EATEN) return;
        if(this._state != GhostStates.FRIGHTENED || this._state != GhostStates.EATEN){
            this._state = state;
            this._justEnteredFrightenedState = false;
        }
        if(this._state == GhostStates.FRIGHTENED){
            this._animator.changeCurrentAnimation(this._frightened);
        }
    }

    protected void updateSprite(){
        if(this._state == GhostStates.FRIGHTENED) return;
        if(this._state == GhostStates.EATEN){
            if(this._hDirection == 1){
                this._animator.changeCurrentAnimation(this._deadRight);
            }
            if(this._hDirection == -1){
                this._animator.changeCurrentAnimation(this._deadLeft);
            }
            if(this._vDirection == -1){
                this._animator.changeCurrentAnimation(this._deadUp);
            }
            if(this._vDirection == 1){
                this._animator.changeCurrentAnimation(this._deadDown);
            }
        }else{
            if(this._hDirection == 1){
                this._animator.changeCurrentAnimation(this._ghostRight);
            }
            if(this._hDirection == -1){
                this._animator.changeCurrentAnimation(this._ghostLeft);
            }
            if(this._vDirection == -1){
                this._animator.changeCurrentAnimation(this._ghostUp);
            }
            if(this._vDirection == 1){
                this._animator.changeCurrentAnimation(this._ghostDown);
            }
        }
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
