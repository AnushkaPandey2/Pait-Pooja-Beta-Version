package com.example.myapplication.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.RecipeAdapter;
import com.example.myapplication.Models.RecipeModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoryThird extends Fragment {
    ArrayList<RecipeModel> list1;
    public static CategoryThird getInstance() {
        CategoryThird categoryThird = new CategoryThird();
        return categoryThird;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.category_third, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclarView);
        RecipeAdapter adapter=new RecipeAdapter(list1, getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list1 = new ArrayList<>();
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food1"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food2"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food3"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food4"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food5"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food6"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food7"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food8"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food9"));
        list1.add(new RecipeModel(R.drawable.bgimg, "Category3    food10"));

    }

}
