package Playground;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Utils.Position;
import Utils.Settings;

public class Playground extends PlaygroundManager {
    
    private ArrayList<Position> _walkableTiles;
    
    public ArrayList<Position> _energizers;
    public ArrayList<Position> _pellets;
    public ArrayList<Position> _tunnels;
    public ArrayList<Position> _spawns;
    public Tile[][] _playground;

    public Playground(){
        super();
        this.initPositions();
        this.fillPlayground();
    }

    private boolean positionInsidePlayground(int x, int y){
        return x >= 0 && x < Settings.MapSettings.MAP_HORIZONTAL_TILES
        && y >= 0 && y < Settings.MapSettings.MAP_VERTICAL_TILES;
    }

    public ArrayList<Position> getNeighbouringTiles(Position base){
        ArrayList<Position> neighbours = new ArrayList<>();
        if(this.positionInsidePlayground(base._x, base._y - 1) && this._playground[base._y - 1][base._x] != null)
            neighbours.add(new Position(base._x, base._y - 1));

        if(this.positionInsidePlayground(base._x + 1, base._y) && this._playground[base._y][base._x + 1] != null)
            neighbours.add(new Position(base._x + 1, base._y));

        if(this.positionInsidePlayground(base._x, base._y + 1) && this._playground[base._y + 1][base._x] != null)
            neighbours.add(new Position(base._x, base._y + 1));

        if(this.positionInsidePlayground(base._x - 1, base._y) && this._playground[base._y][base._x - 1] != null)
            neighbours.add(new Position(base._x - 1, base._y));

        return neighbours;
    }

    private void initPositions(){
        this._walkableTiles = this.loadWalkablePathPositions();
        this._energizers = this.loadEnergizersPositions();
        this._spawns = this.loadSpawnPositions();
        this._tunnels = this.loadTunnelsPositions();
        this._pellets = this.loadPellets();
    }

    private void fillPlayground(){
        this._playground = new Tile[Settings.MapSettings.MAP_VERTICAL_TILES][Settings.MapSettings.MAP_HORIZONTAL_TILES];

        for(Position p: this._walkableTiles){
            this._playground[p._y][p._x] = new Tile(p, true, false, false, false, false);
        }

        for(Position p: this._pellets){
            this._playground[p._y][p._x]._isPellet = true;
        }

        for(Position p: this._energizers){
            this._playground[p._y][p._x]._isEnergizer = true;
            this._playground[p._y][p._x]._isPellet = false;
        }

        for(Position p: this._spawns){
            this._playground[p._y][p._x]._isSpawn = true;
        }

        for(Position p: this._tunnels){
            this._playground[p._y][p._x]._isTunnel = true;
        }
    }

    public void render(Graphics g){
        this.drawBaseMap(g);
        for(Tile[] t: this._playground){
            for(Tile tile: t){
                if(tile != null){
                    if(tile._isPellet){
                        g.setColor(new Color(255,239,213));
                        g.fillRect(tile._position._x * (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE) + 4, tile._position._y * (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE) + 4, (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE) - 10, (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE) - 10);
                    }else if(tile._isEnergizer){
                        g.setColor(Color.PINK);
                        g.fillRect(tile._position._x * (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE), tile._position._y * (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE), (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE), (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE));
                    }
                }
            }
        }
    }
}

