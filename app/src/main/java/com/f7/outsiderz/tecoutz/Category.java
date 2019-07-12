package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.adapters.OfferAdapter;

import java.util.ArrayList;

/**
 * Created by fajibfaaz on 17/04/17.
 */

public class Category extends AppCompatActivity {


    Bundle bundle;
    ArrayList<Offer> offers;
    OfferManager manager;
    String category;
    TextView text;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        manager = OfferManager.getInstance(this);
        list = (ListView) findViewById(R.id.categoryOffers);
        bundle = getIntent().getExtras();
        category = bundle.getString("category");
        text = (TextView) findViewById(R.id.category);
        text.setText(category);
        offers = manager.getOffersByCategory(category);
        if(!offers.isEmpty()) {
            OfferAdapter adapter = new OfferAdapter(this, offers);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Category.this, OfferView.class);
                    intent.putExtra("idOffer", id);
                    startActivity(intent);
                }
            });
        }
    }

}




