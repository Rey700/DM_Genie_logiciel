package com.dm_genie_logiciel.fera.model;

import java.util.List;

public class Media {
    private int id;
    private int userId;
    private String type;     // "photo" ou "video"
    private String url;
    private List<Integer> labelIds;

    public Media(int id, int userId, String type, String url, List<Integer> labelIds) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.url = url;
        this.labelIds = labelIds;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public String getUrl() { return url; }
    public List<Integer> getLabelIds() { return labelIds; }
}
