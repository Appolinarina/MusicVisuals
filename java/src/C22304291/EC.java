package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PShape;

public class EC extends PApplet {
    Minim minim;
    AudioPlayer track;
    AudioBuffer ab;
    FFT fft;
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
        if (track == null) {
            println("Failed to load track.");
            exit(); // Exit if track cannot be loaded
        }
        track.loop();
        ab = track.mix;
        heartModel = loadShape("MusicVisuals/java/src/C22304291/heartObject.obj");
        if (heartModel == null) {
            println("Failed to load heart model.");
            exit(); // Exit if model cannot be loaded
        }

        fft = new FFT(track.bufferSize(), track.sampleRate());
    }

    public void draw() {
        background(0);
        lights();
    
        // Set up drawing parameters for the waveform
        stroke(255, 100); // Make waveform slightly transparent
        strokeWeight(2);
        noFill();
    
        // Set waveform height and position more tightly controlled
        float yScale = height / 8; // Reducing the multiplier for height
        float yOffset = height * 0.75f; // Positioning the waveform lower on the screen
    
        pushMatrix(); // Save current transformation matrix
        translate(0, 0, -50); // Move waveform back in Z-axis to appear behind the heart
    
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float y1 = map(ab.get(i), -1, 1, yOffset - yScale, yOffset + yScale);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y2 = map(ab.get(i + 1), -1, 1, yOffset - yScale, yOffset + yScale);
            line(x1, y1, x2, y2);
        }
    
        popMatrix(); // Restore previous transformation matrix
    
        // Center the drawing for the heart model
        translate(width / 2, height / 2);
        rotateX(PI); // Rotate to correct orientation if necessary
    
        // Calculate the average amplitude of the audio buffer
        float avgAmplitude = 0;
        for (float v : ab.toArray()) {
            avgAmplitude += abs(v);
        }
        avgAmplitude /= ab.size();
    
        // Map amplitude to a scale factor for dramatic effect
        float scaleFactor = 50 + map(avgAmplitude, 0, 1, 0, 50);
    
        // Apply scaling based on audio
        scale(scaleFactor * 10);
        shape(heartModel);
    }
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
