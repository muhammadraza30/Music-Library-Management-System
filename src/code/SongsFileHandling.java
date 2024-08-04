package code;

import interfaces.ISongs;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SongsFileHandling implements ISongs, Serializable {

    //Creating File obj
    File file;
    String defaultPath;
    String songsFilePath;

    //SongsFileHandling Constructor
    public SongsFileHandling() {
        defaultPath = String.valueOf(Paths.get("src", "data"));
        songsFilePath = defaultPath + File.separator + "songs.txt";
    }

    //This method will get all data of the songs
    @Override
    public ArrayList<SongsData> getAllData() {
        ArrayList<SongsData> songs = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(songsFilePath))) {
            Object obj = in.readObject();
            songs = (ArrayList<SongsData>) obj;

        } catch (Exception e) {
            System.out.println(e);
        }
        return songs;
    }

    //Function createSongFile
    @Override
    public void createSongFile() {
        try {
            //If file don't exist then it will create file
            file = new File(songsFilePath);
            if (!file.exists()) {
                String result = file.createNewFile() ? "File Created" : "Issue";
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addSong(SongsData song) {
        ArrayList<SongsData> songs = getSongs();

        // Find the maximum ID in the existing list
        int maxId = 0;
        for (SongsData existingSong : songs) {
            if (existingSong.getId() > maxId) {
                maxId = existingSong.getId();
            }
        }

        // Assign a new ID to the new song
        song.setId(maxId + 1);

        // Add the new song to the list
        songs.add(song);

        // Save the updated list of songs
        saveSongsData(songs);
    }

    //Saving data in txt file of Song and getting an ArrayList<SongsData>
    @Override
    public void saveSongsData(ArrayList<SongsData> song) {
        //Call the createSongFile function which storing data in the file

        createSongFile();
        // ObjectOutputStream is to write data in file

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(songsFilePath))) {
            out.writeObject(song);
            System.out.println("Object added");
            getSongs();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    //This function is used for deleteSong
    @Override
    public SongsData deleteSong(int songId) {
        //here the id will get as a parameter
        ArrayList<SongsData> songs = getSongs();
        SongsData deletedSong = null;

        for (Iterator<SongsData> iterator = songs.iterator(); iterator.hasNext();) {
            SongsData song = iterator.next();
            if (song.getId() == songId) {
                // Save the deleted song and remove it from the list
                deletedSong = song;
                iterator.remove();
            } else if (song.getId() > songId) {
                // Decrease the ID of songs after the deleted one
                song.setId(song.getId() - 1);
            }
        }

        // Save the updated list of songs
        saveSongsData(songs);

        return deletedSong;
    }

    //This method is for Edit Songs Data
    @Override
    public void saveEditedSong(SongsData editedSong) {
        ArrayList<SongsData> retrievedSongs = getSongs();
        
        //Using for loop and run in the ArrayList<SongsData> and get the items
        for (int i = 0; i < retrievedSongs.size(); i++) {
            SongsData song = retrievedSongs.get(i);
            if (Objects.equals(song.getId(), editedSong.getId())) {
                retrievedSongs.set(i, editedSong);
                break;
            }
        }

        saveSongsData(retrievedSongs);
    }
    
     //Getting data from txt file of User and use it anywhere

    @Override
    public ArrayList<SongsData> getSongs() {
        // ObjectInputStream is to read data from txt file

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(songsFilePath))) {
            Object obj = in.readObject();
            ArrayList<SongsData> song = (ArrayList<SongsData>) obj;
            System.out.println("Object rec");
            return song;
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ArrayList<>();

    }

}
