package com.example.gerardodwyer.tabletopcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private ImageButton btn5;
    private ImageButton newBtn;
    private int choice;
    private String path;
    private CharSequence[] values = {" Player 1 "," Archive ", " Stash "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBtn = (ImageButton) findViewById(R.id.allItems);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openItems();
            }
        });

        btn5 = findViewById(R.id.imageButton5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stashChoiceMenu();
            }
        });
    }

    public void openItems() {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }

    public void stashChoiceMenu() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Open Inventory");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        choice = item;
                        break;
                    case 1:
                        choice = item;
                        break;
                    case 2:
                        choice = item;
                        break;
                }
                builder.setMessage("Please Choose Inv Path");
            }
        });


        builder.setCancelable(true);
        builder.setPositiveButton("Open Inventory", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openStashActivity();

            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void openStashActivity() {

        if(choice == 0){
            String path = "Player1/";
            this.path = path;
        }
        else
        if(choice == 1){
            String path = "Archive/";
            this.path = path;
        }
        else
        if(choice == 2){
            String path = "Stash/";
            this.path = path;
        }
        else
        {
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }


        Intent i=new Intent(this,PlayerStashActivity.class);
        i.putExtra("path", path);
        this.startActivity(i);

    }
}