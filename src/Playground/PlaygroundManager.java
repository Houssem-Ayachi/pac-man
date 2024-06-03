package Playground;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Utils.LoadSave;
import Utils.Position;
import Utils.Settings;

public class PlaygroundManager {
    
    private BufferedImage[] _baseMap;

    public PlaygroundManager(){
        this.loadBaseMap();
    }

    public void loadBaseMap(){
        BufferedImage img = LoadSave.GetSpriteAtlas(Settings.MapSettings.BASE_MAP_PATH);
        this._baseMap = new BufferedImage[Settings.MapSettings.MAP_TILES_NUMBER];
        for(int j=0;j<Settings.MapSettings.MAP_VERTICAL_TILES;j++){
            for(int i=0;i<Settings.MapSettings.MAP_HORIZONTAL_TILES;i++){
                int index = j * Settings.MapSettings.MAP_HORIZONTAL_TILES + i;
                this._baseMap[index] = img.getSubimage(i*Settings.MapSettings.MAP_TILE_SIZE, j*Settings.MapSettings.MAP_TILE_SIZE, Settings.MapSettings.MAP_TILE_SIZE, Settings.MapSettings.MAP_TILE_SIZE);
            }
        }
    }

    private ArrayList<Position> loadSpecialTilesAsPositions(String path){
        ArrayList<Position> positions = new ArrayList<>();
        BufferedImage sprite = LoadSave.GetSpriteAtlas(path);
        for(int j=0;j<Settings.MapSettings.MAP_VERTICAL_TILES;j++){
            for(int i=0;i<Settings.MapSettings.MAP_HORIZONTAL_TILES;i++){
                Color color = new Color(sprite.getRGB(i*Settings.MapSettings.MAP_TILE_SIZE, j*Settings.MapSettings.MAP_TILE_SIZE));
                //only white tiles are walkable
                if(color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255){
                    positions.add(new Position(i, j));
                }
            }
        }
        return positions;
    }

    public ArrayList<Position> loadWalkablePathPositions(){
        return this.loadSpecialTilesAsPositions(Settings.MapSettings.WALKABLE_TILES_PATH);
    }

    public ArrayList<Position> loadEnergizersPositions(){
        return this.loadSpecialTilesAsPositions(Settings.MapSettings.ENERGIZERS_PATH);
    }

    public ArrayList<Position> loadSpawnPositions(){
        ArrayList<Position> spawns = this.loadSpecialTilesAsPositions(Settings.MapSettings.SPAWN_POINTS_PATH);
        return spawns;
    }

    public ArrayList<Position> loadTunnelsPositions(){
        return this.loadSpecialTilesAsPositions(Settings.MapSettings.TUNNELS_POINTS_PATH);
    }

    public ArrayList<Position> loadPellets(){
        return this.loadSpecialTilesAsPositions(Settings.MapSettings.PELLETS_PATH);
    }

    public void drawBaseMap(Graphics g){
        int index = 0;
        for(int j=0;j<Settings.MapSettings.MAP_VERTICAL_TILES;j++){
            for(int i=0;i<Settings.MapSettings.MAP_HORIZONTAL_TILES;i++){
                g.drawImage(this._baseMap[index], i* Settings.MapSettings.MAP_TILE_WIDTH, j *Settings.MapSettings.MAP_TILE_WIDTH, Settings.MapSettings.MAP_TILE_WIDTH, Settings.MapSettings.MAP_TILE_WIDTH, null);
                index++;
            }
        }
    }
}