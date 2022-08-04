package handler;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundHandler {
    private final URL[] soundURL = new URL[4];
    private Clip clip;

    public SoundHandler() {
        soundURL[0] = getClass().getResource("/sounds/ambience.wav");
        soundURL[1] = getClass().getResource("/sounds/key.wav");
        soundURL[2] = getClass().getResource("/sounds/door.wav");
        soundURL[3] = getClass().getResource("/sounds/sign.wav");
    }

    public void setFile(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
