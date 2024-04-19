package C22488994;

import ie.tudublin.Visual;
import ddf.minim.Minim;
import ddf.minim.ugens.Waveform;
import processing.core.PApplet;

public class Life extends PApplet {
    // Your code here
    Waveform wf;
    Minim minim;
    Visual visual; // declare visual object
    LifeBoard board;

    public void settings() {
        // window size
        size(1000, 1000);
        board = new LifeBoard(200, this);
    }

    public void setup() {
        // setup code
        minim = new Minim(this);
        visual = new Visual() {};
        String fname = "Heartbeat.mp3";
        minim.loadFile(fname);
        background(0);
        colorMode(RGB);
    }

    public void draw() {
        board.render();
        board.update();
    }

    public static void main(String[] args) {
        PApplet.main("C22488994.ac");
    }
}
