package com.f7.outsiderz.tecoutz;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;
import com.f7.outsiderz.tecoutz.adapters.CategoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends AppCompatActivity {

    protected UserSessionManager session;
    public static final int THIRTY_SECONDS = 30000;
    private static Button searchButton;

    private static EditText textToSearch;
    GridView categoryList;
    ArrayList<String> categories;
    OfferManager offerManager;

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new UserSessionManager(this);


        textToSearch = (EditText) findViewById(R.id.home_text_to_search);
        searchButton = (Button) findViewById(R.id.home_button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = textToSearch.getText().toString();
                if (word.equals("")) {
                    textToSearch.setError("Please input key word to search.");
                } else {
                    Intent intent = new Intent(Home.this, SearchResult.class);
                    intent.putExtra("wordToSearch", word);
                    startActivity(intent);
                }
            }
        });

        offerManager = OfferManager.getInstance(this);
        categoryList = (GridView) findViewById(R.id.categoryList);
        categories = offerManager.getAllCategories();
        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        categoryList.setAdapter(adapter);
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Home.this, Category.class);
                TextView name = (TextView) view.findViewById(R.id.catName);
                intent.putExtra("category", name.getText().toString());
                startActivity(intent);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(THIRTY_SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notifyUserForNewOffer();
                }
            }
        }).start();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notifyUserForNewOffer() {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.drawable.icon); //change icon
        nBuilder.setContentTitle("All Offers");
        int count = offerManager.getOffersCount();
        nBuilder.setContentText("There are " + count + " offers now.");
        nBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, Home.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Home.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        nBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, nBuilder.build());

    }
}



