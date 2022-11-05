package com.application1.githubuserrepo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

public class FollowingFragment extends Fragment {

    Search searchData;
    UserViewModel followingViewModel;
    FollowAdapter followingAdapter;
    RecyclerView ListFollowing;
    ConstraintLayout layoutEmpty;
    ProgressDialog progressDialog;
    String strUsername;

    public FollowingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("start loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data loaded !!");

        ListFollowing = view.findViewById(R.id.rvListFollowing);
        searchData = this.getArguments().getParcelable("modelSearchData");
        if (searchData != null) {
            strUsername = searchData.getLogin();
            Log.d("Username",strUsername);
        }
        followingAdapter = new FollowAdapter(getContext());
        ListFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
        ListFollowing.setAdapter(followingAdapter);
        ListFollowing.setHasFixedSize(true);

        followingViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        followingViewModel.setFollowingUser(strUsername);
        progressDialog.show();

        followingViewModel.getFollowingUser().observe(getViewLifecycleOwner(), new Observer<ArrayList<Follower>>() {
            @Override
            public void onChanged(ArrayList<Follower> followers) {
                if (followers.size() != 0) {
//                    layoutEmpty.setVisibility(View.GONE);
                    followingAdapter.setFollowList(followers);
                } else {
//                    layoutEmpty.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Following not found !", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
        return view;
    }

}