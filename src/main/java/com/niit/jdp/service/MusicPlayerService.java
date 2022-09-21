package com.niit.jdp.service;/*
 *Author Name: Sneha Shinde
 *Date: 9/20/2022
 * Created With: IntelliJ IDEA Community Edition
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayerService {
    public void play(String songPath) {
        File songFile = new File(songPath);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            long songDurationInMilliseconds = clip.getMicrosecondLength() / 1000L;
            Thread.sleep(songDurationInMilliseconds);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("song thread was interrupted");
        }
    }
}

