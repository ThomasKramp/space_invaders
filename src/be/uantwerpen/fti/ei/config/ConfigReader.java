package be.uantwerpen.fti.ei.config;

import be.uantwerpen.fti.ei.enums.MusicType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private final int[] screenDimen = new int[2];
    private final int[] playerConfig = new int[2];
    private final ArrayList<LevelConfig> levels = new ArrayList<>();
    private final Map<MusicType, String> music = new HashMap<>();

    // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public void getScreenSettings(String path) {
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
                case "enemy_size"   -> level.setEnemySize(Integer.parseInt(setting[1]));

                case "boss_total"   -> level.setBossTotal(Integer.parseInt(setting[1]));
                case "boss_lives"   -> level.setBossLives(Integer.parseInt(setting[1]));
                case "boss_size"    -> level.setBossSize(Integer.parseInt(setting[1]));

                case "wall_total"   -> level.setWallTotal(Integer.parseInt(setting[1]));
                case "wall_lives"   -> level.setWallLives(Integer.parseInt(setting[1]));
                case "wall_size"    -> level.setWallSize(Integer.parseInt(setting[1]));
            }
        }
        levels.add(level);
    }

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

    public int[] getScreenDimen() { return screenDimen; }
    public int[] getPlayerConfig() { return playerConfig; }
    public ArrayList<LevelConfig> getLevels() { return levels; }
    public Map<MusicType, String> getMusic() { return music; }
}
