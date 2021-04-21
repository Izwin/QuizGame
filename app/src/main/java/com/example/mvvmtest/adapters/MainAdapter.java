package com.example.mvvmtest.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmtest.R;
import com.example.mvvmtest.model.QuizItemModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>  {

    Context context;
    ArrayList<QuizItemModel> arrayList;
    OnQuizItemClick onQuizItemClick;
    public MainAdapter(Context context, ArrayList<QuizItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent , false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        QuizItemModel quizItemModel = arrayList.get(position);
        holder.questionsCount.setText(String.valueOf(quizItemModel.getQuestionList().size()));
        holder.itemName.setText(quizItemModel.getName());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onQuizItemClick.onQuizItemLongClick(position , arrayList);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuizItemClick.onQuizItemClick(position, arrayList);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnQuizItemClick(OnQuizItemClick onQuizItemClick) {
        this.onQuizItemClick = onQuizItemClick;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView itemName , questionsCount;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            questionsCount = itemView.findViewById(R.id.questions_count_text);
        }
    }
    public interface OnQuizItemClick {
        public void onQuizItemClick(int pos , ArrayList<QuizItemModel> quizItemModels);
        public void onQuizItemLongClick(int pos ,  ArrayList<QuizItemModel> quizItemModels);
    }

}
