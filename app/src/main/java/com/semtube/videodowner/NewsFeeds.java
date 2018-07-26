package com.semtube.videodowner;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsFeeds implements Parcelable {

    private String imgURL,feedName, content,vv,url,dr;
    private int rating,PlayPauseId,id;
    private boolean paused;

    protected NewsFeeds(Parcel in) {
        imgURL = in.readString();
        feedName = in.readString();
        content = in.readString();
        vv = in.readString();
        url = in.readString();
        dr = in.readString();
        rating = in.readInt();
        PlayPauseId = in.readInt();
        paused = in.readByte() != 0;
        id = in.readInt();
    }

    public static final Creator<NewsFeeds> CREATOR = new Creator<NewsFeeds>() {
        @Override
        public NewsFeeds createFromParcel(Parcel in) {
            return new NewsFeeds(in);
        }

        @Override
        public NewsFeeds[] newArray(int size) {
            return new NewsFeeds[size];
        }
    };

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public NewsFeeds(String name, String content, String viev, String imgurl, String url, String dr, int rating,int pl,Boolean pa,int id) {
        this.feedName = name;
        this.content = content;
        this.imgURL = imgurl;
        this.rating = rating;
        this.vv=viev;
        this.url=url;
        this.dr=dr;
        this.PlayPauseId=pl;
        this.paused=pa;
        this.id=id;
    }

    public int getPlayPauseId() {
        return PlayPauseId;
    }

    public void setPlayPauseId(int playPauseId) {
        PlayPauseId = playPauseId;
    }

    public String getContent() {
        return content;
    }
    public String getVv() {
        return vv;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getFeedName() {
        return feedName;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }
    public String getUrl() {
        return url;
    }
    public String getDr() {
        return dr;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgURL);
        dest.writeString(feedName);
        dest.writeString(content);
        dest.writeString(vv);
        dest.writeString(url);
        dest.writeString(dr);
        dest.writeInt(rating);
        dest.writeInt(PlayPauseId);
        dest.writeByte((byte) (paused ? 1 : 0));
        dest.writeInt(id);
    }
}