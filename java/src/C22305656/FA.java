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
        drawWaveVisualization(player);
    }

    public void drawWaveVisualization(AudioPlayer music) {
        noStroke();

        float orbValue = 0;
        float dotsValue = 0;

        for (int i = 0; i < music.bufferSize() - 1; i++) {
            // orb calculations
            float orbAngle = sin(i + orbValue) * 300;
            float orbX = sin(radians(i)) * (orbAngle + 30);
            float orbY = cos(radians(i)) * (orbAngle + 30);

            // dots calculations
            float dotsAngle = sin(i + dotsValue) * 10;
            float dotsX = sin(radians(i)) * (500 / dotsAngle);
            float dotsY = cos(radians(i)) * (500 / dotsAngle);

            // draw orb
            fill(orbColor == 0 ? originalOrbColor : orbColor);
            ellipse(orbX, orbY, music.left.get(i) * 5, music.left.get(i) * 5);
            fill(orbColor == 0 ? originalOrbColor : orbColor);
            rect(orbX, orbY, music.right.get(i) * 5, music.left.get(i) * 5);

            // draw dots
            fill(dotsColor == 0 ? originalDotsColor : dotsColor);
            rect(dotsX, dotsY, music.left.get(i) * 10, music.left.get(i) * 5);
            fill(dotsColor == 0 ? originalDotsColor : dotsColor);
            rect(dotsX, dotsY, music.right.get(i) * 5, music.right.get(i) * 10);
        }

        // update orb and dots values
        orbValue += 0.4;
        dotsValue += 0.008;
    }

    // variables for orb and dots colors
    private float orbColor = 0;
    private float dotsColor = 0;
    private float originalOrbColor = 255;
    private float originalDotsColor = 255;

    public static void main(String[] args) {
        String[] a = { "FA" };
        PApplet.runSketch(a, new FA());
    }
}
