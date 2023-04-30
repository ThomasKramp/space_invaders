package be.uantwerpen.fti.ei.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    private int[] screenDimen = new int[2];
    private List<LevelConfig> levels = new ArrayList<>();

    // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public void getScreenSettings(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String config;
        while ((config = br.readLine()) != null) {
            System.out.println(config);
            String[] words = config.split("\n");
            for (String word: words) {
                String[] setting = word.split("=");
                if (setting[0].equals("screen_width"))
                    screenDimen[0] = Integer.parseInt(setting[1]);
                else if (setting[0].equals("screen_height"))
                    screenDimen[1] = Integer.parseInt(setting[1]);
                else if (setting[0].contains("level") && setting[0].contains("config"))
                    addLevel(path, setting[1]);
            }
        }
    }
    private void addLevel(String path, String file) throws IOException {
        if (path.contains(".txt")) path = path.substring(0, path.lastIndexOf('\\') + 1);
        BufferedReader br = new BufferedReader(new FileReader(path + file));
        String config;
        LevelConfig level = new LevelConfig();
        while ((config = br.readLine()) != null) {
            System.out.println(config);
            String[] words = config.split("\n");
            for (String word: words) {
                String[] setting = word.split("=");
                switch (setting[0]) {
                    case "player_lives" -> level.setPlayerLives(Integer.parseInt(setting[1]));
                    case "player_size"  -> level.setPlayerSize(Integer.parseInt(setting[1]));

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
        }
        levels.add(level);
    }

    public int[] getScreenDimen() { return screenDimen; }
    public List<LevelConfig> getLevels() { return levels; }
}
