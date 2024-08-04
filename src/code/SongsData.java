package code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//We use Serializable because it encryptes the data in txt file
public class SongsData implements Serializable {

    private static int lastAssignedId = 1;
    private int id;
    private String SongName;
    private String artistName;
    private String genre;
    private int year;

    public SongsData(int id,String SongName, String artistName, String genre, int year) {

        this.id = lastAssignedId++;
        this.SongName = SongName;
        this.artistName = artistName;
        this.genre = genre;
        this.year = year;

    }

    public SongsData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artsistName) {
        this.artistName = artsistName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String SongName) {
        this.SongName = SongName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "SongsData{" + "SongName=" + SongName + ", artsistName=" + artistName + ", genre=" + genre + ", year=" + year + '}';
    }
    //this array list of song is static because we have to use in the recommend song to see all the songs in jtable 
    public static ArrayList<SongsData> list = new ArrayList<>();

    public ArrayList<SongsData> listSongs() {
        ArrayList<SongsData> list = new ArrayList<>();
        SongsFileHandling h = new SongsFileHandling();
        h.saveSongsData(list);
        return list;
    }
    //this function is performing search 
    public ArrayList<SongsData> performSearch(ArrayList<SongsData> songs, String searchItem, boolean searchForSong) {
        //Creating ArrayList<SongsData> obj
        ArrayList<SongsData> searchResults = new ArrayList<>();
        //Applying For each loop on ArrayList we get
        for (SongsData song : songs) {
            //Here we call another func matchesAnyItem =>
            if (matchesAnyItem(song.getSongName(), searchItem)
                    || matchesAnyItem(song.getArtistName(), searchItem)
                    || matchesAnyItem(song.getGenre(), searchItem)
                    || matchesAnyItem(String.valueOf(song.getId()), searchItem)
                    || matchesAnyItem(String.valueOf(song.getYear()), searchItem)) {
                searchResults.add(song);
            }
        }

        return searchResults;
    }
    private final SongsFileHandling songHandling = new SongsFileHandling();

    public ArrayList<SongsData> getAllSongs() {
        list = (ArrayList<SongsData>) songHandling.getSongs();
        return list;
    }
    
    //This method gets the 2 parameter of data and searchItem and convert it to lowercase so it can find easily
    private boolean matchesAnyItem(String data, String searchItem) {
        return data.toLowerCase().contains(searchItem.toLowerCase());
    }

    //This method is for Filtering the Songs by genre used in recommened Songs
    public ArrayList<SongsData> filterByGenre(String mood) {
        ArrayList<SongsData> filteredSongs = new ArrayList<>();
        
        //Calling func filterByMood then runs the condition
        if (!mood.isEmpty() && !mood.equalsIgnoreCase("ALL")) {
            filteredSongs.addAll(filterByMood(mood));
        }
        if ((mood.equalsIgnoreCase("ALL")) || (mood.isEmpty())) {
            return list;
        }

        return removeDuplicateFromTheList(filteredSongs);
    }
    
    //This method is for Remove duplication of Songs
    private ArrayList<SongsData> removeDuplicateFromTheList(List<SongsData> list) {
        ArrayList<SongsData> uniqueSongs = new ArrayList<>();
        //For Each loop through the given array of songs then if it not contains the same SongData it will add it
        for (SongsData song : list) {
            if (!uniqueSongs.contains(song)) {
                uniqueSongs.add(song);
            }
        }
        return uniqueSongs;
    }
    
    //This method is for Filtering the Songs by mood used in recommened Songs
    private ArrayList<SongsData> filterByMood(String mood) {
        ArrayList<SongsData> filterByMood = new ArrayList<>();

        //For Each loop through the Static Array up then filter it with mood or genre
        for (SongsData song : list) {
            if (song.getGenre().equalsIgnoreCase(mood)) {
                filterByMood.add(song);
            }
        }

        return filterByMood;
    }
}
