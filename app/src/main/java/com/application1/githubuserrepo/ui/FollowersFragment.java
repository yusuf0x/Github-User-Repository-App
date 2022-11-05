package com.application1.githubuserrepo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.viewmodel.UserViewModel;
import com.application1.githubuserrepo.adapter.FollowAdapter;
import com.application1.githubuserrepo.models.Follower;
import com.application1.githubuserrepo.models.Search;

import java.util.ArrayList;


public class FollowersFragment extends Fragment {

    Search searchData;
    UserViewModel followersViewModel;
    FollowAdapter followAdapter;
    RecyclerView ListFollowers;
    ConstraintLayout layoutEmpty;
    ProgressDialog progressDialog;
    String strUsername;

    public FollowersFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(" Start loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data loaded ");

        ListFollowers = view.findViewById(R.id.rvListFollowers);
        searchData  = this.getArguments().getParcelable("modelSearchData");
        if (searchData != null) {
            strUsername = searchData.getLogin();
        }
        followAdapter = new FollowAdapter(getContext());
        ListFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFollowers.setAdapter(followAdapter);
        ListFollowers.setHasFixedSize(true);
        followersViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        followersViewModel.setFollowerUser(strUsername);
        progressDialog.show();
        followersViewModel.getFollowersUser().observe(getViewLifecycleOwner(), new Observer<ArrayList<Follower>>() {
            @Override
            public void onChanged(ArrayList<Follower> followers) {
                if (followers.size()!=0){
                    followAdapter.setFollowList(followers);
                }else{
                    Toast.makeText(getContext(), "Followers not found !", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
        return view;
    }
}