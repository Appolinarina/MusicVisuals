package C22447674;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSource;
import ddf.minim.Minim;
import processing.core.PApplet;
import ie.tudublin.Visual;



public class PP extends PApplet {
    Visual visual;
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    int mode = 0;

    float[] lerpedBuffer;
    float rotX, rotY;
    float r1, r2;
    float defaultr1, defaultr2;

    public void settings()
    {
        size(1000, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }
    
    public void setup()
    {
        minim = new Minim(this);
        visual = new Visual() {};
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        ap = minim.loadFile("java/data/Heartbeat.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        rotX = rotY = 0;
        defaultr1 = 100;
        defaultr2 = 60;
        perspective((float) (PI/3.0), width/height, 1, 1000);
        pointLight(255, 255, 255, 400, 400, 400);
        float cameraX = (float)(width / 2.0);
        float cameraY = (float)(height / 2.0);
        float cameraZ = (float)((height / 2.0) / tan((float) (PI * 30.0 / 180.0)));
        float targetX = (float)(width / 2.0);
        float targetY = (float)(height / 2.0);
        float targetZ = 0;
        camera(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, 0, 1, 0);
        
    }

    float off = 0;


    float lerpedAvg = 0;
    float hue;
    public void draw(){
        background(0);
        //float average = 0;
        // float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        
        lerpedBuffer = new float[ab.size()];
    
        // Lerp band magnitudes for smoother visualization
        float cx = width / 2;   
        float cy = height / 2;

        float tot = 0;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            tot += abs(ab.get(i));
        }
        float avg = tot / ab.size();
        lerpedAvg = lerp(lerpedAvg, avg, 0.1f);

        for (int i = 0; i < ab.size(); i ++)
                {
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

                    float sample = lerpedBuffer[i] * width * 5;    
                    stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
                    line(0, i, sample, i); 
                    line(width, i, width - sample, i); 
                    line(i, 0, i, sample); 
                    line(i, height, i, height - sample);
                }


        float cameraDistance = height / 2.0f / tan(QUARTER_PI);
        float cameraX = cameraDistance * cos(QUARTER_PI);
        float cameraY = cameraDistance * sin(QUARTER_PI);
        float cameraZ = height / 2.0f;
        camera(cameraX, cameraY, cameraZ, width/2.0f, height/2.0f, 0, 0, 0, -1);
        pushMatrix();
        translate(cx, cy);
        rotateX(rotX);
        rotateY(rotY); 
        stroke(255);
        strokeWeight(2);
        int lines = 100; // Number of lines
        float angleIncrement = TWO_PI / lines; // Angle increment between lines
        float hueIncrement = (255.0f / lines); // Hue increment between lines

        // 
        float defaulthue = 0; // Initial hue value
        r1 = defaultr1;
        r2 = (defaultr2 * lerpedAvg) * 10;
        for (float angle = 0; angle < TWO_PI; angle += angleIncrement) {
            stroke(hue, 255, 255); // Set stroke color based on current hue
            beginShape(LINES);
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
            defaulthue += hueIncrement; // Increment hue for the next line 
            hue = defaulthue + mouseX/3;
            if(hue > 255){
                hue = hue % 255;
            }
            // hueIncrement = (255.0f / (lines + (mouseY/5)));
            // each time a line is drawn it is given a hue defined by ( 255(maxhue) / number of lines) therefore each hue is equally spaced
            // how can we update all of the hues by a fixed amount which is determined by the mouse position
            // hue + fixed amount
            // what happens when fixed amount makes the hue over 255?
        }
        popMatrix();
        
        // Update rotation angles
        rotX -= 0.01 * lerpedAvg * 20; 
        rotY += 0.02 * lerpedAvg * 20; 
        camera(cx, cy, cy / tan((float) (PI * 30.0 / 180.0)), cx, cy, 0, 0, 1, 0);

        
    }
}
