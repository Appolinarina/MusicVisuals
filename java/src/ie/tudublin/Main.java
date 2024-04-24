package ie.tudublin;

import C22447674.PP;
import C22488994.ac;
import C22305656.FA;
import ddf.minim.Minim;
import ie.tudublin.Visual;
import processing.core.PApplet;

public class Main extends PApplet {
    StageManager stageManager;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        stageManager = new StageManager(this);
    }

    public void draw() {
        background(0);
        stageManager.display();
    }

    public void keyPressed() {
        stageManager.checkKeyPress(key);
    }

    public static void main(String[] args) {
        PApplet.main("ie.tudublin.Main");
    }
}