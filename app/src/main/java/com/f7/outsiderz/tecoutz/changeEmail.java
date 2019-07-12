package com.f7.outsiderz.tecoutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;

import java.util.HashMap;

public class changeEmail extends AppCompatActivity {

    protected UserSessionManager session;
    private Button editEmail;
    private EditText password;
    private EditText email;
    static private UserManager manager;
    private UserAcc user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);


        manager = UserManager.getInstance(this);
        session = new UserSessionManager(this);
        editEmail = (Button)findViewById(R.id.edit_email_save_button);
        password = (EditText) findViewById(R.id.edit_email_password);
        email = (EditText) findViewById(R.id.edit_email_email);




        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passText = password.getText().toString();
                final String emailText = email.getText().toString();
                if(passText.isEmpty()) {
                    password.setError("This field is required.");
                    return;
                }
                if(emailText.isEmpty()) {
                    email.setError("This field is required.");
                    return;
                }
                long id = Long.parseLong(session.getUserDetails().get(session.KEY_ID));
                if (manager.checkPassword(id, passText)) {
                    if(!manager.existEmail(emailText)) {
                        long result = manager.updateEmail(id, emailText);
                        if(result ==1) {
                            Toast.makeText(changeEmail.this, "Email Changed", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(changeEmail.this, accountSettings.class);
                            startActivity(i);
                            finish();
                        }
                        else
                            Toast.makeText(changeEmail.this, "Email Edit Failed", Toast.LENGTH_LONG).show();
                    }
                    else{
                        email.setError("Email already exists");
                    }
                }
                else{
                    password.setError("Wrong password");
                }
            }
        });
    }

}


