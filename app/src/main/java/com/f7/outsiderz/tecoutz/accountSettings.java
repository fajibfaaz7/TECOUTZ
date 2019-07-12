package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;

import java.util.HashMap;

public class accountSettings extends AppCompatActivity {

    protected UserSessionManager session;

    private static Button editMail;
    private static  Button editPersonalInfo;
    private static Button editPass;
    private static Button logout;
    public TextView helloMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        session = new UserSessionManager(getApplicationContext());

        helloMsg = (TextView) findViewById(R.id.hellomsg);

        if(session.isUserLoggedIn()){
            // get user details
            HashMap<String, String> user = session.getUserDetails();

            helloMsg.setText( user.get(session.KEY_NAME) + "'s Account");
        }
        else{
            helloMsg.setText("Hello, guest");
        }

        editMail = (Button) findViewById(R.id.edit_mail);
        editMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accountSettings.this, changeEmail.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });
        editPass = (Button) findViewById(R.id.edit_pass);
        editPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accountSettings.this, changePassword.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });
        editPersonalInfo = (Button) findViewById(R.id.edit_personal_info);
        editPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accountSettings.this, editProInfo.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });

        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               session.logoutUser();
                finish();
            }
        });

    }


}
