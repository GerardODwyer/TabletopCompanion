package com.example.gerardodwyer.tabletopcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private String path;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private FirebaseDatabase firebaseDb;
    private DatabaseReference tabletopRef;
    private Button createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stash);

        firebaseDb = FirebaseDatabase.getInstance();
        tabletopRef = firebaseDb.getReference();

        Intent intent = getIntent();
        path = intent.getStringExtra("path");


        createButton = findViewById(R.id.addItmBtn2);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createItem();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadItemData(path);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Item item = itemsList.get(position);
                returnItem(item);
            }

            @Override
            public void onLongClick(View view, int position) {
                Item item = itemsList.get(position);
                removeItem(item);
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

    private void removeItem(Item item) {

        tabletopRef = FirebaseDatabase.getInstance().getReference(path +item.getId());
        tabletopRef.removeValue();

        Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
    }

    private void returnItem(Item item) {

        tabletopRef = FirebaseDatabase.getInstance().getReference("Archive/" + item.getId());
        tabletopRef.setValue(item);

        tabletopRef = FirebaseDatabase.getInstance().getReference(path +item.getId());
        tabletopRef.removeValue();

        Toast.makeText(this, "Item Returned to Archive", Toast.LENGTH_SHORT).show();
    }

    private void createItem() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(PlayerStashActivity.this);
        builder.setTitle("Create New Item");


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);


        final EditText text_id = new EditText(this);
        text_id.setHint("ID");
        layout.addView(text_id);

        final EditText text_item = new EditText(this);
        text_item.setHint("Item");
        layout.addView(text_item);

        final EditText text_lootedFrom = new EditText(this);
        text_lootedFrom.setHint("Looted From");
        layout.addView(text_lootedFrom);

        final EditText text_lore = new EditText(this);
        text_lore.setHint("Lore");
        layout.addView(text_lore);

        final EditText text_desc = new EditText(this);
        text_desc.setHint("Description");
        layout.addView(text_desc);

        final EditText text_unique = new EditText(this);
        text_unique.setHint("Unique");
        layout.addView(text_unique);

        final EditText text_type = new EditText(this);
        text_type.setHint("Type");
        layout.addView(text_type);


        builder.setView(layout);


        builder.setCancelable(true);
        builder.setPositiveButton("Add to Inventory", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String Id = text_id.getText().toString();
                String Item = text_item.getText().toString();
                String LootedFrom = text_lootedFrom.getText().toString();
                String Lore = text_lore.getText().toString();
                String Description = text_desc.getText().toString();
                String Unique = text_unique.getText().toString();
                String Type = text_type.getText().toString();


                if (Id.isEmpty() || Item.isEmpty() || LootedFrom.isEmpty() || Lore.isEmpty()|| Description.isEmpty() || Unique.isEmpty() || Type.isEmpty())
                {
                    Toast.makeText(PlayerStashActivity.this, "Please Fill all Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    Item item = new Item(Id, Item, LootedFrom, Lore, Description, Unique, Type);


                    tabletopRef = FirebaseDatabase.getInstance().getReference(path + item.getId());
                    tabletopRef.setValue(item);
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
