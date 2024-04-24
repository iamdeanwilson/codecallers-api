package org.launchcode.codecallers.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "quizHistory")

public class QuizHistory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userID;
    private java.sql.Date date;
    private String topic;
    private String difficulty;
    private int score;

    public QuizHistory(int userID, Date date, String topic, String difficulty, int score) {
        this.userID = userID;
        this.date = date;
        this.topic = topic;
        this.difficulty = difficulty;
        this.score = score;
    }

    public QuizHistory(){}

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

}
