package com.blue_b.rest.presnter;

import com.blue_b.rest.model.Areas;
import com.blue_b.rest.model.Brand;
import com.blue_b.rest.model.Categories;
import com.blue_b.rest.model.City;
import com.blue_b.rest.model.Comment;
import com.blue_b.rest.model.Countries;
import com.blue_b.rest.model.Home;
import com.blue_b.rest.model.ItemFavorite;
import com.blue_b.rest.model.ItemTest;
import com.blue_b.rest.model.Offer;
import com.blue_b.rest.model.Post;
import com.blue_b.rest.model.Test;
import com.blue_b.rest.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<Brand> getBrandSubCat();

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


    @POST("Login")
    Call<String> testLoginRetrofit(@Body TestLogin testLogin,@Header("Accept") String accept,@Header("Content-Type") String content);

}
