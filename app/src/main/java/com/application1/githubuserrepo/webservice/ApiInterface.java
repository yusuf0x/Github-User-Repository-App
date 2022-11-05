package com.application1.githubuserrepo.webservice;

import com.application1.githubuserrepo.models.Follower;
import com.application1.githubuserrepo.models.ModelSearch;
import com.application1.githubuserrepo.models.Repo;
import com.application1.githubuserrepo.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("users/{username}")
    Call<User> detailUser(@Path("username") String username);

    @GET("/search/users")
    Call<ModelSearch> searchUser(@Header("Authorization") String authorization,
                                 @Query("q") String username);

    @GET("users/{username}/followers")
    Call<List<Follower>> followersUser(@Header("Authorization") String authorization,
                                       @Path("username") String username);

    @GET("users/{username}/following")
    Call<List<Follower>> followingUser(@Header("Authorization") String authorization,
                                          @Path("username") String username);
    @GET("users/{username}/repos")
    Call<List<Repo>> reposUser(@Header("Authorization") String authorization,
                           @Path("username") String username);

}
