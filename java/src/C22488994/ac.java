package C22488994;

import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import ddf.minim.*;
import C22488994.LifeBoard;

public class ac extends PApplet {
    Minim minim;
    BeatDetect beat;
    LifeBoard board;
    boolean beatDetected = false;
    AudioPlayer player;
    int cellR = 255; // Default color for cells
    int cellG = 255;
    int cellB = 255;

    public void settings() {
        size(1000, 1000);
        board = new LifeBoard(200, this);
    }

    public void setup() {
        minim = new Minim(this);
        beat = new BeatDetect();
        player = minim.loadFile("java/data/Heartbeat.mp3");
        player.play();
        beat.setSensitivity(300); // Adjust sensitivity for beat detection
    }

    public void draw() {
        background(0);
        beat.detect(player.mix);
        
        if (beat.isOnset()) {
            beatDetected = true;
            cellR = (int) random(256); // Generate random color values
            cellG = (int) random(256);
            cellB = (int) random(256);
        }
        
        if (beatDetected) {
            board.update();
            beatDetected = false; // Reset beat detection flag
        }
        
        board.render(cellR, cellG, cellB);
    }

    public static void main(String[] args) {
        PApplet.main("C22488994.ac");
    }
}
