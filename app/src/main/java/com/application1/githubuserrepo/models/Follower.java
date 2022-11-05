package com.application1.githubuserrepo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Follower implements  Parcelable{

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(avatarUrl);
        parcel.writeString(login);
        parcel.writeString(htmlUrl);
    }

    protected Follower(Parcel in) {
        avatarUrl = in.readString();
        login = in.readString();
        htmlUrl = in.readString();
    }

    public static final Parcelable.Creator<Follower> CREATOR = new Parcelable.Creator<Follower>() {
        @Override
        public Follower createFromParcel(Parcel in) {
            return new Follower(in);
        }

        @Override
        public Follower[] newArray(int size) {
            return new Follower[size];
        }
    };

}
