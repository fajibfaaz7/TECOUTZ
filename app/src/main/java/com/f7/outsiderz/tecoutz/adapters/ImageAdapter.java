package com.f7.outsiderz.tecoutz.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.f7.outsiderz.tecoutz.R;

import java.util.List;

/**
 * Created by fajibfaaz on 18/04/17.
 */

public class ImageAdapter extends ArrayAdapter<byte[]> {

    public ImageAdapter(Context context, List<byte[]> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        byte[] image = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_list_view, parent, false);
        }

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        ImageView imView = (ImageView) convertView.findViewById(R.id.image_offer_view);

        imView.setImageBitmap(bmp);

        return convertView;
    }
}