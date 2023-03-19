package com.example.mob_lab2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TwoActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);

        ListView listView = findViewById(R.id.listView);

        final String[] emotes = new String[] {
                "pepeChill", "Sadge", "Jokerge", "Hmm", "Susge"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row, emotes);

        listView.setAdapter(adapter);
    }
}