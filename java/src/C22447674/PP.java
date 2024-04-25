package C22447674;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import processing.core.PApplet;



public class PP extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    float[] lerpedBuffer; // lerped values of all bands in the audio
    float lerpedAvg = 0;

    // initialising default values for torus rotation, radii 
    float rotX = 0, rotY = 0;
    float defaultr1 = 100;
    float defaultr2 = 60;

    float r1, r2; // current values of radii meant to be manipulated with amplitude

    float hue; // hue of current line

    public void settings()
    {
        size(1000, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }
    
    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("java/data/Heartbeat.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        
    }

    public void draw(){
        background(0);

        //create buffer for size of audio file
        lerpedBuffer = new float[ab.size()];

        float cx = width / 2;   
        float cy = height / 2;


        // calculate avg magnitude of bands
        float tot = 0;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            tot += abs(ab.get(i));
        }

        float avg = tot / ab.size();
        lerpedAvg = lerp(lerpedAvg, avg, 0.1f);

        // Lerp band magnitudes for smoother visualization
        for (int i = 0; i < ab.size(); i ++) {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

            // visualisations along all 4 sides of the screen
            float sample = lerpedBuffer[i] * width * 5;    
            stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
            line(0, i, sample, i); 
            line(width, i, width - sample, i); 
            line(i, 0, i, sample); 
            line(i, height, i, height - sample);
        }

        // Position the camera so the torus fills the black space
        float cameraDistance = height / 2.0f / tan(QUARTER_PI);
        float cameraX = cameraDistance * cos(QUARTER_PI);
        float cameraY = cameraDistance * sin(QUARTER_PI);
        float cameraZ = height / 2.0f;
        camera(cameraX, cameraY, cameraZ, width/2.0f, height/2.0f, 0, 0, 0, -1);
        translate(cx, cy);
        rotateX(rotX);
        rotateY(rotY); 
        strokeWeight(2);

        int lines = 100; // Number of lines
        float angleIncrement = TWO_PI / lines; // Angle increment between lines
        float hueIncrement = (255.0f / lines); // Hue increment between lines

        float defaulthue = 0; // Initial hue value
        
        // changing torus size with amplitude of audio
        r1 = defaultr1;
        r2 = (defaultr2 * lerpedAvg) * 10;

        pushMatrix();
        //loops around torus shape and draws a ring perpendicular to the circumference at an interval of angle
        for (float angle = 0; angle < TWO_PI; angle += angleIncrement) {
            stroke(hue, 255, 255); 
            beginShape(LINES);
            // generating torus shape
            for (float torusAngle = 0; torusAngle < TWO_PI; torusAngle += 0.1) {
                float x1 = (r1 + r2 * cos(torusAngle)) * cos(angle);
                float y1 = (r1 + r2 * cos(torusAngle)) * sin(angle);
                float z1 = r2 * sin(torusAngle);
                float x2 = (r1 + r2 * cos((float) (torusAngle + 0.1))) * cos(angle);
                float y2 = (r1 + r2 * cos((float) (torusAngle + 0.1))) * sin(angle);
                float z2 = r2 * sin((float) (torusAngle + 0.1));
                vertex(x1, y1, z1);
                vertex(x2, y2, z2);
            }
            endShape();
            // update the hue for the next ring
            defaulthue += hueIncrement;
            // loop hue offset of the ring with mouse movement
            hue = defaulthue + mouseX/3;
            if(hue > 255){
                hue = hue % 255;
            }
        }
        popMatrix();
        
        // Update rotation angles for next frame
        rotX -= 0.01 * lerpedAvg * 20; 
        rotY += 0.02 * lerpedAvg * 20; 
        camera(cx, cy, cy / tan((float) (PI * 30.0 / 180.0)), cx, cy, 0, 0, 1, 0);

        
    }
}
