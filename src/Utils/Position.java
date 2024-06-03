package Utils;

import java.util.ArrayList;

public class Position {
    
    public int _x;
    public int _y;

    public Position(int x, int y){
        this._x = x;
        this._y = y;
    }

    public Position getTilePositon(){
        Position newPosition = new Position(
            (int)(this._x / Settings.MapSettings.MAP_TILE_WIDTH),
            (int)(this._y / Settings.MapSettings.MAP_TILE_WIDTH)
        );
        return newPosition;
    }

    public float getDistance(Position other){
        int diffX = this._x - other._x;
        int diffY = this._y - other._y;
        return (float)Math.sqrt((diffX* diffX) + (diffY * diffY));
    }

    //TODO: SHOULD THINK OF A WAY TO MAKE IT BETTER
    public static float minNeighborDistance(Position base,ArrayList<Position> neighbours, Position target, boolean noGoBack){
        float minDistance = -1;
        for(Position pos : neighbours){
            if(noGoBack && pos._x == base._x && pos._y == base._y){
                continue;
            }
            float distance = pos.getDistance(target);
            if(minDistance == -1){
                minDistance = distance;
            }else if(distance < minDistance){
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
