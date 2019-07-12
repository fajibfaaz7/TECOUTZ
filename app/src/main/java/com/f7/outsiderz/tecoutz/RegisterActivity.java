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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private UserManager userManager;

    private static Button register;

    private static EditText username;
    private static EditText password;
    private static EditText confirmpassword;
    private static EditText email;
    private static EditText semester;
    private static EditText phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userManager = UserManager.getInstance(this);

        register = (Button) findViewById(R.id.btn_signup);

        username        = (EditText) findViewById(R.id.input_name);
        password        = (EditText) findViewById(R.id.input_passwordr);
        confirmpassword = (EditText) findViewById(R.id.input_reEnterPasswordr);
        email           = (EditText) findViewById(R.id.input_emailr);
        semester        = (EditText) findViewById(R.id.input_semester);
        phone           = (EditText) findViewById(R.id.input_mobile);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String emailTxt = email.getText().toString();
                String semesterTxt = semester.getText().toString();
                String phoneTxt = phone.getText().toString();

                boolean usernameCheck = false;
                boolean emailCheck = false;
                boolean passwordCheck = false;
                boolean passwordMatch = false;

                if(userManager.existUsername(usernameTxt)){
                    username.setError("Username already exists");
                }
                else if(usernameTxt.isEmpty()){
                    username.setError("This field is required");
                } else
                    usernameCheck = true;

                if (userManager.existEmail(emailTxt)){
                    email.setError("Email already exists");
                }
                else if(emailTxt.isEmpty()){
                    email.setError("This field is required");
                }
                else if(!isEmailValid(emailTxt)){
                    email.setError("Please enter a valid email");
                }
                else
                    emailCheck = true;

                if(password.getText().toString().isEmpty()) {
                    password.setError("This field is required.");
                } else {
                    if (!userManager.checkPasswordStrength(password.getText().toString())) {
                        password.setError("Password is too weak\n At least 8 symbols \n At least 1 lowercase and uppercase \n At least 1 number");
                    } else
                        passwordCheck = true;

                    if (userManager.checkPasswordStrength(password.getText().toString()) && !password.getText().toString().equals(confirmpassword.getText().toString())) {
                        confirmpassword.setError("Passwords don't match");
                    } else
                        passwordMatch = true;
                }

                if(usernameCheck && emailCheck && passwordCheck && passwordMatch){
                    UserAcc user = new UserAcc(emailTxt, passwordTxt, usernameTxt, phoneTxt, semesterTxt);
                    long id = userManager.register(user);
                    if(id != -1)
                        Toast.makeText(getApplicationContext(), "Register successful" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });


        TextView login = (TextView)findViewById(R.id.link_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);

                startActivity(loginIntent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
