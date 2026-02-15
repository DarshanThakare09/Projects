package com.example.recyclerviewex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerUserAdapter extends RecyclerView.Adapter<RecyclerUserAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserModel> model;
    public RecyclerUserAdapter(Context context,ArrayList<UserModel> model){
        this.context = context;
        this.model = model;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.list_item_card,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.img.setImageResource(model.get(position).getImage());
        holder.name.setText(model.get(position).getName());
        holder.number.setText(model.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,number;
        public MyViewHolder(View viewType){
            super(viewType);
            img = viewType.findViewById(R.id.image);
            name = viewType.findViewById(R.id.name);
            number = viewType.findViewById(R.id.number);
        }
    }
}
