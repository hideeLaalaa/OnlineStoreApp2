package com.example.onlinestoreapp2.Model;

public class Data {

    private String image;
    private String title;
    private String id;
    private String description;

    public static Data[] data = new Data[]{

        new Data()


    };

    public Data(){

    }

    public Data(String image, String title, String id, String description) {
        this.image = image;
        this.title = title;
        this.id = id;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
