package Entities;

import java.awt.Graphics;

import Inputs.Inputs;
import Playground.Playground;
import Playground.Tile;
import Utils.Animation;
import Utils.Animator;
import Utils.Position;
import Utils.Settings;

public class Pac {
    public Position _position;
    private Animator _animator;
    private Playground _pg;
    private GameManager _gameManager;

    private int _pacDeadIndex;
    private int _pacDownIndex;
    private int _pacUpIndex;
    private int _pacRightIndex;
    private int _pacLeftIndex;

    private int _currentAnimationIndex;

    public int _horizontalDirection = 1;
    public int _verticalDirection = 0;

    private long _lastCheck = System.currentTimeMillis();

    public Pac(Position p, Playground pg, GameManager gm){
        this._gameManager = gm;
        this._pg = pg;
        this._position = p;
        this._animator = new Animator();
        this.loadAnimations();
        this._animator.changeCurrentAnimation(this._pacRightIndex);
    }

    private void loadAnimations(){
        this._pacDeadIndex = this._animator.addAnimation(new Animation(Settings.ActorSettings.PAC_DEAD_PATH, Settings.ActorSettings.PAC_DEAD_TILES_NUMBER, false));
        this._pacDownIndex = this._animator.addAnimation(new Animation(Settings.ActorSettings.PAC_DOWN_PATH, Settings.ActorSettings.PAC_DOWN_TILES_NUMBER, true));
        this._pacRightIndex = this._animator.addAnimation(new Animation(Settings.ActorSettings.PAC_RIGHT_PATH, Settings.ActorSettings.PAC_SIDE_TILES_NUMBER, true));
        this._pacLeftIndex = this._animator.addAnimation(new Animation(Settings.ActorSettings.PAC_LEFT_PATH, Settings.ActorSettings.PAC_SIDE_TILES_NUMBER, true));
        this._pacUpIndex = this._animator.addAnimation(new Animation(Settings.ActorSettings.PAC_UP_PATH, Settings.ActorSettings.PAC_SIDE_TILES_NUMBER, true));
        this._currentAnimationIndex = this._pacRightIndex;
    }

    private void updatePos(){
        long now = System.currentTimeMillis();
        if(Inputs.LEFT && canMove(-1, 0)){
            this._currentAnimationIndex = this._pacLeftIndex;
            this._horizontalDirection = -1;
            this._verticalDirection = 0;
        }
        if(Inputs.RIGHT && canMove(1, 0)){
            this._currentAnimationIndex = this._pacRightIndex;
            this._horizontalDirection = 1;
            this._verticalDirection = 0;
        }
        if(Inputs.DOWN && canMove(0, 1)){
            this._currentAnimationIndex = this._pacDownIndex;
            this._verticalDirection = 1;
            this._horizontalDirection = 0;
        }
        if(Inputs.UP && canMove(0, -1)){
            this._currentAnimationIndex = this._pacUpIndex;
            this._verticalDirection = -1;
            this._horizontalDirection = 0;
        }
        if(now - this._lastCheck >= 150 && canMove(_horizontalDirection, _verticalDirection)){            
            this._lastCheck = now;
            this._animator.changeCurrentAnimation(this._currentAnimationIndex);
            this._position._x += this._horizontalDirection;
            this._position._y += this._verticalDirection;
        }
    }

    private boolean canMove(int hDirection, int vDirection){
        try{
            Tile nextTile = this._pg._playground[this._position._y + vDirection][this._position._x + hDirection];
            if(nextTile == null){
                return false;
            }
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    private void handleTileAction(){
        Tile pacTile = this._pg._playground[this._position._y][this._position._x];
        if(pacTile._isPellet){
            pacTile._isPellet = false;
            this._gameManager.pelletEaten();
        }
        if(pacTile._isEnergizer){
            pacTile._isEnergizer = false;
            this._gameManager.pelletEaten();
            this._gameManager.frightenGhosts();
        }
        if(pacTile._isTunnel){
            pacTile._isPellet = false;
            if(this._pg._tunnels.get(0)._x == this._position._x && this._horizontalDirection == -1){//left tunnel
                this._position._x = this._pg._tunnels.get(1)._x;
            }else if(this._pg._tunnels.get(1)._x == this._position._x && this._horizontalDirection == 1){// right tunnel
                this._position._x = this._pg._tunnels.get(0)._x;
            }
        }
    }

    public void update(){
        this._animator.updateCurrentAnimation();
        handleTileAction();
        this.updatePos();
    }

    public void render(Graphics g){
        this._animator.drawCurrentAnimation(g, this._position);
    }
}