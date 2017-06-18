package com.mile.android.pojo;

/**
 * Created by yacovyitzhak on 16/06/2017.
 */

public class Contact {
    public String phoneNumber;
    public String displayName;
    public String id;
    public boolean selected;



    public String imageUri;

    public Contact(String phoneNumber, String displayName, String id, String imageUri) {
        this.phoneNumber = phoneNumber;
        this.displayName = displayName;
        this.id = id;
        this.imageUri = imageUri;
    }

    public Contact(String phoneNumber, String displayName, String id, String imageUri,boolean selected) {
        this( phoneNumber,  displayName,  id,   imageUri);
        this.selected = selected;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
