package com.application1.githubuserrepo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.application1.githubuserrepo.models.Follower;
import com.application1.githubuserrepo.models.ModelSearch;
import com.application1.githubuserrepo.models.Repo;
import com.application1.githubuserrepo.models.Search;
import com.application1.githubuserrepo.models.User;
import com.application1.githubuserrepo.webservice.ApiClient;
import com.application1.githubuserrepo.webservice.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Search>> SearchMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Follower>> modelFollowersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Follower>> modelFollowingMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<User> modelUserMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Repo>> modelRepoMutableLiveData = new MutableLiveData<>();
    public static String strApiKey ="";

    public  void setSearchUser(String query){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelSearch> call = apiInterface.searchUser(strApiKey,query);
        call.enqueue(new Callback<ModelSearch>() {
            @Override
            public void onResponse(Call<ModelSearch> call, Response<ModelSearch> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<Search> items = new ArrayList(response.body().getModelSearchData());
                    SearchMutableLiveData.setValue(items);
                }
            }
            @Override
            public void onFailure(Call<ModelSearch> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public  void setUserDetail(String username){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.detailUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Log.e("response", response.toString());
                }else if(response.body()!=null) {
                    modelUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public  void setFollowerUser(String username){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Follower>> call = apiInterface.followersUser(strApiKey, username);
        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowersMutableLiveData.setValue((ArrayList<Follower>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
    public void setFollowingUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Follower>> call = apiService.followingUser(strApiKey, strUsername);
        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowingMutableLiveData.setValue((ArrayList<Follower>) response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
    public  void setRepoUser(String username){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Repo>> call = apiService.reposUser(strApiKey,username);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelRepoMutableLiveData.setValue((ArrayList<Repo>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
    public LiveData<ArrayList<Search>> getResultList() {
        return SearchMutableLiveData;
    }

    public LiveData<User> getUserList() {
        return modelUserMutableLiveData;
    }

    public LiveData<ArrayList<Follower>> getFollowersUser() {
        return modelFollowersMutableLiveData;
    }

    public LiveData<ArrayList<Follower>> getFollowingUser() {
        return modelFollowingMutableLiveData;
    }
    public LiveData<ArrayList<Repo>> getReposUser() {
        return modelRepoMutableLiveData;
    }

}
