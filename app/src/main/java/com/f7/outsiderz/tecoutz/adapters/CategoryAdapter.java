package com.f7.outsiderz.tecoutz.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.f7.outsiderz.tecoutz.R;

import java.util.List;

/**
 * Created by fajibfaaz on 17/04/17.
 */

public class CategoryAdapter extends ArrayAdapter<String> {

    private Activity context;
    private List<String> categories;

    public CategoryAdapter(Activity context, List<String> categories) {
        super(context, R.layout.category_view, categories);
        this.context = context;
        this.categories = categories;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.category_view, parent, false);
        }

        String category = categories.get(position);

        ImageView image = (ImageView) convertView.findViewById(R.id.catImage);
        TextView name = (TextView) convertView.findViewById(R.id.catName);

        switch (category){
            case "Books" : image.setImageResource(R.drawable.books); break;
            case "Instruments" : image.setImageResource(R.drawable.instruments); break;
            case "Photostats" : image.setImageResource(R.drawable.photostats); break;
            case "Notes" : image.setImageResource(R.drawable.notes); break;
            case "Accessories" : image.setImageResource(R.drawable.accessories); break;

        }
        name.setText(category);

        return convertView;
    }
}

