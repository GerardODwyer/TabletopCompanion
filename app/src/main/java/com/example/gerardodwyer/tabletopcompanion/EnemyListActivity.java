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

import com.example.gerardodwyer.tabletopcompanion.model.Enemy;
import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.example.gerardodwyer.tabletopcompanion.model.NPC;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class EnemyListActivity extends AppCompatActivity {

    private List<Enemy> enemyList = new ArrayList<>();
    private Enemy enemy;
    private RecyclerView mRecyclerView;
    private EnemyAdapter enemyAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRef;
    private Button AddEnemyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_list);

        databaseRef = FirebaseDatabase.getInstance().getReference("Enemies/");

        AddEnemyBtn = findViewById(R.id.addEnemyBtn);
        AddEnemyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEnemy();
            }
        });

        mRecyclerView = findViewById(R.id.EnemyRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                enemy = enemyList.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {
                enemy = enemyList.get(position);

            }
        }));

        loadData();
    }

    private List<Enemy> loadData() {
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Enemy enemy = dataSnapshot.getValue(Enemy.class);
                enemyList.add(enemy);


//                mRecyclerView.setHasFixedSize(true); //remove if broken recyclerView
                enemyAdapter = new EnemyAdapter(enemyList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(enemyAdapter);
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
        return enemyList;
    }


    private void createEnemy() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(EnemyListActivity.this);
        builder.setTitle("Create New Enemy");


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
        builder.setPositiveButton("Add to Enemy List", new DialogInterface.OnClickListener() {
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
                    Toast.makeText(EnemyListActivity.this, "Please Fill all Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    Enemy enemy = new Enemy(name, weapon, power, allegiance, appearance, backstory);


                    databaseRef = FirebaseDatabase.getInstance().getReference("Enemies/" + enemy.getName());
                    databaseRef.setValue(enemy);
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