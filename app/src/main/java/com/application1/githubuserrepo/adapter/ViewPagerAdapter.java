package com.application1.githubuserrepo.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.application1.githubuserrepo.models.Search;
import com.application1.githubuserrepo.ui.FollowersFragment;
import com.application1.githubuserrepo.ui.FollowingFragment;
import com.application1.githubuserrepo.ui.RepoFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    Search searchData;

    public ViewPagerAdapter(FragmentManager fragmentManager, Search searchData) {
        super(fragmentManager);
        this.searchData = searchData;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchData",searchData);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FollowersFragment();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FollowingFragment();
                fragment.setArguments(bundle);
                break;
            case 2:
                fragment = new RepoFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Followers";
                break;
            case 1:
                title = "Following";
                break;
            case 2:
                title = "Repositorys";
                break;
        }
        return title;
    }

}
