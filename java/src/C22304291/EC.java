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

    boolean direction = false;
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
        float amplitude = ab.level() * 500; // Scale amplitude level

        for (float angle = 0; angle <= 360; angle += 5) {
            float radius = sphereRadius + amplitude;
            float x = cos(radians(angle)) * radius + center_x;
            float y = sin(radians(angle)) * radius + height / 2;
            float colorScale = map(amplitude, 0, 500, 100, 255);

            noStroke();
            fill(colorScale, 100, 255);
            circle(x, y, unit / 10);
            noFill();
        }
    }

    float getGroundY(float x) {
        return height / 2;
    }

    public static void main(String[] args) {
        PApplet.main("C22304291.EC");
    }
}
