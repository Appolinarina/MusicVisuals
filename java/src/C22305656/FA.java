package C22305656;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.PShape;

public class FA extends PApplet {
    private Minim minim;
    private AudioPlayer player;
    private FFT fft;
    private PShape model;

    String audioFilePath;
    float rotationAngle = 0; 
    float rotationSpeed = 0;

    boolean isPaused = false; 

    public FA() {
        this.audioFilePath = "java/data/Heartbeat.mp3"; 
    }

    public FA(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public void settings() {
        size(800, 600, P3D); // Set the renderer to P3D for 3D rendering
    }

    public void setup() {
        minim = new Minim(this);
        player = minim.loadFile(audioFilePath, 1024);
        player.play();
        fft = new FFT(player.bufferSize(), player.sampleRate());

        model = loadShape("C22305656/Ned.obj");
        model.rotateX(PI); 

        model.scale(1); 
    }

    public void draw() {
        background(0);
        
        float overallAmplitude = 0;

        // Only update rotation when audio is not paused
        if (!isPaused) {
            drawWaveVisualisation(player);
        
            // Analyze spectrum
            fft.forward(player.mix);
        
            // Get amplitude values from specific frequency bands
            float bassAmplitude = fft.getBand(50);  
            float midAmplitude = fft.getBand(500);
            float trebleAmplitude = fft.getBand(2000);
        
            // Calculate overall amplitude
            overallAmplitude = bassAmplitude + midAmplitude + trebleAmplitude;
        
            // Map overall amplitude to rotation speed
            float rotationSpeed = map(overallAmplitude, 0.0f, 1.0f, -0.05f, 0.05f); 
            rotationAngle += rotationSpeed;
        }

        float hue = map(overallAmplitude, 0.0f, 1.0f, 0, 255); 
        model.setFill(color(hue, 255, 255));
        
        pushMatrix();
        translate(width / 2, height / 2); // Center of the screen
        rotateY(rotationAngle); // Rotate around Y-axis
        scale(5); // Scale the model 
        shape(model); // Draw the model shape
        popMatrix();
    }

    public void drawWaveVisualisation(AudioPlayer music) {
        colorMode(HSB, 255); 
        noStroke();
    
        float orbValue = 0;
        float dotsValue = 0;
        
        float quarterWidth = width / 4;
        float quarterHeight = height / 4;
        
        for (int i = 0; i < music.bufferSize() - 1; i++) {
            // orb calculations
            float orbAngle = sin(i + orbValue) * 300;
            float orbX = sin(radians(i)) * (orbAngle + 30);
            float orbY = cos(radians(i)) * (orbAngle + 30);
    
            // dots calculations
            float dotsAngle = sin(i + dotsValue) * 10;
            float dotsX = sin(radians(i)) * (500 / dotsAngle);
            float dotsY = cos(radians(i)) * (500 / dotsAngle);
    
            float hue = map(i, 0, music.bufferSize(), 0, 255);
    
            // draw orbs and dots in each quadrant
            drawOrb(orbX + quarterWidth, orbY + quarterHeight, hue, music.left.get(i));
            drawOrb(orbX + 3 * quarterWidth, orbY + quarterHeight, hue, music.left.get(i));
            drawOrb(orbX + quarterWidth, orbY + 3 * quarterHeight, hue, music.left.get(i));
            drawOrb(orbX + 3 * quarterWidth, orbY + 3 * quarterHeight, hue, music.left.get(i));
    
            drawDot(dotsX + quarterWidth, dotsY + quarterHeight, hue, music.left.get(i));
            drawDot(dotsX + 3 * quarterWidth, dotsY + quarterHeight, hue, music.left.get(i));
            drawDot(dotsX + quarterWidth, dotsY + 3 * quarterHeight, hue, music.left.get(i));
            drawDot(dotsX + 3 * quarterWidth, dotsY + 3 * quarterHeight, hue, music.left.get(i));
        }
    
        // update orb and dots values
        orbValue += 0.4;
        dotsValue += 0.008;
    }
    
    // Function to draw orbs
    void drawOrb(float x, float y, float hue, float size) {
        fill(hue, 255, 255);
        ellipse(x, y, size * 5, size * 5);
    }
    
    // Function to draw dots
    void drawDot(float x, float y, float hue, float size) {
        fill(hue, 255, 255);
        rect(x, y, size * 10, size * 5);
    }

    public void keyPressed() {
        if (key == ' ') { // check if space bar is pressed
            if (isPaused) {
                player.play(); // if audio is paused, resume 
            } else {
                player.pause(); // if audio is playing, pause 
            }
            isPaused = !isPaused; 
        } else if (key == '1') { // check if '1' is pressed
            player.rewind(); // rewind the audio to the beginning
            rotationAngle = 0; // reset the rotation angle 
        }
    }

    public static void main(String[] args) {
        String[] a = { "FA" };
        PApplet.runSketch(a, new FA());
    }
}
