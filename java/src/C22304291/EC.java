package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.*;


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
        // Load the 3D model
        heartModel = loadShape("MusicVisuals/java/src/C22304291/heartObject.obj");
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
    
        // Display the heart model with scaling based on audio
        scale(scaleFactor * 10); // Adjust scaling factor as needed
        shape(heartModel);
    }
    
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
