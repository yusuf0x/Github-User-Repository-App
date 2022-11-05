package com.application1.githubuserrepo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.viewmodel.UserViewModel;
import com.application1.githubuserrepo.adapter.ViewPagerAdapter;
import com.application1.githubuserrepo.models.Search;
import com.application1.githubuserrepo.models.User;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    public static final String DETAIL_USER = "DETAIL_USER";
//    private FavoriteHelper favoriteHelper;
    ArrayList<User> modelUserArrayList = new ArrayList<>();
    UserViewModel userViewModel;
    Search SearchData;
    String strUsername;
    ImageView imageUser;
    TextView tvUsername, tvBio, tvFollowers, tvFollowing, tvRepository;
    TabLayout tabsLayout;
    Toolbar toolbar;
    ViewPager viewPager;
//    MaterialFavoriteButton imageFavorite;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        imageUser = findViewById(R.id.imageUser);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        tvRepository = findViewById(R.id.tvRepository);
        tabsLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewPager);
//        imageFavorite = findViewById(R.id.imageFavorite);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SearchData = getIntent().getParcelableExtra(DETAIL_USER);
        if (SearchData != null) {
            strUsername = SearchData.getLogin();
        }
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), SearchData));
        viewPager.setOffscreenPageLimit(2);
        tabsLayout.setupWithViewPager(viewPager);

        userViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel.setUserDetail(strUsername);
        userViewModel.getUserList().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Glide.with(getApplicationContext())
                        .load(user.getAvatarUrl())
                        .into(imageUser);
                collapsingToolbarLayout.setTitle(user.getName());
                tvUsername.setText(user.getLogin()+"\u2022"+user.getLocation());
                tvBio.setText(user.getBio());
                tvFollowers.setText(user.getFollowers());
                tvFollowing.setText(user.getFollowing());
                tvRepository.setText(user.getPublicRepos());
            }
        });
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