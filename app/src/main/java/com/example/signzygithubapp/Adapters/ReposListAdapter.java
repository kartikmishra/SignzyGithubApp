package com.example.signzygithubapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signzygithubapp.Model.ReposModel;
import com.example.signzygithubapp.R;
import com.example.signzygithubapp.Activities.WebViewActivity;

import java.util.List;



public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.ReposListAdapterViewHolder> {


    private List<ReposModel> reposModel;
    private Context mContext;


    public ReposListAdapter(List<ReposModel> reposModel, Context mContext) {
        this.reposModel = reposModel;
        this.mContext = mContext;

    }


    public void updateList(List<ReposModel> list){
        reposModel = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReposListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.repo_recycler_view_item,parent,false);
        return new ReposListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReposListAdapterViewHolder holder, final int position) {

        holder.repoNumber.setText(String.valueOf(position+1).concat("."));
        holder.repoName.setText(reposModel.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", reposModel.get(position).getHtmlUrl());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reposModel.size();
    }



    class ReposListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView repoNumber, repoName;

        public ReposListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repoName);
            repoNumber = itemView.findViewById(R.id.repoNumber);

        }

    }
}
