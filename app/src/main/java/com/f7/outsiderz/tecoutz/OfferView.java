package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;

import java.util.ArrayList;

public class OfferView extends AppCompatActivity {

    private static Offer offer;

    public static final int CALL_REQUEST_CODE = 111;

    private static Button call;

    private OfferManager manager = OfferManager.getInstance(this);
    private UserManager userManager = UserManager.getInstance(this);
    protected UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_view);
        Bundle bundle = getIntent().getExtras();
        final long id = bundle.getLong("idOffer");
        offer = manager.getOfferByID(id);
        ArrayList<byte[]> images = offer.getImages();
        session = new UserSessionManager(this);

        Bitmap bmp = BitmapFactory.decodeByteArray(images.get(0), 0, images.get(0).length);
        ImageView mainImage = (ImageView) findViewById(R.id.offer_view_main_image);
        mainImage.setImageBitmap(bmp);
        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfferView.this, ImagesView.class);
                intent.putExtra("idOffer", id);

                startActivity(intent);
            }
        });

        TextView title = (TextView) findViewById(R.id.offer_view_title_text);
        title.setText(offer.getName());

        TextView price = (TextView) findViewById(R.id.offer_view_price_text);
        price.setText(String.valueOf("₹ " + offer.getPrice()));

        TextView addedFrom = (TextView) findViewById(R.id.offer_view_added_from_text);
        addedFrom.setText(offer.getSeller().getUserName());


        TextView condition = (TextView) findViewById(R.id.offer_view_condition_text);
        condition.setText("Condition: " + offer.getCondition());


        TextView description = (TextView) findViewById(R.id.offer_view_description_text);
        description.setText(offer.getDescription());


        Button sendMessage = (Button) findViewById(R.id.offer_view_send_message_button);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = offer.getSeller().getUserId();
                long myId = Long.parseLong(session.getUserDetails().get(session.KEY_ID));
                if (id != myId) {
                    Intent intent = new Intent(OfferView.this, SendMessage.class);
                    intent.putExtra("id", id);
                    intent.putExtra("Username", offer.getSeller().getUserName());
                    intent.putExtra("Title", offer.getName());
                    startActivity(intent);
                } else {
                    Toast.makeText(OfferView.this, "Cannot send message to yourself", Toast.LENGTH_SHORT).show();
                }
            }
        });

        call = (Button) findViewById(R.id.offer_view_phone_number_button);
        call.setText("Call " + offer.getSeller().getPhoneNumber());
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "tel:" + offer.getSeller().getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));

                int hasCallPermission = ActivityCompat.checkSelfPermission(OfferView.this, android.Manifest.permission.CALL_PHONE);
                if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(OfferView.this, new String[]{android.Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE);
                } else
                    startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CALL_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String url = "tel:" + offer.getSeller().getPhoneNumber();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    int hasCallPermission = ActivityCompat.checkSelfPermission(OfferView.this, android.Manifest.permission.CALL_PHONE);
                    if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    } else {
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                    }
                }
                return;
        }
    }
}
