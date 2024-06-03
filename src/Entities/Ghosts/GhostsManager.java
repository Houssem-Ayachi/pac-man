package Entities.Ghosts;

import java.awt.Graphics;
import java.util.ArrayList;

import Entities.Pac;
import Playground.Playground;
import Utils.Settings;

public class GhostsManager {
    
    public ArrayList<Blinky> _ghosts = new ArrayList<>();

    protected long lastCheck = System.currentTimeMillis();
    private GhostStates _ghostState = GhostStates.SCATTER;

    public GhostsManager(Playground pg, Pac pac){
        Blinky blinky = new Blinky(pg, pac, pg._spawns.get(0));
        blinky.loadAnimations(Settings.ActorSettings.BLINKY_RIGHT, Settings.ActorSettings.BLINKY_LEFT, Settings.ActorSettings.BLINKY_UP, Settings.ActorSettings.BLINKY_DOWN);

        Pinky pinky = new Pinky(pg, pac, pg._spawns.get(0));
        pinky.loadAnimations(Settings.ActorSettings.PINKY_RIGHT, Settings.ActorSettings.PINKY_LEFT, Settings.ActorSettings.PINKY_UP, Settings.ActorSettings.PINKY_DOWN);

        Inky inky = new Inky(pg, pac, pg._spawns.get(0), blinky);
        inky.loadAnimations(Settings.ActorSettings.INKY_RIGHT, Settings.ActorSettings.INKY_LEFT, Settings.ActorSettings.INKY_UP, Settings.ActorSettings.INKY_DOWN);

        Clyde clyde = new Clyde(pg, pac, pg._spawns.get(0));
        clyde.loadAnimations(Settings.ActorSettings.CLYDE_RIGHT, Settings.ActorSettings.CLYDE_LEFT, Settings.ActorSettings.CLYDE_UP, Settings.ActorSettings.CLYDE_DOWN);

        this._ghosts.add(blinky);
        this._ghosts.add(pinky);
        this._ghosts.add(inky);
        this._ghosts.add(clyde);
    }
    
    public GhostStates getGhostStates(){
        return this._ghostState;
    }

    //TODO: MAKE THIS TIME DEPENDING ON THE LEVEL
    private int scatterTime(){
        return 8000;
    }

    //TODO: MAKE THIS TIME DEPENDING ON THE LEVEL
    private int chaseTime(){
        return 8000;
    }
    
    public void frightenGhosts(){
        this.lastCheck = System.currentTimeMillis();
        for(Blinky ghost : this._ghosts){
            ghost.changeState(GhostStates.FRIGHTENED);
        }
    }

    public void update(){
        long now = System.currentTimeMillis();
    
        if(now - lastCheck >= this.scatterTime() && _ghostState == GhostStates.SCATTER){
            for(Blinky ghost : this._ghosts){
                ghost.changeState(GhostStates.CHASE);
            }
            lastCheck = now;
            this._ghostState = GhostStates.CHASE;
        }
        
        if(now - lastCheck >= this.chaseTime() && _ghostState == GhostStates.CHASE){
            for(Blinky ghost : this._ghosts){
                ghost.changeState(GhostStates.SCATTER);
            }
            lastCheck = now;
            this._ghostState = GhostStates.SCATTER;
        }

        for(Blinky ghost : this._ghosts){
            ghost.update();
        }
    }

    public void render(Graphics g){
        for(Blinky ghost : this._ghosts){
            ghost.render(g);
        }
    }

}