package Entities;
import java.awt.Graphics;
import Entities.Ghosts.Blinky;
import Entities.Ghosts.GhostStates;
import Entities.Ghosts.GhostsManager;
import Main.Game;
import Playground.Playground;
import Utils.Position;

public class GameManager {
    public GhostsManager _ghostsManager;

    public int _level = 1;
    public int _modeDuration = 7; // used to set each mode's (scatter / chase) duration;
    public int _pelletsNumber = 0;
    public int _pelletsEaten = 0;

    private Pac pac;
    private Game _game;
    private Playground _playground;
    private UI _ui;

    public GameManager(Game game){
        this._game = game;
        this._playground = new Playground();
        this._pelletsNumber = this._playground._pellets.size();
        Position pacPosition = this._playground._spawns.get(1);
        this.pac = new Pac(new Position(pacPosition._x, pacPosition._y), this._playground, this);
        this._ghostsManager = new GhostsManager(this._playground, this.pac);
        this._ui = new UI();
    }

    private void checkCollisions(){
        for(Blinky ghost : this._ghostsManager._ghosts){
            if(ghost._position._x == this.pac._position._x && ghost._position._y == this.pac._position._y){
                if(ghost._state == GhostStates.FRIGHTENED){
                    ghost.changeState(GhostStates.EATEN);
                }else if(ghost._state == GhostStates.CHASE || ghost._state == GhostStates.SCATTER){
                    this._game.stopGame();
                }
            }
        }
    }

    //TODO: FINISH LATER
    public void pelletEaten(){
        this._pelletsEaten++;
        this._ui.setScore(this._pelletsEaten);
        if(this._pelletsEaten == this._pelletsNumber){
            this._level+=1;
            this._ui.setLevel(this._level);
            System.out.println("YOU WON!!");
        }
    }

    public void energizerEaten(){
        this._ghostsManager.frightenGhosts();
    }

    public void frightenGhosts(){
        this._ghostsManager.frightenGhosts();
    }

    public Position getPacPosition(){
        return this.pac._position.getTilePositon();
    }

    public void update(){
        this.checkCollisions();
        this._ghostsManager.update();
        this.pac.update();
    }

    public void render(Graphics g){
        this._playground.render(g);
        this._ghostsManager.render(g);
        this.pac.render(g);
        this._ui.render(g);
    }
}
