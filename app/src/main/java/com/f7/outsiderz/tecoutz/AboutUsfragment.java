package com.f7.outsiderz.tecoutz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fajibfaaz on 20/04/17.
 */

public class AboutUsfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity())
                .setActionBarTitle("About Us");

        View v = inflater.inflate(R.layout.aboutus, container, false);
        return  v;
    }
}
