package com.application1.githubuserrepo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.adapter.FavoriteAdapter;
import com.application1.githubuserrepo.database.FavoriteHelper;
import com.application1.githubuserrepo.models.User;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    ArrayList<User> modelUserArrayList = new ArrayList<>();
    private FavoriteHelper favoriteHelper;
    private FavoriteAdapter favoriteAdapter;
    Toolbar toolbar;
//    ConstraintLayout layoutEmpty;
    RecyclerView rvListFavoriteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        toolbar = findViewById(R.id.toolbar);
//        layoutEmpty = findViewById(R.id.layoutEmpty);
        rvListFavoriteUser = findViewById(R.id.rvListFavoriteUser);

        favoriteHelper = FavoriteHelper.getFavoriteHelper(FavoriteActivity.this);
        favoriteHelper.open();

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favoriteAdapter = new FavoriteAdapter();
        rvListFavoriteUser.setLayoutManager(new LinearLayoutManager(this));
        rvListFavoriteUser.setAdapter(favoriteAdapter);
        rvListFavoriteUser.setHasFixedSize(true);

        getDataFavorite();
    }

    //method show data
    private void getDataFavorite() {
        if (modelUserArrayList.size() == 0) {
//            layoutEmpty.setVisibility(View.VISIBLE);
            rvListFavoriteUser.setVisibility(View.GONE);
        } else {
//            layoutEmpty.setVisibility(View.GONE);
            rvListFavoriteUser.setVisibility(View.VISIBLE);
            favoriteAdapter.setFavoriteUserList(modelUserArrayList);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getDataFavorite();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}