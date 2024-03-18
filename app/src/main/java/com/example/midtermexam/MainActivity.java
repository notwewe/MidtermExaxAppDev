package com.example.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void startBatch2Activity(View view) {
            Button openAct = (Button) findViewById(R.id.btnOpen);
            Intent intent = new Intent(this, Batch2.class);
            startActivity(intent);

            openAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getBaseContext(), "Francis Wedemeyer N. Dayagro, Color Matchmaking", Toast.LENGTH_SHORT).show();
                    Intent scene = new Intent(MainActivity.this, Batch2.class);
                }
            });
        }


}