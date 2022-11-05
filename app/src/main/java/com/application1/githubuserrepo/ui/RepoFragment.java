package com.application1.githubuserrepo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.application1.githubuserrepo.adapter.RepoAdapter;
import com.application1.githubuserrepo.models.Repo;
import com.application1.githubuserrepo.models.Search;
import com.application1.githubuserrepo.viewmodel.UserViewModel;

import java.util.ArrayList;


public class RepoFragment extends Fragment {

    Search searchData;
    UserViewModel reposViewModel;
    RecyclerView ListRepos;
    String strUsername;
    ProgressDialog progressDialog;
    RepoAdapter repoAdapter;
    public RepoFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_repository, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(" Start loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("data loaded ");

        ListRepos = view.findViewById(R.id.ListRepos);
        searchData  = this.getArguments().getParcelable("modelSearchData");
        if (searchData != null) {
            strUsername = searchData.getLogin();
        }
        repoAdapter = new RepoAdapter(getContext());
        ListRepos.setLayoutManager(new LinearLayoutManager(getContext()));
        ListRepos.setAdapter(repoAdapter);
        ListRepos.setHasFixedSize(true);

        reposViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        reposViewModel.setRepoUser(strUsername);
        progressDialog.show();
        reposViewModel.getReposUser().observe(getViewLifecycleOwner(), new Observer<ArrayList<Repo>>() {
            @Override
            public void onChanged(ArrayList<Repo> repos) {
                if (repos.size()!=0){
                    repoAdapter.setReposUserList(repos);
                }else{
                    Toast.makeText(getContext(), "Repos not found !", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });


        return  view;
    }
}