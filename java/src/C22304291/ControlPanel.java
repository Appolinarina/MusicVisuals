package C22304291;
import processing.core.PApplet;

public class ControlPanel {
    PApplet parent;
    Button playButton;
    Button stopButton;
    boolean isSidebarVisible;
    float sidebarX;  // X position of the sidebar
    float sidebarWidth;
    Button arrowButton;

    public ControlPanel(PApplet p) {
        this.parent = p;
        sidebarWidth = 200; // Width of the sidebar
        sidebarX = -sidebarWidth; // Start hidden
        isSidebarVisible = false;
        playButton = new Button(parent, "Play", 50, 500, 100, 40);
        stopButton = new Button(parent, "Stop", 50, 550, 100, 40);
        arrowButton = new Button(parent, ">", 0, 50, 20, 40);
    }

    public void display() {
        update();  // Handles showing or hiding the sidebar
    
        // Draw sidebar
        parent.fill(180);
        parent.rect(sidebarX, 0, sidebarWidth, parent.height);
    
        // Check if the sidebar is fully visible
        if (sidebarX == 0) {  // Ensure this condition checks for full visibility
            playButton.x = (int) sidebarX + 50;
            stopButton.x = (int) sidebarX + 50;
            playButton.display();
            stopButton.display();
        }
    
        // Arrow button should always be displayed
        arrowButton.display();
    }

    private void update() {
        float speed = 10;  // Adjust speed for smoother transition
        if (isSidebarVisible && sidebarX < 0) {
            sidebarX += speed;
            if (sidebarX > 0) sidebarX = 0;
        } else if (!isSidebarVisible && sidebarX > -sidebarWidth) {
            sidebarX -= speed;
            if (sidebarX < -sidebarWidth) sidebarX = -sidebarWidth;
        }
    }
    

    public void toggleSidebar() {
        isSidebarVisible = !isSidebarVisible;
        arrowButton.label = isSidebarVisible ? "<" : ">";
    }

    public void checkMousePressed() {
        System.out.println("Mouse X: " + parent.mouseX + " Mouse Y: " + parent.mouseY);  // Debug mouse position
    
        if (arrowButton.over(parent.mouseX, parent.mouseY)) {
            System.out.println("Toggling sidebar");
            toggleSidebar();
        } else if (isSidebarVisible) {
            if (playButton.over(parent.mouseX, parent.mouseY)) {
                System.out.println("Play button pressed");
                ((EC)parent).playMusic();
            } else if (stopButton.over(parent.mouseX, parent.mouseY)) {
                System.out.println("Stop button pressed");
                ((EC)parent).stopMusic();
            }
        }
    }
    

    class Button {
        PApplet parent;
        String label;
        int x, y, w, h;

        Button(PApplet parent, String label, int x, int y, int w, int h) {
            this.parent = parent;
            this.label = label;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        void display() {
            parent.fill(120);
            parent.rect(x, y, w, h);
            parent.fill(0);
            parent.text(label, x + 20, y + 25);
        }

        boolean over(int mx, int my) {
            return mx > x && mx < x + w && my > y && my < y + h;
        }
    }
}
