package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class storeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ImageView Image1 = (ImageView) findViewById(R.id.books);
        Image1.setClickable(true);
        Image1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                Intent intent = new Intent(V.getContext(), BooksActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageView Image2 = (ImageView) findViewById(R.id.imageView11);
        Image2.setClickable(true);
        Image2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                Intent intent = new Intent(V.getContext(), SportsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageView Image3 = (ImageView) findViewById(R.id.instru);
        Image3.setClickable(true);
        Image3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                Intent intent = new Intent(V.getContext(), InsrumentsActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageView Image4 = (ImageView) findViewById(R.id.uniform);
        Image4.setClickable(true);
        Image4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View V) {

                Intent intent = new Intent(V.getContext(), UniformActivity.class);
                startActivityForResult(intent, 0);
            }
        });



    }
}
