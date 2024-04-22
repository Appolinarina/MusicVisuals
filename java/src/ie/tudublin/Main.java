package ie.tudublin;

import C22447674.PP;
import C22488994.ac;
import C22305656.FA;
import ddf.minim.Minim;
import ie.tudublin.Visual;
public class Main{
    public void startUI() {
        String[] a = { "MAIN" };
        //processing.core.PApplet.runSketch(a, new PP());
        //processing.core.PApplet.runSketch(a, new FA());
        processing.core.PApplet.runSketch(a, new ac());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
        
    }
}