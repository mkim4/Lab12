package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * new layout.
 */

public class News extends AppCompatActivity {
    /**
     * edit text.
     */
    private EditText input;
    /**
     * create.
     * @param savedInstanceState does things.
     */
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        TextView display = findViewById(R.id.textView2);
        display.setText(MainActivity.a);
    }
}
