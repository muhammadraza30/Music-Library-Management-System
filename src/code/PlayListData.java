package code;

import java.io.Serializable;

public class PlayListData implements Serializable{
private String playListName;    

    public PlayListData(String playListName) {
        this.playListName = playListName;
    }

    public PlayListData() {
    }
    
    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    @Override
    public String toString() {
        return "PlayListData{" + "playListName=" + playListName + '}';
    }

}