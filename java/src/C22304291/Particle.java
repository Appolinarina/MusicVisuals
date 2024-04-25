package C22304291;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

class Particle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    PApplet parent;
    float radius;

    // Constructor
    Particle(PVector start, PApplet p, float spread) {
        parent = p;
        position = new PVector(parent.random(parent.width), 0);  // Start at random x positions at the top
        velocity = new PVector(0, parent.random(1, 2));  // Downward velocity
        acceleration = new PVector(0, 0.02f);  // Simulate slight gravity
        radius = parent.random(4, 8); // Varying sizes for a more natural look
        lifespan = 150;
    }

    void update() {
        velocity.add(acceleration);
        position.add(velocity);
        //reduces lifespan by each frame -- used to track when the particle dies
        lifespan -= 0.5;
    }

    void display(int color) {
        parent.fill(color, lifespan); // Use the passed color with transparency
        parent.noStroke();
        parent.ellipse(position.x, position.y, radius, radius);
    }

    void checkEdges() {
        if (position.y > parent.height) {
            position.y = 0;  // Reset to the top
            position.x = parent.random(parent.width);  // Reset to a random x position at the top
        }
    }

    boolean isDead() {
        return lifespan < 0;
    }
}

class ParticleSystem {
    ArrayList<Particle> particles;
    PVector origin;
    PApplet parent;
    float spread;

    ParticleSystem(PVector position, PApplet parent, float spread) {
        this.origin = position.copy();
        this.parent = parent;
        this.particles = new ArrayList<Particle>();
        this.spread = spread;
    }

    void addParticle() {
        // Adds new particles
        particles.add(new Particle(origin, parent, spread));
    }

    void run(int color) {
        //Iterates backwards through the particle list to update, display, and check the edges of each particle.
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            p.display(color); // Pass color to each particle
            p.checkEdges();
            //check if particle is dead, removing it from the list
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }
}
