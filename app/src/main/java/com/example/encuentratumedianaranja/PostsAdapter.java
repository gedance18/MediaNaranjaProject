package com.example.encuentratumedianaranja;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.checkerframework.checker.nullness.qual.NonNull;

class PostsAdapter extends FirestoreRecyclerAdapter<newPostFragment.Post, PostsAdapter.PostViewHolder> {
    public PostsAdapter(@NonNull FirestoreRecyclerOptions<newPostFragment.Post> options) {super(options);}

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_post, parent, false));
    }

    @Override
    protected void onBindViewHolder(@androidx.annotation.NonNull PostViewHolder holder, int i, @androidx.annotation.NonNull newPostFragment.Post post) {
        Glide.with(getContext()).load(post.authorPhotoUrl).circleCrop().into(holder.authorPhotoImageView);
        holder.authorTextView.setText(post.author);
        holder.contentTextView.setText(post.content);
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView authorPhotoImageView;
        TextView authorTextView, contentTextView;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);

            authorPhotoImageView = itemView.findViewById(R.id.authorPhotoImageView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
        }
    }
}
