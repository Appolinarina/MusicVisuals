package C22488994;

import ie.tudublin.Visual;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.ugens.Waveform;
import processing.core.PApplet;


public class ac extends PApplet{
    // Your code here
    Waveform wf;
    PApplet p;
    Minim m;
    Visual visual; //declare visual object
    
    Minim minim; // declare minim object
    
    public void settings(){
        //window size
        size(width/10, height/10);
    }

    
    public void setup(){
        //setup code
        minim = new Minim(this);
        visual = new Visual() {};
        String fname = "Heartbeat.mp3";
        m.loadFile(fname);
        background(0);
    }
    
    public static void main(String[] args) {
        // ac ac = new ac();
        // ac.setup();
        PApplet.main("C22488994.ac");
    }
}
