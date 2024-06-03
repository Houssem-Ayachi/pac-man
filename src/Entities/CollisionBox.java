package Entities;

import java.awt.Color;
import java.awt.Graphics;

import Utils.Position;
import Utils.Settings;

public class CollisionBox {
    
    public Position _position;

    public CollisionBox(Position position){
        this._position = new Position(
            position._x / Settings.MapSettings.MAP_TILE_WIDTH, 
            position._y / Settings.MapSettings.MAP_TILE_WIDTH
        );
    }

    public void updateTilePosition(Position hodlerPosition){
        int holderCenterOffset = (int)(Settings.ActorSettings.ACTOR_WIDTH / 2);
        Position holderCenter = new Position(
            hodlerPosition._x + holderCenterOffset,
            hodlerPosition._y + holderCenterOffset
        );
        Position newPosition = new Position(
            (int)(holderCenter._x / Settings.MapSettings.MAP_TILE_WIDTH),
            (int)(holderCenter._y / Settings.MapSettings.MAP_TILE_WIDTH)
        );
        this._position = newPosition;
    }

    public void drawBox(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(this._position._x * Settings.MapSettings.MAP_TILE_WIDTH, this._position._y * Settings.MapSettings.MAP_TILE_WIDTH, Settings.MapSettings.MAP_TILE_WIDTH, Settings.MapSettings.MAP_TILE_WIDTH);
    }
}
