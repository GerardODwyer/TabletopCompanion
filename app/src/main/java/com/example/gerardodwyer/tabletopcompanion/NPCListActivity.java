package com.example.gerardodwyer.tabletopcompanion;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private Button AddNPCBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npclist);

        databaseRef = FirebaseDatabase.getInstance().getReference("NPCs");

        AddNPCBtn = findViewById(R.id.addNPCBtn);
        AddNPCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNPC();
            }
        });

        mRecyclerView = findViewById(R.id.npcRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                npc = npcList.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {
                npc = npcList.get(position);

            }
        }));

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


    private void createNPC() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(NPCListActivity.this);
        builder.setTitle("Create New NPC");


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);


        final EditText text_name = new EditText(this);
        text_name.setHint("Name");
        layout.addView(text_name);

        final EditText text_weapon = new EditText(this);
        text_weapon.setHint("Weapon");
        layout.addView(text_weapon);

        final EditText text_power = new EditText(this);
        text_power.setHint("Power");
        layout.addView(text_power);

        final EditText text_allegiance = new EditText(this);
        text_allegiance.setHint("Allegiance");
        layout.addView(text_allegiance);

        final EditText text_appearance = new EditText(this);
        text_appearance.setHint("Appearance");
        layout.addView(text_appearance);

        final EditText text_backstory = new EditText(this);
        text_backstory.setHint("Backstory");
        layout.addView(text_backstory);


        builder.setView(layout);


        builder.setCancelable(true);
        builder.setPositiveButton("Add to NPC List", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String name = text_name.getText().toString();
                String weapon = text_weapon.getText().toString();
                String power = text_power.getText().toString();
                String allegiance = text_allegiance.getText().toString();
                String appearance = text_appearance.getText().toString();
                String backstory = text_backstory.getText().toString();


                if (name.isEmpty() || weapon.isEmpty() || power.isEmpty() || allegiance.isEmpty()|| appearance.isEmpty() || backstory.isEmpty())
                {
                    Toast.makeText(NPCListActivity.this, "Please Fill all Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    NPC npc = new NPC(name, weapon, power, allegiance, appearance, backstory);


                    databaseRef = FirebaseDatabase.getInstance().getReference("NPCs/" + npc.getName());
                    databaseRef.setValue(npc);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}