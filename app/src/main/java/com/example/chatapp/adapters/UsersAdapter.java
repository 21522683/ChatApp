package com.example.chatapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemConstainerUserBinding;
import com.example.chatapp.listener.UserListener;
import com.example.chatapp.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.userViewHolder>{

    private final List<User> listUsers;
    private final UserListener userListener;

    public UsersAdapter(List<User> listUsers, UserListener userListener) {

        this.listUsers = listUsers;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConstainerUserBinding itemConstainerUserBinding = ItemConstainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new userViewHolder(itemConstainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        holder.setUserData(listUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    class userViewHolder extends RecyclerView.ViewHolder{
        ItemConstainerUserBinding binding;
        userViewHolder(ItemConstainerUserBinding itemConstainerUserBinding)
        {
            super(itemConstainerUserBinding.getRoot());
            binding = itemConstainerUserBinding;
        }

        void setUserData(User user)
        {
            binding.textEmail.setText(user.email);
            binding.textName.setText(user.name);
            binding.imageProfile.setImageBitmap(getUserImage(user.image));
            binding.getRoot().setOnClickListener(view -> userListener.onUserClicked(user));
        }
    }

    Bitmap getUserImage(String encodedImage){
        byte[] bytes =Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}