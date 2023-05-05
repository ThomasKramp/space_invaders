package be.uantwerpen.fti.ei.audio;

import be.uantwerpen.fti.ei.enums.MusicType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/play-audio-file-using-java/
public class AudioPlayer {
    Thread musicThread, effectThread;
    Clip musicClip, effectClip;
    final String pathToFile;
    final Map<MusicType, AudioInputStream> music;

    public AudioPlayer(String pathToFile, Map<MusicType, String> music) {
        this.pathToFile = pathToFile;

        // Load music
        this.music = new HashMap<>();
        try {
            for (Map.Entry<MusicType, String> entry : music.entrySet())
                this.music.put(entry.getKey(), AudioSystem.getAudioInputStream(new File(pathToFile + entry.getValue()).getAbsoluteFile()));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }

        // create clip reference
        try { musicClip = AudioSystem.getClip(); effectClip = AudioSystem.getClip(); }
        catch (LineUnavailableException e) { throw new RuntimeException(e); }
    }

    public void play(MusicType file) {
        // Close previous thread
        try { if (effectThread != null) effectThread.join(); }
        catch (InterruptedException e) { throw new RuntimeException(e); }

        // End previous clip
        if (effectClip.isOpen()) {
            effectClip.stop();
            effectClip.close();
        }

        effectThread = new Thread(() -> {
            // Start new clip
            try { effectClip.open(music.get(file)); }
            catch (LineUnavailableException | IOException e) { throw new RuntimeException(e); }
            effectClip.start();
        });
        effectThread.start();
    }

    public void playContinues(MusicType file) {
        musicThread = new Thread(() -> {
            // open audioInputStream to the clip
            try { musicClip.open(music.get(file)); }
            catch (LineUnavailableException | IOException e) { throw new RuntimeException(e); }
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        });
        musicThread.start();
    }
}
