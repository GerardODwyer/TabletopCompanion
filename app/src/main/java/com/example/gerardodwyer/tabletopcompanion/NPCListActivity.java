package com.example.gerardodwyer.tabletopcompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.example.gerardodwyer.tabletopcompanion.model.NPC;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class NPCListActivity extends AppCompatActivity {

    private List<NPC> npcList = new ArrayList<>();
    private NPC npc;
    private RecyclerView mRecyclerView;
    private NPCAdapter npcAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npclist);

        databaseRef = FirebaseDatabase.getInstance().getReference("NPCs");

        mRecyclerView = findViewById(R.id.npcRecyclerView);
//
//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                NPC npc = npcList.get(position);
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                NPC npc = npcList.get(position);
//
//            }
//        }));

        loadData();
    }

    private List<NPC> loadData() {
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NPC npc = dataSnapshot.getValue(NPC.class);
                npcList.add(npc);


//                mRecyclerView.setHasFixedSize(true); //remove if broken recyclerView
                npcAdapter = new NPCAdapter(npcList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(npcAdapter);
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
        return npcList;
    }
}
