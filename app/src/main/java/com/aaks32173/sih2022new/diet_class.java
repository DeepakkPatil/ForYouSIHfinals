package com.aaks32173.sih2022new;

public class diet_class {

    private String title,desc,image;

    public diet_class(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public diet_class(String image) {
        this.image = image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
