package com.application1.githubuserrepo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.viewmodel.UserViewModel;
import com.application1.githubuserrepo.adapter.SearchAdapter;
import com.application1.githubuserrepo.models.Search;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView ListUser;
    private EditText searchUser;
    private  ImageView imageClear, imageFavorite;
    private ConstraintLayout layoutEmpty;
    private ProgressDialog progressDialog;
    private UserViewModel searchViewModel;
    private SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListUser = findViewById(R.id.ListUser);
        imageFavorite = findViewById(R.id.imageFavorite);
        imageClear = findViewById(R.id.imageClear);
        searchUser = findViewById(R.id.searchUser);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("start loading ...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Showing data");

        imageClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchUser.getText().clear();
                imageClear.setVisibility(View.GONE);
//                layoutEmpty.setVisibility(View.VISIBLE);
                ListUser.setVisibility(View.GONE);
            }
        });
        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
            }
        });

        searchUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String username = textView.getText().toString();
                if (username.isEmpty()){
                    Toast.makeText(MainActivity.this, "The form cannot be empty!", Toast.LENGTH_SHORT).show();
                }else {
                    if (i == EditorInfo.IME_ACTION_SEARCH){
                        progressDialog.show();
                        searchViewModel.setSearchUser(username);
                        InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                        imageClear.setVisibility(View.VISIBLE);
//                        layoutEmpty.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });
        searchAdapter = new SearchAdapter(this);
        ListUser.setLayoutManager(new LinearLayoutManager(this));
        ListUser.setAdapter(searchAdapter);
        ListUser.setHasFixedSize(true);

        searchViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        searchViewModel.getResultList().observe(this, new Observer<ArrayList<Search>>() {
            @Override
            public void onChanged(ArrayList<Search> searches) {
                progressDialog.dismiss();
                if (searches.size() != 0) {
                    searchAdapter.setSearchUserList(searches);
                } else {
                    Toast.makeText(MainActivity.this, "User Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}