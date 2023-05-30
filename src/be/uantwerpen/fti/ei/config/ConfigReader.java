package be.uantwerpen.fti.ei.config;

import be.uantwerpen.fti.ei.enums.MusicType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Class that reads .txt files to obtain the game's configuration. */
public class ConfigReader {
    private final int[] screenDimen = new int[2];
    private final int[] playerConfig = new int[2];
    /**
     * List containing all the different level configurations
     * @see     LevelConfig
     */
    private final ArrayList<LevelConfig> levels = new ArrayList<>();
    /**
     * Dictionary that binds music clips with a name
     * @see     MusicType
     */
    private final Map<MusicType, String> music = new HashMap<>();

    /**
     * Method to read and store the settings of multiple config files.
     * {@link}  &nbsp;Reading text files <a href="https://www.geeksforgeeks.org/different-ways-reading-text-file-java/">Reading text files</a>
     * @param   path a string representing the path to the main config file
     */
    public void readSettings(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String config;
            while ((config = br.readLine()) != null) {
                System.out.println(config);
                String[] setting = config.split("=");
                if (setting[0].contains("level") && setting[0].contains("config")) addLevel(path, setting[1]);
                else switch (setting[0]) {
                    case "screen_width"     -> screenDimen[0] = Integer.parseInt(setting[1]);
                    case "screen_height"    -> screenDimen[1] = Integer.parseInt(setting[1]);

                    case "player_lives"     -> playerConfig[0] = Integer.parseInt(setting[1]);
                    case "player_size"      -> playerConfig[1] = Integer.parseInt(setting[1]);

                    case "audio_files"      -> addMusic(path, setting[1]);
                }
            }
        } catch (IOException e) { throw new RuntimeException(e); }
    }
    /**
     * Method to read and store the settings of multiple config files.
     * @param   path a string representing the path to the main config file
     * @param   file a string representing the name of the level config file
     * @throws  IOException throws when the given file doesn't exist
     */
    private void addLevel(String path, String file) throws IOException {
        if (path.contains(".txt")) path = path.substring(0, path.lastIndexOf('/') + 1);
        BufferedReader br = new BufferedReader(new FileReader(path + file));
        String config;
        LevelConfig level = new LevelConfig();
        while ((config = br.readLine()) != null) {
            System.out.println(config);
            String[] setting = config.split("=");
            switch (setting[0]) {
                case "enemy_total"  -> level.setEnemyTotal(Integer.parseInt(setting[1]));
                case "enemy_lives"  -> level.setEnemyLives(Integer.parseInt(setting[1]));
                case "enemy_width"   -> level.setEnemyWidth(Integer.parseInt(setting[1]));

                case "boss_total"   -> level.setBossTotal(Integer.parseInt(setting[1]));
                case "boss_lives"   -> level.setBossLives(Integer.parseInt(setting[1]));
                case "boss_width"    -> level.setBossWidth(Integer.parseInt(setting[1]));

                case "wall_total"   -> level.setWallTotal(Integer.parseInt(setting[1]));
                case "wall_lives"   -> level.setWallLives(Integer.parseInt(setting[1]));
                case "wall_width"    -> level.setWallWidth(Integer.parseInt(setting[1]));
            }
        }
        levels.add(level);
    }
    /**
     * Method to read and store the settings of multiple music files.
     * @param   path a string representing the path to the main config file
     * @param   file a string representing the name of the music config file
     * @throws  IOException throws when the given file doesn't exist
     */
    private void addMusic(String path, String file) throws IOException {
        if (path.contains(".txt")) path = path.substring(0, path.lastIndexOf('/') + 1);
        BufferedReader br = new BufferedReader(new FileReader(path + file));
        String config;
        while ((config = br.readLine()) != null) {
            System.out.println(config);
            String[] setting = config.split("=");
            try {
                // Cast config parameter to enum
                MusicType type = MusicType.valueOf(setting[0].toUpperCase());
                // Add if enum of parameter exists
                music.put(type, setting[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(setting[0] + " is not a music type");
            }
        }
    }

    /**
     * Returns the screen-dimensions defined by the config-file
     * @return  an array of integers representing the screen-dimensions
     */
    public int[] getScreenDimen() { return screenDimen; }
    /**
     * Returns the player configuration defined by the config-file
     * @return  an array of integers representing the player configuration
     */
    public int[] getPlayerConfig() { return playerConfig; }
    /**
     * Returns a list containing all the different level configurations
     * @return  a list of all the different level configurations
     * @see     LevelConfig
     */
    public ArrayList<LevelConfig> getLevels() { return levels; }
    /**
     * Returns a dictionary containing all the music files
     * @return  a dictionary containing all the music files
     * @see     MusicType
     */
    public Map<MusicType, String> getMusic() { return music; }
}
