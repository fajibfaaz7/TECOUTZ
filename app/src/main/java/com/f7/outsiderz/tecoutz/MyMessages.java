package com.f7.outsiderz.tecoutz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;

import com.f7.outsiderz.tecoutz.Dbpart.Message;
import com.f7.outsiderz.tecoutz.Dbpart.UserManager;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;
import com.f7.outsiderz.tecoutz.adapters.MessageAdapter;

import java.util.ArrayList;

public class MyMessages extends AppCompatActivity {

    TabHost tabHost;
    ArrayList<Message> sentMessages;
    ArrayList<Message> receivedMessages;
    RecyclerView receivedView;
    RecyclerView sentView;
    UserManager manager;
    protected UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_messages);
        manager = UserManager.getInstance(this);
        session = new UserSessionManager(this);

        sentMessages = manager.getSentMessages(Long.parseLong(session.getUserDetails().get(session.KEY_ID)));
        receivedMessages = manager.getReceivedMessages(Long.parseLong(session.getUserDetails().get(session.KEY_ID)));

        receivedView = (RecyclerView) findViewById(R.id.messages_received);
        receivedView.setLayoutManager(new LinearLayoutManager(this));

        sentView = (RecyclerView) findViewById(R.id.messages_send);
        sentView.setLayoutManager(new LinearLayoutManager(this));

        MessageAdapter sentAdapter = new MessageAdapter(this, sentMessages);
        MessageAdapter receivedAdapter = new MessageAdapter(this, receivedMessages);

        receivedView.setAdapter(receivedAdapter);
        sentView.setAdapter(sentAdapter);




        tabHost = (TabHost) findViewById(R.id.messages_tab_host);
        tabHost.setup();

        TabHost.TabSpec received = tabHost.newTabSpec("RECEIVED");
        received.setIndicator("RECEIVED");

        received.setContent(R.id.received);
        tabHost.addTab(received);

        //tab 2 etc...
        TabHost.TabSpec sent = tabHost.newTabSpec("SENT");
        sent.setIndicator("SENT");
        sent.setContent(R.id.sent);
        tabHost.addTab(sent);

    }

}

