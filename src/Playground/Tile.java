package Playground;

import Utils.Position;

public class Tile {

    public Position _position;
    public boolean _isWalkable;
    public boolean _isPellet;
    public boolean _isEnergizer;
    public boolean _isTunnel;
    public boolean _isSpawn;

    public Tile(Position position, boolean isWalkable, boolean isPellet, boolean isEnergizer, boolean isTunnel, boolean isSpawn){
        this._position = position;
        this._isEnergizer = isEnergizer;
        this._isPellet = isPellet;
        this._isWalkable = isWalkable;
        this._isTunnel = isTunnel;
        this._isSpawn = isSpawn;
    }

}
