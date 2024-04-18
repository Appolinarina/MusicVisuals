package C22304291;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

class Particle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    PApplet parent; // Reference to PApplet to use Processing functions

    Particle(PVector l, PApplet parent) {
        this.parent = parent;
        acceleration = new PVector(0, 0.05f);
        velocity = new PVector(parent.random(-1, 1), parent.random(-2, 0)); // Use parent to access random
        position = l.copy();
        lifespan = 255;
    }

    void run() {
        update();
        display();
    }

    void update() {
        velocity.add(acceleration);
        position.add(velocity);
        lifespan -= 1.0;
    }

    void display() {
        parent.stroke(255, lifespan);
        parent.fill(255, lifespan);
        parent.ellipse(position.x, position.y, 8, 8);
    }

    boolean isDead() {
        return lifespan < 0;
    }
}

class ParticleSystem {
    ArrayList<Particle> particles;
    PVector origin;
    PApplet parent; // Reference to PApplet for drawing and using Processing methods

    ParticleSystem(PVector position, PApplet parent) {
        this.origin = position.copy();
        this.particles = new ArrayList<Particle>();
        this.parent = parent;
    }

    void addParticle() {
        particles.add(new Particle(origin, parent));
    }

    void run() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.run();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }
}
