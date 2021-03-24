package com.fashion.rest.presnter;

import com.fashion.rest.model.Areas;
import com.fashion.rest.model.Brand;
import com.fashion.rest.model.Categories;
import com.fashion.rest.model.City;
import com.fashion.rest.model.Comment;
import com.fashion.rest.model.Countries;
import com.fashion.rest.model.Flag;
import com.fashion.rest.model.Home;
import com.fashion.rest.model.ItemFavorite;
import com.fashion.rest.model.ItemTest;
import com.fashion.rest.model.Offer;
import com.fashion.rest.model.Post;
import com.fashion.rest.model.Test;
import com.fashion.rest.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    @GET(".")
    Call<List<Categories>> getCategories();

    @GET(".")
    Call <List<Home>> getHome(@Query("_start") int start,
                              @Query("_limit") int end
    );

    @GET(".")
    Call<List<Home>> getHome2();

    @GET("{page}&_limit=4/") //
    Call<List<Home>> listGallery(@Path("page") int page);

    @GET(".")
    Call <List<ItemTest>> getAllItems(@Query("_start") int start,
                                      @Query("_limit") int end
    );

    @GET(".")
    Call<List<Offer>> getOffers(@Query("_start") int start,
                                @Query("_limit") int end
    );

    @GET(".")
    Call<List<Brand>> getBrand();

    @GET(".")
    Call<ItemFavorite> getDetailsItem();

    @POST("profiles")
    Call<UserInfo> insertUser(@Body UserInfo userInfo);

    //change every thing
    @PUT("profiles/{id}")
    Call<UserInfo> replaceUserInfo(@Path("id") String id ,@Body UserInfo userInfo);

    //just update the change
    @PATCH("profiles/{id}")
    Call<UserInfo> updateUserInfo(@Path("id") String id ,@Body UserInfo userInfo);

    @GET(".")
    Call<Integer> getNumberOfItems();
}
