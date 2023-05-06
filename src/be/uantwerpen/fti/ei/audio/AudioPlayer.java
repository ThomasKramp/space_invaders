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
    final Map<MusicType, Clip> music;

    public AudioPlayer(String pathToFile, Map<MusicType, String> music) {
        this.pathToFile = pathToFile;

        // Load music
        this.music = new HashMap<>();
        music.forEach((key, value) -> {
            /*Map.Entry<MusicType, Clip> musicEntry*/
            try {
                // create clip reference
                Clip clip = AudioSystem.getClip();
                // open audioInputStream to the clip
                clip.open(AudioSystem.getAudioInputStream(new File(pathToFile + value)/*.getAbsoluteFile()*/));
                // add clip
                this.music.put(key, clip);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void play(MusicType file) {
        // Close previous thread
        try { if (effectThread != null) effectThread.join(); }
        catch (InterruptedException e) { throw new RuntimeException(e); }

        // End previous clip
        if (effectClip != null && effectClip.isOpen()) {
            effectClip.stop();
            // effectClip.close();
        }

        effectThread = new Thread(() -> {
            // Start new clip
            effectClip = music.get(file);
            effectClip.setFramePosition(0);
            effectClip.start();
        });
        effectThread.start();
    }

    public void playContinues(MusicType file) {
        musicThread = new Thread(() -> {
            musicClip = music.get(file);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        });
        musicThread.start();
    }

    public void stop() {
        music.entrySet().stream().filter(entry -> entry.getValue() != null && entry.getValue().isOpen())
                .forEach(musicEntry -> {
                    /*Map.Entry<MusicType, Clip> musicEntry*/
                    musicEntry.getValue().stop();
                    musicEntry.getValue().close();
                });
        try {
            if (effectThread != null) effectThread.join();
            if (musicThread != null) musicThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
