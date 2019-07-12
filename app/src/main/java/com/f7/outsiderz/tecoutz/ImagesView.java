package com.f7.outsiderz.tecoutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.adapters.ImageAdapter;

import java.util.ArrayList;

public class ImagesView extends AppCompatActivity {

    private OfferManager manager = OfferManager.getInstance(this);
    private ListView imagesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_view);

        Bundle bundle = getIntent().getExtras();
        final long id = bundle.getLong("idOffer");
        Offer offer = manager.getOfferByID(id);
        ArrayList<byte[]> images = offer.getImages();

        ImageAdapter adapter = new ImageAdapter(this, images);
        imagesView = (ListView) findViewById(R.id.images_list_view);
        imagesView.setAdapter(adapter);
    }
}
