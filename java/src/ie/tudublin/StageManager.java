package ie.tudublin;

import processing.core.PApplet;
import C22488994.ac;
import C22447674.PP;
import C22305656.FA;
import C22304291.EC;

public class StageManager {
    PApplet parent;

    public StageManager(PApplet parent) {
        this.parent = parent;
    }

    public void display() {
        parent.textSize(20);
        parent.fill(255);
        parent.text("Press keys 1 to 4 to switch between sketches:", 10, 30);
        parent.text("1: FA (Frequency Analysis)", 30, 60);
        parent.text("2: EC (Heartbeat Visualization)", 30, 90);
        parent.text("3: PP (Particle Playground)", 30, 120);
        parent.text("4: AC (Game of Life)", 30, 150);
    }

    public void checkKeyPress(char key) {
        switch (key) {
            case '1':
                PApplet.runSketch(new String[] { "FA" }, new FA());
                break;
            case '2':
                PApplet.runSketch(new String[] { "EC" }, new EC());
                break;
            case '3':
                PApplet.runSketch(new String[] { "PP" }, new PP());
                break;
            case '4':
                PApplet.runSketch(new String[] { "AC" }, new ac());
                break;
        }
    }
}
