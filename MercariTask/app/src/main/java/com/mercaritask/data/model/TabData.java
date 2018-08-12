package com.mercaritask.data.model;

public class TabData {
    public String id;
    public String name;
    public String status;
    public String photo;
    private String num_likes;
    private String num_comments;
    private String price;

    public String getLikes() {
        return (num_likes != null && !num_likes.isEmpty()) ? num_likes : "0";
    }

    public String getComments() {
        return (num_comments != null && !num_comments.isEmpty()) ? num_comments : "0";
    }

    public String getPrice() {
        return "$ " + ((price != null && !price.isEmpty()) ? price : "0");
    }
}
