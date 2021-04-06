package com.example.consultation.myapplication.helper;


public class Message  {
    private int usersId;
    private int recieverId;
    private String message;
    private String sentAt;
    private String name;

    public Message(int usersId, int recieverId, String message, String sentAt, String name) {
        this.usersId = usersId;
        this.recieverId = recieverId;
        this.message = message;
        this.sentAt = sentAt;
        this.name = name;
    }
    public Message(int usersId, String message, String sentAt, String name) {
        this.usersId = usersId;
      // this.recieverId = recieverId;
        this.message = message;
        this.sentAt = sentAt;
        this.name = name;
    }



    public int getUsersId() {
        return usersId;
    }

    public int getRecieverId() {     return recieverId;}

    public String getMessage() {
        return message;
    }

    public String getSentAt() {
        return sentAt;
    }

    public String getName() {
        return name;
    }
}

