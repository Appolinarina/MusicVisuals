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
    PShape heartModel;

    public void settings() {
        size(canvasWidth, canvasHeight, P3D); // Use P3D renderer
        smooth(8);

    }

    public void setup() {
        minim = new Minim(this);
        track = minim.loadFile("../data/Heartbeat.mp3", 2048);
        track.loop();
        ab = track.mix;
        heartModel = loadShape("heartObject.obj");  // Make sure the file name matches
        heartModel.scale(0.1);  // Adjust the scale as needed
    }

    public void draw() {
        background(0);
        lights(); // Improved lighting for 3D visibility
    
        // Calculate the average amplitude of the audio buffer
        float[] buffer = ab.toArray();
        float avgAmplitude = 0;
        for (float v : buffer) {
            avgAmplitude += abs(v);
        }
        avgAmplitude /= buffer.length;
    
        // Map amplitude to a scale factor for dramatic effect
        float scaleFactor = 50 + map(avgAmplitude, 0, 1, 0, 50);
    
        // Center the drawing and scale
        translate(width / 2, height / 2);
        rotateY(frameCount * 0.01f);
    
        // Color and shape settings
        noStroke();
        fill(255, 0, 0); // Red color for the heart
    
        // Top part of the heart using two spheres
        pushMatrix();
        translate(-scaleFactor * 0.5f, 0, 0);
        sphere(scaleFactor * 0.6f);
        popMatrix();
    
        pushMatrix();
        translate(scaleFactor * 0.5f, 0, 0);
        sphere(scaleFactor * 0.6f);
        popMatrix();
    
        // Bottom part of the heart using a triangle fan
        beginShape(TRIANGLE_FAN);
        vertex(0, scaleFactor);  // Apex of the heart
        float angleStep = TWO_PI / 10; // Density of the fan points
        for (float angle = -PI/4; angle < 5 * PI / 4; angle += angleStep) {
            float x = cos(angle) * scaleFactor * 0.75f;
            float y = sin(angle) * scaleFactor * 0.75f;
            vertex(x, y, 0);
        }
        // Close the shape
        vertex(cos(-PI/4) * scaleFactor * 0.75f, sin(-PI/4) * scaleFactor * 0.75f, 0);
        endShape();
    }
    
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
