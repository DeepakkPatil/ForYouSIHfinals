package com.aaks32173.sih2022new.ui;

public class Post {
    private String title;
    private String desc;
    private String imageUrl;
    private String likes;
//    private String email;
    public Post(){

    }



    public Post(String title,String desc, String image,String likes){
        this.title=title;
        this.desc=desc;
        this.imageUrl=image;
        this.likes=likes;
    }
//
    public String getImageUrl() {
        return imageUrl;
    }

    public Post(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getLikes(){return likes ;}

//    public String getEmail() {
//        return email;
//    }
//
//    public String setEmail(){return email ;}

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
