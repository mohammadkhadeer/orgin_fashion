package com.fashion.rest.presnter;

import com.fashion.rest.model.Areas;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Comment;
import com.fashion.rest.model.Countries;
import com.fashion.rest.model.Post;
import com.fashion.rest.model.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET(".")
    Call<List<Test>> getTest();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComment(@Url String url);

    @GET(".")
    Call<List<Areas>> getAreas();

    @GET(".")
    Call<Integer> getNumberOfCount();

    @GET(".")
    Call<List<Countries>> getCountries();

    @GET(".")
    Call<List<City>> getCities();
}
