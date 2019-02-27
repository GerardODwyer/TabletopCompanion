package com.example.gerardodwyer.tabletopcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.gerardodwyer.tabletopcompanion.MainActivity;
import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlayerStashActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private List<Item> itemsList = new ArrayList<>();
    private Item item;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private FirebaseDatabase firebaseDb;
    private DatabaseReference tabletopRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stash);

        firebaseDb = FirebaseDatabase.getInstance();
        tabletopRef = firebaseDb.getReference();

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadItemData(path);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Item item = itemsList.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {
                Item item = itemsList.get(position);

            }
        }));

    }

    private List<Item> loadItemData(String path) {
// Attach a listener to read the data at our posts reference
        tabletopRef.child(path).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item post = dataSnapshot.getValue(Item.class);
                itemsList.add(post);

                //mRecyclerView.setHasFixedSize(true); //remove if broken recyclerView

                myAdapter = new MyAdapter(itemsList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(myAdapter);

                //decoration in recycleView
                //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(myAdapter);

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return itemsList;
    }
}
