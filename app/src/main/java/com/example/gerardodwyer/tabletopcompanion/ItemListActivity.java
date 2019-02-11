package com.example.gerardodwyer.tabletopcompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private List<Item> itemsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true); //remove if broken recyclerView

        myAdapter = new MyAdapter(itemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(myAdapter);

        //decoration in recycleView
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //set Adapter
        mRecyclerView.setAdapter(myAdapter);

        loadItemData();
    }

    private void loadItemData() {
        Item item = new Item("01", "Str Ruin", "bob", "Delicious with a side of whipped cream", "Add 2 Str", "No", "Ruin");
        itemsList.add(item);

        item = new Item("02", "Duelin' Duelies", "rob", "None", "2 Atk and 5 Rng", "No", "Weapon");
        itemsList.add(item);

        myAdapter.notifyDataSetChanged();
    }
}
