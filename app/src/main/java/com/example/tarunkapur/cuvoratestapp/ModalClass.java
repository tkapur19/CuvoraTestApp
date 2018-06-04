package com.example.tarunkapur.cuvoratestapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

// Modal Class for getting list of objects from API

public class ModalClass {



    List<info> infoList;

    public List<info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<info> infoList) {
        this.infoList = infoList;
    }


    String OwnerName;


String imageURL;
String shareText;

    public ModalClass() {

    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }




    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }
}



