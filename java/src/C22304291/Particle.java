package C22304291;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

class Particle {
    PVector position;
    PVector velocity;
    PVector acceleration;
    float lifespan;
    float radius;
    float spread;
    PApplet parent;


    Particle(PVector origin, PApplet parent, float spread) {
        this.parent = parent;
        acceleration = new PVector(0, 0.05f);
        // Randomize initial position within a defined spread around the origin
        position = new PVector(
            origin.x + parent.random(-spread, spread),
            origin.y + parent.random(-spread, spread)
        );
        // Increase variability in initial velocity
        velocity = new PVector(parent.random(-2, 2), parent.random(-2, 0));
        lifespan = 255;
        radius = 8;
    }

    void run() {
        update();
        display();
        checkEdges();
    }

    void update() {
        // Apply a random acceleration occasionally to spread particles out
        if (parent.frameCount % 60 == 0) { // Every second, randomize the acceleration slightly
            acceleration.add(new PVector(parent.random(-0.05f, 0.05f), parent.random(-0.05f, 0.05f)));
        }
        velocity.add(acceleration);
        position.add(velocity);
        lifespan -= 1.0;
    }

    void display() {
        parent.stroke(255, lifespan);
        parent.noFill();
        parent.ellipse(position.x, position.y, radius * 0.25f, radius * 0.25f); // Draw using radius
    }

    void checkEdges() {
        if (position.x <= radius || position.x >= parent.width - radius) {
            velocity.x *= -1; // Reverse direction on x-axis
        }
        if (position.y <= radius || position.y >= parent.height - radius) {
            velocity.y *= -1; // Reverse direction on y-axis
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
        particles.add(new Particle(origin, parent, spread));
    }

    void run() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.run();
            repelParticles(p);
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    void repelParticles(Particle particle) {
        // Simple repulsion logic
        for (Particle other : particles) {
            if (other != particle) {
                PVector direction = PVector.sub(particle.position, other.position);
                float distance = direction.mag();
                if (distance < 20) { // arbitrary repulsion distance
                    direction.setMag(0.5f / distance); // repulsion force inversely proportional to distance
                    particle.velocity.add(direction);
                }
            }
        }
    }
}
