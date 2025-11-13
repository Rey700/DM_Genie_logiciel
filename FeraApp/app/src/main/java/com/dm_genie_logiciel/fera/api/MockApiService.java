package com.dm_genie_logiciel.fera.api;

import retrofit2.Call;
import com.dm_genie_logiciel.fera.model.User;
import com.dm_genie_logiciel.fera.model.Label;
import com.dm_genie_logiciel.fera.model.Media;
import com.dm_genie_logiciel.fera.model.Comment;
import java.util.List;
import com.dm_genie_logiciel.fera.model.mock.MockData;

public abstract class MockApiService implements ApiService {

    @Override
    public Call<User> getUser(int id) {
        return FakeCall.success(
                MockData.users.stream()
                        .filter(u -> u.getId() == id)
                        .findFirst()
                        .orElse(null)
        );
    }

    @Override
    public Call<List<Media>> getMedia() {
        return FakeCall.success(MockData.medias);
    }

    @Override
    public Call<List<Label>> getLabels() {
        return FakeCall.success(MockData.labels);
    }

    @Override
    public Call<Comment> addComment(Comment comment) {
        return FakeCall.success(comment);
    }
}