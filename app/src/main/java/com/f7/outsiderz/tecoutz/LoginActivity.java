package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;

public class LoginActivity extends AppCompatActivity {



    private static Button signin;

    private static EditText email;
    private static EditText password;

    protected UserSessionManager session;
    UserManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        session = new UserSessionManager(getApplicationContext());
        manager = UserManager.getInstance(LoginActivity.this);

        signin = (Button) findViewById(R.id.btn_login);


        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);


        if(session.isUserLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();


                if(emailText.isEmpty()) {
                    email.setError("This field is required.");
                    return;
                }
                if(passwordText.isEmpty()){
                    password.setError("This field is required.");
                    return;
                }
                UserAcc user = manager.login(emailText, passwordText);
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login sucssessfull!", Toast.LENGTH_SHORT).show();
                    session.createUserLoginSession(user.getUserId(), user.getUserName());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });


        TextView signup = (TextView)findViewById(R.id.link_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signupIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(signupIntent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });



    }
}
