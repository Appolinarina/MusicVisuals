package C22305656;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class FA extends PApplet {
    private Minim minim;
    private AudioPlayer player;
    private FFT fft;

    String audioFilePath;

   
    public FA() {
        this.audioFilePath = "java/data/Heartbeat.mp3"; 
    }


    public FA(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        minim = new Minim(this);
        player = minim.loadFile(audioFilePath, 1024);
        player.play();
        fft = new FFT(player.bufferSize(), player.sampleRate());
    }

    public void draw() {
        background(0);
    }

    public static void main(String[] args) {
        String[] a = { "FA" };
        PApplet.runSketch(a, new FA());
    }
}
