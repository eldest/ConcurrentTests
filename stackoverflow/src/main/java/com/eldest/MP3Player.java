package com.eldest;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MP3Player {

    public void play(InputStream inputStream) {
        try (BufferedInputStream in = new BufferedInputStream(inputStream)) {
            Player mp3player = new Player(in);
            mp3player.play();
        } catch (IOException | JavaLayerException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void play(String url) {
        try {
            play(new URL(url).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        System.setProperty("java.net.useSystemProxies", "true");

        new MP3Player().play("http://www.ntonyx.com/mp3files/Morning_Flower.mp3");
    }
}
