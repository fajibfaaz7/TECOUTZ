package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.adapters.OfferAdapter;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {

    private  OfferManager manager = OfferManager.getInstance(this);
    private ListView listViewOffers;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        populateListViewOffers();
        listViewOffers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResult.this, OfferView.class);
                intent.putExtra("idOffer", id);
                startActivity(intent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void populateListViewOffers() {
        Bundle bundle = getIntent().getExtras();
        String word = bundle.getString("wordToSearch");
        ArrayList<Offer> offers = manager.getOffersByWord(word);
        OfferAdapter adapter = new OfferAdapter(this, offers);
        listViewOffers = (ListView) findViewById(R.id.listViewOffers);
        listViewOffers.setAdapter(adapter);
    }


}

