package org.launchcode.codecallers.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table (name = "comments")

public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userID;
    private String username;
    private java.sql.Date date;
    private String comment;

    public Comments(int userID, String username, Date date, String comment) {
        this.userID = userID;
        this.username = username;
        this.date = date;
        this.comment = comment;
    }

    public Comments(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }
}
