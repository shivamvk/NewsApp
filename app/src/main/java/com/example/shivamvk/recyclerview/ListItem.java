package com.example.shivamvk.recyclerview;

public class ListItem {

    private String mHeading;
    private String mDescription;
    private String mImageURL;
    private String mDetailURL;

    public ListItem(String mHeading, String mDescription, String mImageURL, String mDetailURL) {
        this.mHeading = mHeading;
        this.mDescription = mDescription;
        this.mImageURL = mImageURL;
        this.mDetailURL = mDetailURL;
    }

    public String getmHeading() {
        return mHeading;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmDetailURL() {
        return mDetailURL;
    }
}
