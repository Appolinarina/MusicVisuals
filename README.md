# Music Visualiser Project


| Student Name  | Student Number|
| ------------- | ------------- |
| Andrew Carty  | C22488994     |
| Polina Pereyaslavets | C22447674 |
| Aileen Coliban |  C22304291 |
| Fatima Alubaidi | C22305656 |

# Description of the assignment
In this assignment, we have 4 different visualisations made by Java processing. Each visual was designed by one person in the group. Each visualisation responds uniquely to the song "Heartbeat", such as elements pulsating or changing based on different frequency bands. We used the Minim audio library to extract audio from an MP3 file.

# Instructions
1. Run and debug Main.java
2. Press 1 for FA.java, 2 for EC.java, 3 for PP.java and 4 for AC.java
3. Enjoy the visuals :)

# How it works
FA.java (Fatima Alubaidi, C22305656) - This code creates a 3D visualisation with a rotating model (Ned.obj) synced to the music. It loads an audio file, analyses its frequency spectrum to determine rotation speed, and displays a model rotating around the y-axis. The code generates visualisations of orbs and dots that pulsate in response to the audio's waveform. You can pause/resume the audio with the spacebar and rewind it to the beginning by pressing '1'.
<img width="791" alt="Screenshot 2024-04-27 at 12 13 28" src="https://github.com/Appolinarina/MusicVisuals/assets/124153153/8c24a4e9-57df-4dec-8f58-60f6da4b58f3">

AC.java (Andrew Carty, C22488994) - This code recreates conways game of life like from the labs that we did. The difference is that this one loads the audio file and then syncs the changes to whenever there is a beat detected. It then changes the colour of the cells aswell
<img width="996" alt="Screenshot 2024-04-27 at 12 23 28" src="https://github.com/Appolinarina/MusicVisuals/assets/124153153/b07d1412-9127-43c1-9a09-9d11c3e3f33a">


EC.java (Aileen Coliban, C22304291):
  This code combines audio processing with graphical visualization in a Processing environment. It loads and analyzes an audio track using Minim, utilizing FFT data to control a 3D model's rotation and a particle system that reacts dynamically to the music. Users can interact through a    control panel to manage audio playback.
<img width="761" alt="Screenshot 2024-04-27 at 12 15 32" src="https://github.com/Appolinarina/MusicVisuals/assets/124153153/794b0a75-68fb-440b-8237-dabb7dcdee93">
Particle.java (Particle System):
  This class defines particles with attributes such as position, velocity, and acceleration. These particles visually interpret the audio by changing movement and appearance based on the sound's amplitude and frequency, providing a dynamic audio-visual experience.
ControlPanel.java (User Interface):
  The ControlPanel class manages a user interface with playback controls (play, stop, rewind) and a toggleable sidebar for additional settings. User inputs control the sidebar’s visibility, enhancing the application's interactivity.
<img width="764" alt="Screenshot 2024-04-27 at 12 16 18" src="https://github.com/Appolinarina/MusicVisuals/assets/124153153/181a451c-f5dc-460f-8116-09ccb2f4e51d">

PP.java
This code represents a Processing sketch that visualizes the given audio file using a 3D torus render. The audio waveform is displayed in two different manners, one: using lines drawn on all 4 edges of the screen, with the amplitude of each sample determining the length and direction of the lines, and two: the radii of the torus inside and outside. Additionally, the sketch incorporates interactive elements, such as mouse-controlled hue adjustment for the visual representation. Overall, the code gives an immersive experience for the opening of the song due to it's calm nature, the lerping torus accurately characterises the smooth and chill nature of the song.
<img width="1001" alt="Screenshot 2024-04-27 at 12 21 55" src="https://github.com/Appolinarina/MusicVisuals/assets/124153153/20aef252-95df-4b0c-9969-5dd199b5fd8b">

# What I am most proud of in the assignment

Aileen Coliban, C22304291:
I am especially proud of putting the audio analysis with real-time graphical visualisations seamlessly, a highlight being the EC file which synchronises 3D model rotations and particle dynamics with live audio. This required a deep understanding of both Minim for audio processing and Processing for graphics. The Particle class creatively applies vectors to adjust particle behavior based on audio data. The ControlPanel class allows the user to play, pause and rewind the music. This project not only pushed my technical skills but also successfully merged sound with visual art, creating a functional and engaging experience.

Andrew Carty C22488994:
For me I am most proud of the structure and planning i put into the project. I set up plans and diagrams for how we wanted to use main, StageManager, Visual.java etc. I was proud of how i well i got the Game of life to integrate into the music. I didnt make a particularly good Game of Life Lab so I wanted to make this one better. I learned better about the processing library and the minim library and I was super happy with how much I learned.
:3

Fatima ALubaidi C22305656:
I'm really proud of how this project combines sound and visuals. I'm proud of how the  3D model, Ned, rotates with the music's beat, which makes it more engaging to watch. The rotation speed depends on how loud different parts of the music are, so it feels like the visuals are dancing to the music. Also, the colourful orbs and dots move along with the music, making everything look more interesting. This project shows how well I can mix sound and pictures to create something cool to experience. I'm also proud of how much new stuff I learnt, such as using the different libraries. 

Polina Pereyaslavets C22447674
What I am proud of is implementing the torus shape and bringing it to life. I had an idea in my head of a rotating torus shape with changing colors and the size depending on the amplitude of the music. I really like how the torus takes on many 'forms' depending on the amplitude - when the music is quiet, the torus is very thin, and when the beat drops, the radii of the torus start to overlap due to the high amplitude of the song, where the torus shape takes on a whole new form, which I was very proud of, as it takes up alot of the screen and feels very stimulating and immersive. I added extra features at the sides from one of the labs to complete the visual. I really enjoyed implementing this, and constantly seeing the progress of updating new features of the visual to see what it could finally become C:





