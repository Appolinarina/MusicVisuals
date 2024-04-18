package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;;

public class EC extends PApplet {
    Minim minim;
    AudioPlayer track;
    AudioBuffer ab;
    FFT fft;
    int canvasWidth = 768;
    int canvasHeight = 768;
    PShape heartModel;
    ParticleSystem ps;

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
        // Initialize ParticleSystem
        ps = new ParticleSystem(new PVector(width / 2, 50), this,400);

    }

    public void setShapeColor(PShape shape, int color) {
        shape.setFill(color);
        for (int i = 0; i < shape.getChildCount(); i++) {
            setShapeColor(shape.getChild(i), color);
        }
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
        float yOffsetTop = height * 0.25f;
    
        // Draw particles
        ps.addParticle();
        ps.run();
        pushMatrix(); // Save current transformation matrix
        translate(0, 0, -50); // Move waveform back in Z-axis to appear behind the heart
    
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float y1 = map(ab.get(i), -1, 1, yOffset - yScale, yOffset + yScale);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y2 = map(ab.get(i + 1), -1, 1, yOffset - yScale, yOffset + yScale);
            // Calculate amplitude for color mapping
            float amplitude = abs(ab.get(i)); // absolute value for amplitude
            
            // Map amplitude to a color gradient from light blue (low) to red (high)
            int c = lerpColor(color(255), color(139, 0, 0), map(amplitude, 0, 1, 0, 1));
            stroke(c);
            line(x1, y1, x2, y2);
        }
    
        popMatrix(); // Restore previous transformation matrix
        // Draw waveform at the top behind the heart, mirroring the bottom waveform
        pushMatrix();
        translate(0, 0, -50); // Move waveform back in Z-axis
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float y1 = map(ab.get(i), -1, 1, yOffsetTop - yScale, yOffsetTop + yScale);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y2 = map(ab.get(i + 1), -1, 1, yOffsetTop - yScale, yOffsetTop + yScale);
            // Calculate amplitude for color mapping
            float amplitude = abs(ab.get(i)); // absolute value for amplitude
            
            // Map amplitude to a color gradient from light blue (low) to red (high)
            int c = lerpColor(color(255), color(139, 0, 0), map(amplitude, 0, 1, 0, 1));
            stroke(c);
            line(x1, y1, x2, y2);
        }
        popMatrix();
    
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
        float scaleFactor = 50 + map(avgAmplitude, 0, 1, 0, 70);
    
        
        // Apply scaling based on audio
        scale(scaleFactor * 10);
        // Set the heart model color to light pink and display it
        int pinkColor =lerpColor(color(255, 182, 193), color(139, 0, 0), map(avgAmplitude, 0, 1, 0, 1)); // RGB for dark red
        setShapeColor(heartModel, pinkColor);
        shape(heartModel);

        
    }
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
