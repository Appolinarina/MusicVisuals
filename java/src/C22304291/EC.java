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
    float rotationAngle = 0.0f;  // Rotation angle in radians

    public void settings() {
        size(canvasWidth, canvasHeight, P3D); // Use P3D renderer
        
        smooth(8);
    }
    

    public void setup() {
        colorMode(PApplet.HSB, 360, 100, 100);
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
        ps = new ParticleSystem(new PVector(width / 2, 50), this,600);

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
    
        
        pushMatrix(); 
        translate(0, 0, -50); // Move waveform back in Z-axis to appear behind the heart
    
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float y1 = map(ab.get(i), -1, 1, yOffset - yScale, yOffset + yScale);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y2 = map(ab.get(i + 1), -1, 1, yOffset - yScale, yOffset + yScale);
            
            stroke(255,100);
            strokeWeight(2);
            noFill();
            line(x1, y1, x2, y2);
        }
    
        popMatrix(); 

        // Draw waveform at the top behind the heart, mirroring the bottom waveform
        pushMatrix();
        translate(0, 0, -50); 
        for (int i = 0; i < ab.size() - 1; i++) {
            float x1 = map(i, 0, ab.size(), 0, width);
            float y1 = map(ab.get(i), -1, 1, yOffsetTop - yScale, yOffsetTop + yScale);
            float x2 = map(i + 1, 0, ab.size(), 0, width);
            float y2 = map(ab.get(i + 1), -1, 1, yOffsetTop - yScale, yOffsetTop + yScale);
            
            stroke(255,100);
            strokeWeight(2);
            noFill();
            line(x1, y1, x2, y2);
        }
        popMatrix();
    
        // Audio-driven particle system update
        float avgAmplitude = 0;
        for (float v : ab.toArray()) {
            avgAmplitude += abs(v);
        }

        // Calculate average amplitude
        avgAmplitude /= ab.size();  

        

        // Prepare to draw the heart model
        pushMatrix();  // Isolate transformations applied to the heart model
        translate(width / 2, height / 2); // Center the heart model
        rotateX(PI);  // Adjust orientation if necessary
        rotateY(PI);  // Rotate around the y-axis

        // Increment the rotation angle for smooth animation
        rotationAngle += 0.05;  // Control speed of rotation

        // Apply scaling based on audio amplitude
        float scaleFactor = 200 + map(avgAmplitude, 0, 1, 0, 250);
        scale(scaleFactor);  // Apply scaling transformation

        // Set color based on audio
        int pinkColor = lerpColor(color(350, 29, 100), color(255, 100, 40), map(avgAmplitude, 0, 1, 0, 1));
        setShapeColor(heartModel, pinkColor);

        // Draw the heart model
        shape(heartModel);
        popMatrix();  

        
        fft.forward(track.mix);  // Perform FFT on the current audio mix
        float avg = 0; // Average amplitude
        for (int i = 0; i < fft.specSize(); i++) {
            avg += fft.getBand(i);
        }
        avg /= fft.specSize();

        int snowColor = color(map(avg, 0, 5, 330, 270), 100, 100); // Mapping avg to a blue scale

        ps.addParticle();
        ps.run(snowColor);
        
    }
    
    
    

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
