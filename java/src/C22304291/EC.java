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

        
        // Calculate the average amplitude of the audio buffer
        float[] buffer = ab.toArray();
        float avgAmplitude = 0;
        for (float v : buffer) {
            avgAmplitude += abs(v);
        }
        avgAmplitude /= buffer.length;

        // Set the base scale to 20.0f as requested
        float baseScale = 10.0f;
        float scaleFactor = baseScale + map(avgAmplitude, 0, 1, 0, 20.0f); // Dynamic scale based on amplitude

        // Translate to the center of the screen for the heart drawing
        pushMatrix();
        translate(width / 2, height / 2);

        // Use scale factor to change the heart size based on the audio amplitude
        scale(scaleFactor, scaleFactor);

        // Draw the heart shape
        smooth();
        noStroke();
        fill(255, 0, 0); // Red color for the heart

        // Left side of the heart
        beginShape();
        vertex(0, -baseScale);
        bezierVertex(-baseScale, -3 * baseScale, -3 * baseScale, -baseScale, 0, baseScale);
        endShape(CLOSE);

        // Right side of the heart
        beginShape();
        vertex(0, -baseScale);
        bezierVertex(baseScale, -3 * baseScale, 3 * baseScale, -baseScale, 0, baseScale);
        endShape(CLOSE);

        popMatrix();

        // Draw the waveform
        stroke(255);
        strokeWeight(2);
        noFill();
        for (int i = 0; i < buffer.length - 1; i++) {
            float x1 = map(i, 0, buffer.length, 0, width);
            float y1 = map(buffer[i], -1, 1, 0, height);
            float x2 = map(i + 1, 0, buffer.length, 0, width);
            float y2 = map(buffer[i + 1], -1, 1, 0, height);
            line(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
