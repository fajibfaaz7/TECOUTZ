package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;
import com.f7.outsiderz.tecoutz.adapters.MyOffersAdapter;
import com.f7.outsiderz.tecoutz.adapters.OfferAdapter;

import java.util.ArrayList;

public class MyOffers extends AppCompatActivity {

    ListView offersList;

    Bundle bundle;
    UserManager userManager;
    OfferManager offerManager;
    protected UserSessionManager session;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        session = new UserSessionManager(this);
        userManager = UserManager.getInstance(this);
        offerManager = OfferManager.getInstance(this);
        offersList = (ListView) findViewById(R.id.user_offers_list);


        bundle = getIntent().getExtras();
        id = Long.parseLong(session.getUserDetails().get(session.KEY_ID));
        UserAcc user = userManager.getUser(id);

        ArrayList<Offer> userActiveOffers = offerManager.getOffersByUser(id);
        ArrayList<Offer> userAllOffers = offerManager.getAllMyOffers(id);


        if (String.valueOf(id).equals(session.getUserDetails().get(session.KEY_ID))) {
            MyOffersAdapter adapter = new MyOffersAdapter(this, userAllOffers);
            offersList.setAdapter(adapter);
            offersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MyOffers.this, OfferView.class);
                    intent.putExtra("idOffer", id);
                    startActivity(intent);
                }
            });
        } else {
            OfferAdapter adapter = new OfferAdapter(this, userActiveOffers);
            offersList.setAdapter(adapter);
            offersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MyOffers.this, OfferView.class);
                    intent.putExtra("idOffer", id);
                    startActivity(intent);
                }
            });
        }
    }
}