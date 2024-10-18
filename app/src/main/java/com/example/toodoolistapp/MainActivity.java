package com.example.toodoolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.list_view);
        FloatingActionButton fab = findViewById(R.id.circularButton);
        ImageView ivMic = findViewById(R.id.iv_mic);
        findViewById(R.id.et_quick_task);
        Button btnNewList = findViewById(R.id.new_list);
        Spinner arrow = findViewById(R.id.icon_arrow);


        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Task.class);
            startActivity(intent);     });

        btnNewList.setOnClickListener(v -> Toast.makeText(MainActivity.this, "ALL LIST", Toast.LENGTH_SHORT).show());


        ivMic.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Mic Clicked!", Toast.LENGTH_SHORT).show());

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.list_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            arrow.setAdapter(adapter);
        }

    }
    


