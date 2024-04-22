package C22488994;

import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import ddf.minim.*;


public class ac extends PApplet {
    Minim minim;
    BeatDetect beat;
    LifeBoard board;
    boolean beatDetected = false;
    AudioPlayer player;

    public void settings() {
        size(1000, 1000);
        board = new LifeBoard(200, this);
    }

    public void setup() {
        minim = new Minim(this);
        beat = new BeatDetect();
        player = minim.loadFile("Heartbeat.mp3");
        player.play();
        beat.setSensitivity(300); // Adjust sensitivity for beat detection
    }

    public void draw() {
        background(0);
        beat.detect(player.mix);
        if (beat.isOnset()) {
            beatDetected = true;
        }
        if (beatDetected) {
            board.update();
            beatDetected = false;
        }
        board.render();
    }

    public static void main(String[] args) {
        PApplet.main("C22488994.ac");
    }
}
