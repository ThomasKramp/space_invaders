package be.uantwerpen.fti.ei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigReader {
    int width, height;

    // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public void readFile() throws IOException {
        String cwd = Path.of("").toAbsolutePath().toString();
        System.out.println(cwd + "\\config.txt");
        File file = new File(cwd + "\\src\\be\\uantwerpen\\fti\\ei\\config.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String config;
        while ((config = br.readLine()) != null) {
            System.out.println(config);
            String[] words = config.split(" ");
            if (words[0].equals("width")) width = Integer.parseInt(words[1]);
            if (words[0].equals("height")) height = Integer.parseInt(words[1]);
        }
    }
}
