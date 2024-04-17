package C22304291;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class EC extends PApplet {
    Minim minim;
    AudioPlayer track;
    AudioBuffer ab;
    int canvasWidth = 1080;
    int canvasHeight = 1080;

    // Additional variables for the visual effect
    boolean direction = false;
    int surrCount = 0;
    float sphereRadius = 50;
    float center_x = canvasWidth / 2;
    int unit = 100;

    public void settings() {
        size(canvasWidth, canvasHeight);
        smooth(8);
    }

    public void setup() {
        minim = new Minim(this);
        try {
            track = minim.loadFile("../data/Heartbeat.mp3", 1024);
            track.play();
            ab = track.mix;
        } catch (Exception e) {
            println("Error loading audio: " + e.getMessage());
        }
    }

    public void draw() {
        background(0); // Clear the canvas each frame
        float surrRadMin = sphereRadius + sphereRadius * 0.5f * surrCount;
        float surrRadMax = surrRadMin + surrRadMin * 0.125f;
        float addon = frameCount * 1.5f;

        if (direction) {
            addon *= 1.5;
        }

        for (float angle = 0; angle <= 240; angle += 1.5) {
            float surroundingRadius = map(sin(radians(angle * 7 + addon)), -1, 1, surrRadMin, surrRadMax);
            float surrYOffset = sin(radians(150)) * surroundingRadius;
            int x = round(cos(radians(angle + 150)) * surroundingRadius + center_x);
            float y = round(sin(radians(angle + 150)) * surroundingRadius + getGroundY(x) - surrYOffset);

            noStroke();
            fill(map(surroundingRadius, surrRadMin, surrRadMax, 100, 255));
            circle(x, y, 3 * unit / 10.24f);
            noFill();
        }

        direction = !direction;
        surrCount++;
    }

    // Utility method for calculating ground Y based on X
    float getGroundY(float x) {
        return height / 2;
    }

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
