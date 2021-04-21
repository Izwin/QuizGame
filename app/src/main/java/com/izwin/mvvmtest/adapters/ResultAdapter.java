package com.izwin.mvvmtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.model.ResultModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    Context context;
    ArrayList<ResultModel> resultModels;

    public ResultAdapter(Context context, ArrayList<ResultModel> resultModels) {
        this.context = context;
        this.resultModels = resultModels;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.result_item,parent,false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, int position) {
        ResultModel resultModel = resultModels.get(position);
        holder.itemNameText.setText(resultModel.getItemName());
        holder.dateText.setText(new SimpleDateFormat("dd/MM").format(resultModel.getDate()));
        holder.usernameText.setText(resultModel.getUser());
        holder.scoreText.setText(String.format("%.2f", resultModel.getScore() ));
    }

    @Override
    public int getItemCount() {
        return resultModels.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameText, scoreText , dateText , itemNameText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.username_text);
            scoreText = itemView.findViewById(R.id.score_text);
            dateText = itemView.findViewById(R.id.date_text);
            itemNameText = itemView.findViewById(R.id.item_name_text);

        }
    }
}
