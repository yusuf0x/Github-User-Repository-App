package com.application1.githubuserrepo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.models.Search;
import com.application1.githubuserrepo.ui.ProfileActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private ArrayList<Search> SearchDataList= new ArrayList<>();
    private Context context;
    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchUserList(ArrayList<Search> items) {
        SearchDataList.clear();
        SearchDataList.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Search item = SearchDataList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.Username.setText(item.getLogin());
        holder.Url.setText(item.getHtmlUrl());
        holder.ListUser.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra(ProfileActivity.DETAIL_USER, SearchDataList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return SearchDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView ListUser;
        TextView Username, Url;
        ImageView imageUser;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ListUser = itemView.findViewById(R.id.cvListUser);
            Username = itemView.findViewById(R.id.tvUsername);
            Url = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }
}
