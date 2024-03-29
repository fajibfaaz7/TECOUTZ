package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.Message;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;

/**
 * Created by fajibfaaz on 20/04/17.
 */
public class ViewMessage extends AppCompatActivity {

    TextView from;
    TextView to;
    TextView title;
    TextView content;
    UserManager manager;
    Bundle bundle;
    Message m;

    private static Button replyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        bundle = getIntent().getExtras();

        manager = UserManager.getInstance(this);
        m = manager.getMessage(bundle.getLong("msgId"));

        from = (TextView) findViewById(R.id.view_message_from);
        to = (TextView) findViewById(R.id.view_message_to);
        title = (TextView) findViewById(R.id.view_message_title);
        content = (TextView) findViewById(R.id.view_message_content);

        final String recName = manager.getUser(m.getReceiverId()).getUserName();
        final String senderName =  manager.getUser(m.getSenderId()).getUserName();

        from.setText(from.getText().toString() + senderName);
        to.setText(to.getText().toString() + recName);
        title.setText(m.getHeading());
        content.setText(m.getText());


        replyMessage = (Button)findViewById(R.id.view_message_reply_button);
        replyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewMessage.this, SendMessage.class);
                i.putExtra("id", m.getSenderId());
                i.putExtra("Username", senderName);
                i.putExtra("Title", "RE: " + m.getHeading());
                startActivity(i);
                finish();

            }
        });
    }

}
