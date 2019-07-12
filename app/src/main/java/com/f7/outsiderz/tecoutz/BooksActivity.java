package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        Button note = (Button) findViewById(R.id.button3);

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noteIntent = new Intent(BooksActivity.this, NotebookActivity.class);

                startActivity(noteIntent);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Button fair = (Button) findViewById(R.id.button4);

        fair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noteIntent = new Intent(BooksActivity.this, FairActivity.class);

                startActivity(noteIntent);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Button rough = (Button) findViewById(R.id.button5);

        rough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noteIntent = new Intent(BooksActivity.this, RoughActivity.class);

                startActivity(noteIntent);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Button assign = (Button) findViewById(R.id.button2);

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noteIntent = new Intent(BooksActivity.this, AssignActivity.class);

                startActivity(noteIntent);

                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
}
