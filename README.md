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

ac.java (Andrew Carty, C22488994) - This code recreates conways game of life like from the labs that we did. The difference is that this one loads the audio file and then syncs the changes to whenever there is a beat detected. it then changes the colour of the cells aswell
# What I am most proud of in the assignment

EC.java (Aileen Coliban, C22304291):
This code combines audio processing with graphical visualization in a Processing environment. It loads and analyzes an audio track using Minim, utilizing FFT data to control a 3D model's rotation and a particle system that reacts dynamically to the music. Users can interact through a control panel to manage audio playback.
Particle.java (Particle System):
This class defines particles with attributes such as position, velocity, and acceleration. These particles visually interpret the audio by changing movement and appearance based on the sound's amplitude and frequency, providing a dynamic audio-visual experience.
ControlPanel.java (User Interface):
The ControlPanel class manages a user interface with playback controls (play, stop, rewind) and a toggleable sidebar for additional settings. User inputs control the sidebar’s visibility, enhancing the application's interactivity.

:3
