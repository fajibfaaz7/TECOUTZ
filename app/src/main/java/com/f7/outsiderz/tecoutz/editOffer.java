package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.Offer;
import com.f7.outsiderz.tecoutz.Dbpart.OfferManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class editOffer extends AddOffer implements View.OnClickListener {

    private OfferManager manager = OfferManager.getInstance(this);

    private static CheckBox archiveBox;

    private static boolean isActive;

    private static String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);

        archiveBox = (CheckBox) findViewById(R.id.edit_offer_active_checkbox);

        mainPicture = (ImageButton) findViewById(R.id.edit_offer_main_picture);
        picture1 = (ImageButton) findViewById(R.id.edit_offer_picture1);
        picture2 = (ImageButton) findViewById(R.id.edit_offer_picture2);
        picture3 = (ImageButton) findViewById(R.id.edit_offer_picture3);
        picture4 = (ImageButton) findViewById(R.id.edit_offer_picture4);
        picture5 = (ImageButton) findViewById(R.id.edit_offer_picture5);
        picture6 = (ImageButton) findViewById(R.id.edit_offer_picture6);
        title = (EditText) findViewById(R.id.edit_offer_title_text);
        categorySpinner = (Spinner) findViewById(R.id.edit_offer_category_spinner);
        price = (EditText) findViewById(R.id.edit_offer_price_text);
        condition = (RadioGroup) findViewById(R.id.edit_offer_condition_radio);
        description = (EditText) findViewById(R.id.edit_offer_description_text);
        semester = (EditText) findViewById(R.id.edit_offer_sem_text);
        addOfferButton = (Button) findViewById(R.id.edit_offer_save_button);

        Bundle bundle = getIntent().getExtras();
        final long id = bundle.getLong("idOffer");
        final Offer offer = manager.getOfferByID(id);
        isActive = offer.isActive();
        if(isActive){
            archiveBox.setChecked(true);
        } else {
            archiveBox.setChecked(false);
        }
        ArrayList<byte[]> images = offer.getImages();
        pictures = images;

        mainPictureCheck = true;
        for (int i = 0; i < images.size(); i++){
            Bitmap bmp = BitmapFactory.decodeByteArray(images.get(i), 0, images.get(i).length);
            switch (i){
                case 0:
                    mainPicture.setImageBitmap(bmp);
                    break;
                case 1:
                    picture1.setImageBitmap(bmp);
                    break;
                case 2:
                    picture2.setImageBitmap(bmp);
                    break;
                case 3:
                    picture3.setImageBitmap(bmp);
                    break;
                case 4:
                    picture4.setImageBitmap(bmp);
                    break;
                case 5:
                    picture5.setImageBitmap(bmp);
                    break;
                case 6:
                    picture6.setImageBitmap(bmp);
                    break;
                default:
                    Toast.makeText(editOffer.this, "Something went wrong in edit Offer on create switch", Toast.LENGTH_SHORT).show();
            }
        }

        title.setText(offer.getName());

        List<String> categories = offerManager.getAllCategories();
        categories.add(0, "Select category");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);

        categorySpinner.setSelection(((ArrayAdapter) categorySpinner.getAdapter()).getPosition(offer.getCategory()));

        price.setText(String.valueOf(offer.getPrice()));

        String cn = offer.getCondition();
        if(cn.equalsIgnoreCase("new"))
            condition.check(R.id.edit_offer_radioButton);
        else
            condition.check(R.id.edit_offer_radioButton2);

        description.setText(offer.getDescription());

        semester.setText(offer.getSemester());


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mainPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);

                photoPickerIntent.setDataAndType(data, "image/*");

                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST_1);

            }
        });

        picture1.setOnClickListener(this);
        picture2.setOnClickListener(this);
        picture3.setOnClickListener(this);
        picture4.setOnClickListener(this);
        picture5.setOnClickListener(this);
        picture6.setOnClickListener(this);

        addOfferButton.setText("Save edit");
        addOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRequirements(selectedCategory, R.id.edit_offer_condition_radio)){
                    Date date = new Date();

                    RadioGroup rg = (RadioGroup)findViewById(R.id.edit_offer_condition_radio);
                    String conditionString = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                    long userId = Long.parseLong(user.get(session.KEY_ID));
                    isActive = archiveBox.isChecked();
                    Offer of = new Offer(null, title.getText().toString(), description.getText().toString(),Double.parseDouble(price.getText().toString()), conditionString, selectedCategory, semester.getText().toString(), isActive, pictures, date);
                    long offerID = offerManager.updateOffer(offer.getId(), of);
                    Toast.makeText(editOffer.this, "Offer added successfully", Toast.LENGTH_SHORT).show();

                    if (offerID != -1) {
                        Toast.makeText(editOffer.this, "Offer added successfully", Toast.LENGTH_SHORT).show();
                        notifyUserForNewOffer(userId, offerID);
                    }
                    Intent offerViewIntent = new Intent(editOffer.this, OfferView.class);
                    offerViewIntent.putExtra("idOffer", offer.getId());
                    startActivity(offerViewIntent);
                    finish();
                }
            }
        });
    }

    @Override
    protected boolean checkRequirements(String selectedCategory, int condition_radio) {
        return super.checkRequirements(selectedCategory, condition_radio);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_offer_picture1:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_2);
                break;
            case R.id.edit_offer_picture2:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_3);
                break;
            case R.id.edit_offer_picture3:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_4);
                break;
            case R.id.edit_offer_picture4:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_5);
                break;
            case R.id.edit_offer_picture5:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_6);
                break;
            case R.id.edit_offer_picture6:
                askForPhotoWithIntent(IMAGE_GALLERY_REQUEST_7);
                break;
        }
    }

}

