package com.example.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView numbers = (TextView) findViewById(R.id.Numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers = new Intent(MainActivity.this, NumberActivity.class);
                startActivity(numbers);
            }
        });

        TextView Family_Members = (TextView) findViewById(R.id.Family_Members);
        Family_Members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Family_Members = new Intent(MainActivity.this, familyActivity.class);

                startActivity(Family_Members);
            }
        });

        TextView Colors = (TextView) findViewById(R.id.Colors);
        Colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Colors = new Intent(MainActivity.this, colorActivity.class);
                startActivity(Colors);
            }
        });

        TextView phrases = (TextView) findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phrases = new Intent(MainActivity.this, phrasesActivity.class);
                startActivity(phrases);
            }
        });
    }

}
