package com.dm_genie_logiciel.fera.model;

public class Comment {
    private int id;
    private int userId;
    private int mediaId;
    private String message;
    private long timestamp;

    public Comment(int id, int userId, int mediaId, String message, long timestamp) {
        this.id = id;
        this.userId = userId;
        this.mediaId = mediaId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getMediaId() { return mediaId; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }
}