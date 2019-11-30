package com.example.signzygithubapp.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signzygithubapp.Model.ReposModel;
import com.example.signzygithubapp.Model.User;
import com.example.signzygithubapp.R;
import com.example.signzygithubapp.Adapters.ReposListAdapter;
import com.example.signzygithubapp.Retrofit.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRepository extends Fragment {

    private static final String TAG = "FragmentRepository";

    private View view;
    private User user;
    private RecyclerView reposRecyclerView;

    public FragmentRepository(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.repository_fragment, container, false);
        getReposData();

        reposRecyclerView = view.findViewById(R.id.repos_recyclerView);

        FloatingActionButton actionButton = view.findViewById(R.id.fab);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Filter", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public void getReposData() {

        ApiClient.getmInstance().getApi().getReposList(user.getLogin())
                .enqueue(new Callback<List<ReposModel>>() {
                    @Override
                    public void onResponse(Call<List<ReposModel>> call, Response<List<ReposModel>> response) {

                        ReposListAdapter listAdapter = new
                                ReposListAdapter(response.body(),getContext());

                        LinearLayoutManager linearLayoutManager = new
                                LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);

                        reposRecyclerView.setLayoutManager(linearLayoutManager);
                        reposRecyclerView.setHasFixedSize(true);
                        reposRecyclerView.setAdapter(listAdapter);

                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<ReposModel>> call, Throwable t) {

                    }
                });

    }

}
