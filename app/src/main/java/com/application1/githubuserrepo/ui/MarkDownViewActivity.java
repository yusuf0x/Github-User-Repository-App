package com.application1.githubuserrepo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.application1.githubuserrepo.R;

//import es.dmoral.markdownview.MarkdownView;


import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.styles.Github;

public class MarkDownViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_down_viewer);

        MarkdownView markdownView = findViewById(R.id.markdown_view);
//        MarkdownView markdownView = findViewById(R.id.markdown_view);
        String full_name = getIntent().getStringExtra("repo_name");
        Log.d("KEY11",full_name);
//        mark.mark
//        markdownView.loadFromUrl("https://raw.githubusercontent.com/"+full_name+"/master/README.md");
        markdownView.addStyleSheet(new Github());
        markdownView.loadMarkdownFromUrl("https://raw.githubusercontent.com/"+full_name+"/master/README.md");
    }
}