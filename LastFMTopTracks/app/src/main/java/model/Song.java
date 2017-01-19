package model;

import java.io.Serializable;

/**
 * Created by danieltilley on 19/01/2017.
 */

public class Song implements Serializable {
    private static final long id = 1L;

    private String artistName;
    private String artistImageURL;
    private String artistURL;
    private String tackName;
    private String trackURL;
    private int trackRank;
    private int numListeners;

    public static long getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImageURL() {
        return artistImageURL;
    }

    public void setArtistImageURL(String artistImageURL) {
        this.artistImageURL = artistImageURL;
    }

    public String getArtistURL() {
        return artistURL;
    }

    public void setArtistURL(String artistURL) {
        this.artistURL = artistURL;
    }

    public String getTackName() {
        return tackName;
    }

    public void setTackName(String tackName) {
        this.tackName = tackName;
    }

    public String getTrackURL() {
        return trackURL;
    }

    public void setTrackURL(String trackURL) {
        this.trackURL = trackURL;
    }

    public int getTrackRank() {
        return trackRank;
    }

    public void setTrackRank(int trackRank) {
        this.trackRank = trackRank;
    }

    public int getNumListeners() {
        return numListeners;
    }

    public void setNumListeners(int numListeners) {
        this.numListeners = numListeners;
    }
}
