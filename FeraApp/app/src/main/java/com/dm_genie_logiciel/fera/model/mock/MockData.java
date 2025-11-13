package com.dm_genie_logiciel.fera.model.mock;

import com.dm_genie_logiciel.fera.model.Comment;
import com.dm_genie_logiciel.fera.model.Label;
import com.dm_genie_logiciel.fera.model.Media;
import com.dm_genie_logiciel.fera.model.User;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<User> users = Arrays.asList(
            new User(1, "Nexor", "nexor@email.com", null),
            new User(2, "Alice98", "alice@gmail.com", null)
    );

    public static List<Label> labels = Arrays.asList(
            new Label(1, "Voyage"),
            new Label(2, "Sport"),
            new Label(3, "Food")
    );

    public static List<Media> medias = Arrays.asList(
            new Media(1, 1, "photo", "url/photo1.jpg", Arrays.asList(1, 3)),
            new Media(2, 1, "video", "url/video1.mp4", Arrays.asList(2)),
            new Media(3, 2, "photo", "url/photo2.jpg", Arrays.asList(1))
    );

    public static List<Comment> comments = Arrays.asList(
            new Comment(1, 2, 1, "Super photo !", System.currentTimeMillis()),
            new Comment(2, 1, 3, "J'adore !", System.currentTimeMillis())
    );
}