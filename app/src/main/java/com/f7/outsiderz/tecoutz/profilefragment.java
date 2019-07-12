package com.f7.outsiderz.tecoutz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;

import java.util.HashMap;


/**
 * Created by fajibfaaz on 25/03/17.
 */

public class profilefragment extends Fragment {

    private TextView uname, semester, phone;
    private ListView offersList;
    protected UserSessionManager session;
    protected UserManager userManager;
    Bundle bundle;
    private UserAcc user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity())
                .setActionBarTitle("My Account");

        View v = inflater.inflate(R.layout.profile, container, false);
        initViews(v);

        return v;

    }

    public void initViews(View v) {






    }







    @Override
    public void onActivityCreated(Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profragment, menu);
        return;
    }

}
