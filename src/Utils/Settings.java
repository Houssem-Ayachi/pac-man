package Utils;

public class Settings {
    public static final float TILE_SCALE = 2.5f;
    
    public class UISettings{
        public static final int UI_BOX_HEIGHT = 15;
    }

    public class MapSettings{

        public static final int MAP_TILE_SIZE = 8;
        public static final int MAP_HORIZONTAL_TILES = 28;
        public static final int MAP_VERTICAL_TILES = 31;
        public static final int MAP_TILE_WIDTH = (int) (Settings.MapSettings.MAP_TILE_SIZE * Settings.TILE_SCALE);
        public static final int MAP_TILES_NUMBER = MAP_HORIZONTAL_TILES * MAP_VERTICAL_TILES;
        public static final int MAP_WIDTH = MapSettings.MAP_HORIZONTAL_TILES * MapSettings.MAP_TILE_SIZE;
        public static final int MAP_HEIGHT = MapSettings.MAP_VERTICAL_TILES * MapSettings.MAP_TILE_SIZE;

        public static final String BASE_MAP_PATH = "../Assets/map/baseMap.png";
        public static final String WALKABLE_TILES_PATH = "../Assets/map/Path.png";
        public static final String ENERGIZERS_PATH = "../Assets/map/Energizers.png";
        public static final String SPAWN_POINTS_PATH = "../Assets/map/spawnPoints.png";
        public static final String TUNNELS_POINTS_PATH = "../Assets/map/tunnels.png";
        public static final String PELLETS_PATH = "../Assets/map/Pellets.png";
    }

    public class ActorSettings{
        public static final int ACTOR_SIZE = 16;
        public static final int ACTOR_WIDTH = (int) (ActorSettings.ACTOR_SIZE * 2);
        
        public static final String BLINKY_RIGHT = "../Assets/actors/ghosts/blinky/right.png";
        public static final String BLINKY_LEFT = "../Assets/actors/ghosts/blinky/left.png";
        public static final String BLINKY_UP = "../Assets/actors/ghosts/blinky/up.png";
        public static final String BLINKY_DOWN = "../Assets/actors/ghosts/blinky/down.png";

        public static final String INKY_RIGHT = "../Assets/actors/ghosts/inky/right.png";
        public static final String INKY_LEFT = "../Assets/actors/ghosts/inky/left.png";
        public static final String INKY_UP = "../Assets/actors/ghosts/inky/up.png";
        public static final String INKY_DOWN = "../Assets/actors/ghosts/inky/down.png";

        public static final String PINKY_RIGHT = "../Assets/actors/ghosts/pinky/right.png";
        public static final String PINKY_LEFT = "../Assets/actors/ghosts/pinky/left.png";
        public static final String PINKY_UP = "../Assets/actors/ghosts/pinky/up.png";
        public static final String PINKY_DOWN = "../Assets/actors/ghosts/pinky/down.png";

        public static final String CLYDE_RIGHT = "../Assets/actors/ghosts/clyde/right.png";
        public static final String CLYDE_LEFT = "../Assets/actors/ghosts/clyde/left.png";
        public static final String CLYDE_UP = "../Assets/actors/ghosts/clyde/up.png";
        public static final String CLYDE_DOWN = "../Assets/actors/ghosts/clyde/down.png";

        public static final String SCARED_GHOST_PATH = "../Assets/actors/ghosts/scared.png";
        
        public static final String DEAD_RIGHT = "../Assets/actors/ghosts/dead/right.png";
        public static final String DEAD_LEFT = "../Assets/actors/ghosts/dead/left.png";
        public static final String DEAD_UP = "../Assets/actors/ghosts/dead/up.png";
        public static final String DEAD_DOWN = "../Assets/actors/ghosts/dead/down.png";

        public static final int GHOST_SPRITE_NUMBER = 1;

        public static final String PAC_DOWN_PATH = "../Assets/actors/pac/pac_man_down.png";
        public static final String PAC_UP_PATH = "../Assets/actors/pac/pac_man_up.png";
        public static final String PAC_RIGHT_PATH = "../Assets/actors/pac/pac_man_right.png";
        public static final String PAC_LEFT_PATH = "../Assets/actors/pac/pac_man_left.png";
        public static final String PAC_DEAD_PATH = "../Assets/actors/pac/pac_man_dead.png";

        public static final int PAC_DOWN_TILES_NUMBER = 2;
        public static final int PAC_SIDE_TILES_NUMBER = 2;
        public static final int PAC_DEAD_TILES_NUMBER = 6;
    }

}

