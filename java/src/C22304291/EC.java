package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class EC extends PApplet {
    Minim minim;
    AudioPlayer track;
    AudioBuffer ab;
    int canvasWidth = 768;
    int canvasHeight = 768;

    public void settings() {
        size(canvasWidth, canvasHeight);
        smooth(8);
    }

    public void setup() {
        minim = new Minim(this);
        track = minim.loadFile("../data/Heartbeat.mp3", 2048);
        track.loop();
        ab = track.mix;
    }

    public void draw() {
        background(0);
        stroke(255);
        strokeWeight(2);
        noFill();

        // Get the buffer sample for drawing the waveform
        float[] buffer = ab.toArray();

        for (int i = 0; i < buffer.length - 1; i++) {
            // Map buffer value to a Y position
            float x1 = map(i, 0, buffer.length, 0, width);
            float y1 = map(buffer[i], -1, 1, 0, height);
            float x2 = map(i + 1, 0, buffer.length, 0, width);
            float y2 = map(buffer[i + 1], -1, 1, 0, height);
            
            line(x1, y1, x2, y2);
        }

        // Set drawing properties for the heart shape
        smooth();
        noStroke();
        fill(255, 0, 0); // Red color for the heart
        
        // Draw the left half of the heart with a Bezier curve
        beginShape();
        vertex(50, 15);
        bezierVertex(50, -5, 90, 5, 50, 40);
        endShape();
        
        // Draw the right half of the heart with another Bezier curve
        beginShape();
        vertex(50, 15);
        bezierVertex(50, -5, 10, 5, 50, 40);
        endShape();
    }

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
