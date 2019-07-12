package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;


public class changePassword extends AppCompatActivity {

    protected UserSessionManager session;
    private static Button editPassword;
    private static EditText oldPass;
    private static EditText newPass;
    private static EditText confirmPass;

    UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        manager = UserManager.getInstance(this);
        session = new UserSessionManager(this);
        oldPass = (EditText) findViewById(R.id.edit_password_old);
        newPass = (EditText) findViewById(R.id.edit_password_new);
        confirmPass = (EditText) findViewById(R.id.edit_password_confirm);
        editPassword = (Button)findViewById(R.id.edit_password_button_save);
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassTxt = oldPass.getText().toString();
                String newPassTxt = newPass.getText().toString();
                String confirmTxt = confirmPass.getText().toString();
                long id = Long.parseLong(session.getUserDetails().get(session.KEY_ID));

                if(oldPassTxt.isEmpty()) {
                    oldPass.setError("This field is required.");
                    return;
                }
                if(newPassTxt.isEmpty()) {
                    newPass.setError("This field is required.");
                    return;
                }
                else if(confirmTxt.isEmpty()) {
                    confirmPass.setError("This field is required.");
                    return;
                }
                // check if old password is correct
                else if(manager.checkPassword(id, oldPassTxt)){
                    // check strength of new password
                    if (!manager.checkPasswordStrength(newPassTxt)) {
                        newPass.setError("Password is too weak\n At least 8 symbols \n At least 1 lowercase and uppercase \n At least 1 number");
                        return;
                    }
                    else if (!newPassTxt.equals(confirmTxt)) {
                        confirmPass.setError("Passwords don't match");
                        return;
                    }
                    else {
                        // change password
                        long result = manager.updatePassword(id, newPassTxt);
                        Toast.makeText(changePassword.this, (result == 1) ? "Password Changed" : "Password Edit Failed", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(changePassword.this, accountSettings.class);
                        startActivity(i);
                        finish();
                    }
                }
                else{
                    Toast.makeText(changePassword.this, "Wrong password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
