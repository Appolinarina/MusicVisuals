# Music Visualiser Project

Name:		Andrew Carty,	Polina Pereyaslavets,	Aileen Coliban,		Fatima Alubaidi

Student Number: C22488994,	C22447674,		C22304291,		C22305656

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment


# Instructions


# How it works
FA.java (Fatima Alubaidi, C22305656) - This code creates a 3D visualisation with a rotating model (Ned.obj) synced to the music. It loads an audio file, analyses its frequency spectrum to determine rotation speed, and displays a model rotating around the y-axis. The code generates visualisations of orbs and dots that pulsate in response to the audio's waveform. You can pause/resume the audio with the spacebar and rewind it to the beginning by pressing '1'.

ac.java (Andrew Carty, C22488994) - This code recreates conways game of life like from the labs that we did. The difference is that this one loads the audio file and then syncs the changes to whenever there is a beat detected. It then changes the colour of the cells aswell

EC.java (Aileen Coliban, C22304291):
  This code combines audio processing with graphical visualization in a Processing environment. It loads and analyzes an audio track using Minim, utilizing FFT data to control a 3D model's rotation and a particle system that reacts dynamically to the music. Users can interact through a    control panel to manage audio playback.
Particle.java (Particle System):
  This class defines particles with attributes such as position, velocity, and acceleration. These particles visually interpret the audio by changing movement and appearance based on the sound's amplitude and frequency, providing a dynamic audio-visual experience.
ControlPanel.java (User Interface):
  The ControlPanel class manages a user interface with playback controls (play, stop, rewind) and a toggleable sidebar for additional settings. User inputs control the sidebar’s visibility, enhancing the application's interactivity.


PP.java
This code represents a Processing sketch that visualizes the given audio file using a 3D torus render. The audio waveform is displayed in two different manners, one: using lines drawn on all 4 edges of the screen, with the amplitude of each sample determining the length and direction of the lines, and two: the radii of the torus inside and outside. Additionally, the sketch incorporates interactive elements, such as mouse-controlled hue adjustment for the visual representation. Overall, the code gives an immersive experience for the opening of the song due to it's calm nature, the lerping torus accurately characterises the smooth and chill nature of the song.

# What I am most proud of in the assignment

Aileen Coliban, C22304291:
I am especially proud of putting the audio analysis with real-time graphical visualisations seamlessly, a highlight being the EC file which synchronises 3D model rotations and particle dynamics with live audio. This required a deep understanding of both Minim for audio processing and Processing for graphics. The Particle class creatively applies vectors to adjust particle behavior based on audio data. The ControlPanel class allows the user to play, pause and rewind the music. This project not only pushed my technical skills but also successfully merged sound with visual art, creating a functional and engaging experience.

Andrew Carty C22488994:
For me I am most proud of the structure and planning i put into the project. I set up plans and diagrams for how we wanted to use main, StageManager, Visual.java etc. I was proud of how i well i got the Game of life to integrate into the music. I didnt make a particularly good Game of Life Lab so I wanted to make this one better. I learned better about the processing library and the minim library and I was super happy with how much I learned.
:3

Fatima ALubaidi C22305656:
I'm really proud of how this project combines sound and visuals. I'm proud of how the  3D model, Ned, rotates with the music's beat, which makes it more engaging to watch. The rotation speed depends on how loud different parts of the music are, so it feels like the visuals are dancing to the music. Also, the colourful orbs and dots move along with the music, making everything look more interesting. This project shows how well I can mix sound and pictures to create something cool to experience. I'm also proud of how much new stuff I learnt, such as using the different libraries. 






