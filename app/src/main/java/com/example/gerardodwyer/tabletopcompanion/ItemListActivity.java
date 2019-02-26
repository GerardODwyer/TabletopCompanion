package com.example.gerardodwyer.tabletopcompanion;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private Item item;
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private FirebaseDatabase firebaseDb;
    private DatabaseReference tabletopRef;
    private Button addItmBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        firebaseDb = FirebaseDatabase.getInstance();
        tabletopRef = firebaseDb.getReference();
        Log.i("TABLE", tabletopRef.toString());

        addItmBtn = findViewById(R.id.addItmBtn);
        addItmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createItem();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        loadItemData();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Item item = itemsList.get(position);
                Toast.makeText(getApplicationContext(), item.getItem() + " is selected!", Toast.LENGTH_SHORT).show();
                onClickItem(item);
            }

            @Override
            public void onLongClick(View view, int position) {
                Item item = itemsList.get(position);
                Toast.makeText(getApplicationContext(), item.getId() + " is selected!", Toast.LENGTH_SHORT).show();
                removeItem(item);
            }
        }));

    }

    private List<Item> loadItemData() {
// Attach a listener to read the data at our posts reference
        tabletopRef.child("Archive/").addChildEventListener(new ChildEventListener() {
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

    private void onClickItem(final Item item) {


        tabletopRef = FirebaseDatabase.getInstance().getReference();
        tabletopRef.child("Archive/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (item.getId() == null) {
                    Toast.makeText(ItemListActivity.this, "Error could not fetch Item ID", Toast.LENGTH_SHORT).show();
                } else if (dataSnapshot.hasChild(item.getId())) {
                    Toast.makeText(ItemListActivity.this, "This Item is already the List", Toast.LENGTH_SHORT).show();
                } else {
                    addItem(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getItem:onCancelled", databaseError.toException());
            }
        });
    }

    private void addItem(Item item) {

        tabletopRef = FirebaseDatabase.getInstance().getReference("Archive/" +item.getId());
        tabletopRef.setValue(item);

        Toast.makeText(this, "Item added to List", Toast.LENGTH_SHORT).show();

    }

    private void removeItem(Item item) {

        tabletopRef = FirebaseDatabase.getInstance().getReference("Archive/" +item.getId());
        tabletopRef.removeValue();

        Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
    }


    private void createItem() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ItemListActivity.this);
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
        text_type.setHint("Type)");
        layout.addView(text_type);


        builder.setView(layout);


        builder.setCancelable(true);
        builder.setPositiveButton("Add to Stash", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String Id = text_id.getText().toString();
                String Item = text_item.getText().toString();
                String LootedFrom = text_lootedFrom.getText().toString();
                String Lore = text_lore.getText().toString();
                String Description = text_desc.getText().toString();
                String Unique = text_unique.getText().toString();
                String Type = text_type.getText().toString();

                //Item item = new Item(id, itemName, lootedFrom, lore, description, type);

                Item item = new Item(Id, Item, LootedFrom, Lore, Description, Unique, Type);

                tabletopRef = FirebaseDatabase.getInstance().getReference("Archive/" + Id);
                tabletopRef.setValue(item);
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