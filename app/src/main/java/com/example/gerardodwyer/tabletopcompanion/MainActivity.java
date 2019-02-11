package com.example.gerardodwyer.tabletopcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private ImageButton button;
    private ImageButton newBtn;

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
    }

    public void openItems() {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivity(intent);
    }
}