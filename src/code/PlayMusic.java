package code;

// Import necessary Java classes and packages sor audio
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class PlayMusic {

    // Clip object will control audio playback
    Clip clip;

    // playMusic method will play the song from a specified location
    public void playMusic(String location) {

        try {
            // Create a File object with the specified music file location
            File musicPath = new File(location);
            // Check if the file exists
            if (musicPath.exists()) {
                // Get an AudioInputStream from the specified music file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                // Get a Clip object to control audio playback
                clip = AudioSystem.getClip();
                // Open the Clip and load the audio data from the AudioInputStream
                clip.open(audioInput);
                // Start playing the audio
                clip.start();
                // Display a dialog prompting the user to press OK to stop the song
                JOptionPane.showMessageDialog(null, "Press OK to Stop Song");
                // Stop the audio playback when the user presses OK
                clip.stop();
            } else {
                // Print an error message if the file is not found
                System.out.println("Can't Find File");
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
