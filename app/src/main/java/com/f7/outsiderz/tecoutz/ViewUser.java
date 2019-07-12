package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;


import java.util.ArrayList;
import java.util.HashMap;

public class ViewUser extends AppCompatActivity {
    protected UserSessionManager session;
    TextView uname;
    TextView semester;
    TextView phone;
    TextView email;
    private long id;
    UserAcc user;

    UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        session = new UserSessionManager(this);
        manager = UserManager.getInstance(this);
        id = Long.parseLong(session.getUserDetails().get(session.KEY_ID));
        user = manager.getUser(id);


        uname = (TextView) findViewById(R.id.usernameField);
        semester = (TextView) findViewById(R.id.sem);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);



        if(session.isUserLoggedIn()){
            // get user details
            HashMap<String, String> user = session.getUserDetails();

            uname.setText( user.get(session.KEY_NAME) + "'s Account");
        }


        phone.setText(user.getPhoneNumber());
        semester.setText(user.getSemester());
        email.setText(user.getEmail());











        }

    }



