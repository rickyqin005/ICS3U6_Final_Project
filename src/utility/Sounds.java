package utility;

import java.util.HashMap;

import utility.providedtemplates.Music;

public class Sounds {
    private static HashMap<String, Music> loadedMusic = new HashMap<String, Music>();
    /**
     * Finds and returns a reference to the Music with the specified path, or creates and returns a new one if it doesn't exist.
     * Since reading audio files is very slow, it is optimal to load an audio file only once.
     * @param path The file path of the Music.
     * @return The found Music or a new Music object.
     */
    public static Music getMusic(String path) {
        Music result = loadedMusic.get(path);
        if(result == null) {
            result = new Music(path);
            loadedMusic.put(path, result);
            System.out.println("Loaded " + path);
        }
        return result;
    }
    public static String getAudioPath(String name) {
        return "src/media/audio/" + name + ".wav";
    }
}
