package code;

import gui.PanelLoginAndRegister;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PlayListFileHandling {

    File file;
    File nfile;
    String playListFilePath;
    String txtFilePath;
    String user;
    String userPlay;
    String defaultPath;

    public PlayListFileHandling() {
        user = PanelLoginAndRegister.userN +"'s Playlists";
        defaultPath = String.valueOf(Paths.get("src", "data"));
        playListFilePath = defaultPath + File.separator + user;
    }
    //This Method will create folder
    public void createPlayListFile() {
//        user = pathName;
//        playListFilePath = defaultPath + File.separator + user;
        file = new File(playListFilePath);
        if (!file.exists()) {
            try {
                file.mkdir();
                String result = file.createNewFile() ? "File Created" : "Issue";
                System.out.println(result);
            } catch (Exception e) {

            }
        }
    }

    //This Method will delete folder
    public void deletePlayListFile(String pathName) {
//        user = pathName;
        playListFilePath = defaultPath + File.separator + pathName;
        file = new File(playListFilePath);
        if (file.exists()) {
            try {
                String[] options = {"Yes", "No"};
                int selection = JOptionPane.showOptionDialog(
                        null,
                        "Do you want to Delete Folder",
                        "Delete Folder",
                        0, // Default option (rock)
                        3, // Information icon
                        null,
                        options,
                        options[0]
                );
                if(selection == 0){
                file.delete();
                JOptionPane.showMessageDialog(null, "Folder is deleted!","Response",3);
                }
            } catch (Exception e) {

            }
        }
    }

    //This Method will create txt file for playlist
    public void newPlayListFile(String pathName) {
        userPlay = pathName;
        txtFilePath = playListFilePath + File.separator + (userPlay + ".txt");
        nfile = new File(txtFilePath);
        if (!nfile.exists()) {
            try {
                String result = nfile.createNewFile() ? "File Created" : "Issue";
                System.out.println(result);
            } catch (Exception e) {

            }

        }
    }

    public void savePlayListData(ArrayList<PlayListData> playList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(txtFilePath))) {
            out.writeObject(playList);
            System.out.println("Object added");
        } catch (Exception e) {
//            System.out.println(e);
        }

    }
//
//    public ArrayList<PlayListData> getPlayList() {
//
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playListFilePath))) {
//            Object obj = in.readObject();
//            ArrayList<PlayListData> playList = (ArrayList<PlayListData>) obj;
//
//            System.out.println("Object rec");
//            return playList;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return new ArrayList<>();
//
//    }

    public ArrayList<PlayListData> getPlayList() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playListFilePath))) {
            Object obj = in.readObject();
            if (obj instanceof ArrayList) {
                ArrayList<PlayListData> playList = (ArrayList<PlayListData>) obj;
                System.out.println("Object received");
                return playList;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    public void deletePlaylist(String playlistName) {
        ArrayList<PlayListData> playlists = getPlayList();
        playlists.removeIf(playlist -> playlist.getPlayListName().equals(playlistName));
        savePlayListData(playlists);
    }

    public static void main(String[] args) {
        PlayListFileHandling h = new PlayListFileHandling();
        ArrayList<PlayListData> playListstore = new ArrayList<>();
        h.getPlayList();
        h.savePlayListData(playListstore);
        playListstore.clear();
    }
}
