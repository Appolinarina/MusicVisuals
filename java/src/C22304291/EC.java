package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;

public class EC extends PApplet {
    Minim minim;
    AudioPlayer track;
    AudioBuffer ab;
    int canvasWidth = 768;
    int canvasHeight = 768;

    public void settings() {
        size(canvasWidth, canvasHeight, P3D); // Use P3D renderer
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
        lights(); // Add lighting to better see the 3D effect
    
        // Calculate the average amplitude of the audio buffer
        float[] buffer = ab.toArray();
        float avgAmplitude = 0;
        for (float v : buffer) {
            avgAmplitude += abs(v);
        }
        avgAmplitude /= buffer.length;
    
        // Map amplitude to a scaling factor
        float scaleFactor = 20 + map(avgAmplitude, 0, 1, 0, 20);
    
        // Center the drawing
        translate(width / 2, height / 2 + 50); // Adjust vertical position to better view the heart
        rotateY(frameCount * 0.01f); // Continuous rotation for dynamic effect
    
        // Heart top parts - two overlapping spheres
        noStroke();
        fill(255, 0, 0); // Red color for the heart
        pushMatrix();
        translate(-30, 0, 0); // Adjust positioning to overlap spheres
        sphere(scaleFactor);
        popMatrix();
        pushMatrix();
        translate(30, 0, 0); // Adjust positioning to overlap spheres
        sphere(scaleFactor);
        popMatrix();
    
        // Heart bottom part - use a rotated ellipsoid to simulate a cone
        pushMatrix();
        rotateX(PI / 2); // Rotate to point the ellipsoid downwards
        translate(0, scaleFactor, 0);
        scale(1f, 2.5f, 1f); // Stretch the ellipsoid to make it more cone-like
        sphere(scaleFactor);
        popMatrix();
    }
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
