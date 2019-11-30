package com.example.signzygithubapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.signzygithubapp.Model.User;
import com.example.signzygithubapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentUserDetail extends Fragment {

    private static final String TAG = "FragmentUserDetail";
    private View view;
    private User user;
    private TextView profileName,reposCount,followersCount,followingCount,email,bio, location;
    private CircleImageView profileImageview;

    public FragmentUserDetail(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_detail_fragment,container,false);
        setUpViews();
        setUpDataIntoViews();
        return view;
    }


    public void setUpViews() {
        profileName = view.findViewById(R.id.profile_name);
        reposCount = view.findViewById(R.id.repos);
        followersCount = view.findViewById(R.id.followers);
        followingCount = view.findViewById(R.id.following);
        bio = view.findViewById(R.id.bio);
        profileImageview = view.findViewById(R.id.profile_image);
        location = view.findViewById(R.id.location);
    }


    public void setUpDataIntoViews() {

        profileName.setText(user.getName());
        Log.d(TAG, "setUpDataIntoViews: "+ user.getPublicRepos());
        reposCount.setText(String.valueOf(user.getPublicRepos()));
        followingCount.setText(String.valueOf(user.getFollowers()));
        followingCount.setText(String.valueOf(user.getFollowing()));
        bio.setText(user.getBio());
        location.setText(user.getLocation());
        Picasso.get().load(user.getAvatarUrl()).into(profileImageview);

    }
}
