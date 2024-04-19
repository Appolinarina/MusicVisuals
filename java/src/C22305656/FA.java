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

        // Scale the model
        model.scale(1); 
    }

    public void draw() {
        background(0);
        drawWaveVisualisation(player);

        // Analyze spectrum
        fft.forward(player.mix);

        // Get amplitude values from specific frequency bands
        float bassAmplitude = fft.getBand(50);  // Adjust frequency bands as needed
        float midAmplitude = fft.getBand(500);
        float trebleAmplitude = fft.getBand(2000);

        
        float overallAmplitude = bassAmplitude + midAmplitude + trebleAmplitude;

        float rotationSpeed = map(overallAmplitude, 0.0f, 1.0f, -0.05f, 0.05f); 

        rotationAngle += rotationSpeed;

        pushMatrix();
        translate(width / 2, height / 2); // Center of the screen
        rotateY(rotationAngle); // Rotate around Y-axis
        scale(5); // Scale the model 

        fill(255); // Set a fixed color
        noStroke(); // Disable stroke for smooth appearance
        shape(model); // Draw the model shape

        popMatrix();
    }

    public void drawWaveVisualisation(AudioPlayer music) {
        colorMode(HSB, 255); 
    
        noStroke();
    
        float orbValue = 0;
        float dotsValue = 0;
    
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
    
            // draw orb
            fill(hue, 255, 255);
            ellipse(orbX + width * 0.1f, orbY + height * 0.1f, music.left.get(i) * 5, music.left.get(i) * 5);
            fill(hue, 255, 255);
            rect(orbX + width * 0.8f, orbY + height * 0.1f, music.right.get(i) * 5, music.left.get(i) * 5);
    
            // draw dots
            fill(hue, 255, 255);
            rect(dotsX + width * 0.1f, dotsY + height * 0.8f, music.left.get(i) * 10, music.left.get(i) * 5);
            fill(hue, 255, 255);
            rect(dotsX + width * 0.8f, dotsY + height * 0.8f, music.right.get(i) * 5, music.right.get(i) * 10);
        }
    
        // update orb and dots values
        orbValue += 0.4;
        dotsValue += 0.008;
    }

    public static void main(String[] args) {
        String[] a = { "FA" };
        PApplet.runSketch(a, new FA());
    }
}
