package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ListenerInterface;
import com.example.myapplication.database.RecipeModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    Context context;
    List<RecipeModel> recipes;
    private ListenerInterface listener;
    public RecipeAdapter(Context context, List<RecipeModel> recipes,ListenerInterface listener) {
        this.context = context;
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe,parent,false);
        return new RecipeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {
        holder.textTittleRecipe.setText(recipes.get(position).getTittleRecipe());
        holder.authorRecipe.setText("Автор: ".concat(recipes.get(position).getAuthorRecipe()));
        holder.textComponentsRecipe.setText("Ингредиентов: ".concat(recipes.get(position).getComponentsRecipe().toString()));
        holder.imageRecipe.setImageResource(recipes.get(position).getImageRecipe());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Передаёт позицию рецепта, чтобы в дальнейшем передать данные во 2 фрагмент
                listener.onItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageRecipe;
        TextView textTittleRecipe, textComponentsRecipe, authorRecipe;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageRecipe = itemView.findViewById(R.id.imageRecipe);
            textTittleRecipe = itemView.findViewById(R.id.titleRecipe);
            textComponentsRecipe = itemView.findViewById(R.id.numberComponents);
            authorRecipe = itemView.findViewById(R.id.authorRecipe);

        }
    }
}
