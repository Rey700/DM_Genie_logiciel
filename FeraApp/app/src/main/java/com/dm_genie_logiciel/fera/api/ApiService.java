package com.dm_genie_logiciel.fera.api;

import com.dm_genie_logiciel.fera.model.Comment;
import com.dm_genie_logiciel.fera.model.Label;
import com.dm_genie_logiciel.fera.model.Media;
import com.dm_genie_logiciel.fera.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface ApiService {

    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);

    @GET("media")
    Call<List<Media>> getMedia();

    @GET("labels")
    Call<List<Label>> getLabels();

    @POST("comment")
    Call<Comment> addComment(@Body Comment comment);
}