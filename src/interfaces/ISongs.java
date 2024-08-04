package interfaces;

import java.util.ArrayList;
import code.SongsData;

public interface ISongs {

    //This method will get all data of songs 
    ArrayList<SongsData> getAllData();

    //This method will create a file for songs 
    void createSongFile();
    //This method will add new songs 

    void addSong(SongsData song);
    //This method will save all data of songs 

    void saveSongsData(ArrayList<SongsData> song);
    //This method will delete songs 

    SongsData deleteSong(int songId);
    //This method will edit the songs 

    void saveEditedSong(SongsData editedSong);
    //This method will get all songs 

    ArrayList<SongsData> getSongs();
}
