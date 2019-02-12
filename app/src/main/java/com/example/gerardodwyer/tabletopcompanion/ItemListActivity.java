package com.example.gerardodwyer.tabletopcompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private List<Item> itemsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private FirebaseDatabase firebaseDb;
    private DatabaseReference tabletopRef;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        firebaseDb = FirebaseDatabase.getInstance();
        tabletopRef =firebaseDb.getReference();
        Log.i("TABLE", tabletopRef.toString());

//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        mRecyclerView.setHasFixedSize(true); //remove if broken recyclerView
//
//        myAdapter = new MyAdapter(itemsList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(myAdapter);
//
//        //decoration in recycleView
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        //set Adapter
//        mRecyclerView.setAdapter(myAdapter);

        loadItemData();
    }

    private void loadItemData() {
// Attach a listener to read the data at our posts reference
        tabletopRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item post = dataSnapshot.getValue(Item.class);
                itemsList.add(post);
                Log.i("FIREBASE", itemsList.get(0).getDescription());
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
        } );
    }
}
