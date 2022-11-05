package com.application1.githubuserrepo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.models.Follower;
import com.application1.githubuserrepo.models.Repo;
import com.application1.githubuserrepo.ui.MarkDownViewActivity;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder> {

    private  Context context;
    private  ArrayList<Repo> repoArrayList = new ArrayList<>();

    public void setReposUserList(ArrayList<Repo> items) {
        repoArrayList.clear();
        repoArrayList.addAll(items);
        notifyDataSetChanged();
    }
    public  RepoAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         Repo repo = repoArrayList.get(position);
         holder.textViewTitle.setText(repo.getFullName());
         holder.textViewDesc.setText(repo.getDescription());
         holder.textViewLanguage.setText(repo.getLanguage());
         holder.textViewStars.setText(repo.getStargazersCount()+"");
         holder.textViewForks.setText(repo.getForksCount()+"");
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, MarkDownViewActivity.class);
                 intent.putExtra("repo_name",repo.getFullName());
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return repoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDesc;
        TextView textViewLanguage;
        TextView textViewStars;
        TextView textViewForks;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewLanguage = itemView.findViewById(R.id.textViewLanguage);
            textViewStars = itemView.findViewById(R.id.textViewStars);
            textViewForks = itemView.findViewById(R.id.textViewForks);
            cardView = itemView.findViewById(R.id.repoItemParent);
        }
    }
}
