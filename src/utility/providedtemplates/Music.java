package utility.providedtemplates;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
// possible exceptions
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

/**
 * Demonstrates how to load from file and play music
 * @author ICS3U6
 * @version May 2022
 */
public class Music{
    Clip music;
//------------------------------------------------------------------------------         
    public Music(String musicName){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicName));
            this.music = AudioSystem.getClip();
            this.music.open(audioStream);
        } 
        catch (IOException ex){System.out.println("File not found!");}
        catch (UnsupportedAudioFileException ex){System.out.println("Unsupported file!");}   
        catch (LineUnavailableException ex){System.out.println("Audio feed already in use!");}
    }
//------------------------------------------------------------------------------         
    public void start(){
        this.music.start();
    }
    public void loop(){
        this.music.loop(Clip.LOOP_CONTINUOUSLY); 
    }
    public void loop(int count){
        this.music.loop(count);  //loop the playback certain number of times
    }    
}