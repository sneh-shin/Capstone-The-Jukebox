package com.niit.jdp.service;/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayerService {

    File songFile;
    Clip clip;

    private String songPath;

    private boolean songStatus;

    private boolean pauseStatus;

    private long clipTime;

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public boolean isPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(boolean pauseStatus) {
        this.pauseStatus = pauseStatus;
    }

    /**
     * It takes the song path, creates a new audio input stream, opens the clip, loops it, sets the song status to true,
     * sets the clip time to the current time, and starts the clip
     */
    public void play() {
        try {
            songFile = new File(getSongPath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            setSongStatus(true);
            if (clipTime != 0) {
                clip.setMicrosecondPosition(clipTime);
            }
            clip.start();

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * If the song is playing, stop it
     */
    public void stop() {
        if (isSongStatus()) {
            setSongStatus(false);
            clip.stop();
        }
    }

    /**
     * If the song is paused, play it. If the song is playing, pause it
     */
    public void pause() {
        if (isPauseStatus()) {
            play();
            setPauseStatus(false);
        } else {
            setPauseStatus(true);
            clipTime = clip.getMicrosecondPosition();
            if (clipTime / 1000000 < 60) {
                long time = clipTime / 1000000;
                if (time < 10) {
                    System.out.println("Paused at 00:00:0" + time);
                } else {
                    System.out.println("Paused at 00:00:" + time);
                }
            }
            if (clipTime / 1000000 > 60) {
                System.out.println("Paused at 00:" + clipTime / 60000000 + ":" + (clipTime / 1000000) % 60);
            }
            clip.stop();
        }
    }

}
