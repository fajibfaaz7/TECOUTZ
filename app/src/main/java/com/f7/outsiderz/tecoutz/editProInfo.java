package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;


public class editProInfo extends AppCompatActivity  {


    protected UserSessionManager session;
    private Button editInfo;
    private EditText uname;
    private EditText semester;
    private EditText phone;
    private long id;
    private UserAcc user;

    UserManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pro_info);

        session = new UserSessionManager(this);
        manager = UserManager.getInstance(this);
        id = Long.parseLong(session.getUserDetails().get(session.KEY_ID));
        user = manager.getUser(id);

        uname = (EditText) findViewById(R.id.edit_personal_fname);
        phone = (EditText) findViewById(R.id.edit_personal_phone);
        semester = (EditText) findViewById(R.id.edit_personal_sem);
        editInfo = (Button) findViewById(R.id.edit_personal_info_save_button);
        fillData(user);
    }

    public void updateInfo(View v){
        String userName = uname.getText().toString();
        String semText = semester.getText().toString();
        String phoneTxt = phone.getText().toString();

        long result = manager.updatePersonalInfo(id, userName, phoneTxt, semText);
        Toast.makeText(editProInfo.this, (result == 1) ? "Updated Successfully" : "Update Failed", Toast.LENGTH_SHORT).show();
    }


    private void fillData(UserAcc user){
        uname.setText(user.getUserName());

        phone.setText(user.getPhoneNumber());
        semester.setText(user.getSemester());

    }


}
