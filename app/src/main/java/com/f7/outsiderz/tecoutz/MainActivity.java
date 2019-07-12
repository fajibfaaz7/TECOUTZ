package com.f7.outsiderz.tecoutz;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.Dbpart.UserSessionManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    protected UserSessionManager session;
    BottomBar mbottomBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mbottomBar = BottomBar.attach(this,savedInstanceState);
        mbottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {


            @Override
            public void onMenuTabSelected(@IdRes int i) {
                if (i==R.id.Tecstore)
                {
                    tecstorefragment f = new tecstorefragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if (i==R.id.Tecxchange)
                {
                    tecxchangefragment f = new tecxchangefragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if (i==R.id.AboutUs)
                {
                    AboutUsfragment f = new AboutUsfragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }

                else if (i==R.id.Profile)
                {
                    profilefragment f = new profilefragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }



            }


            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {


            }
        });
        mbottomBar.mapColorForTab(0, "#8B0000");
        mbottomBar.mapColorForTab(1, "#502369");
        mbottomBar.mapColorForTab(2, "#162b1c");
        mbottomBar.mapColorForTab(3, "#2a1a15");


  //      BottomBarBadge unread;
    //    unread = mbottomBar.makeBadgeForTabAt(2, "#FF0000", 13);
      //  unread.show();

    }
    public void buttonClick(View v) {
        switch (v.getId()) {
            case R.id.myaccount:
                Intent accIntent = new Intent();
                accIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.ViewUser");
                startActivity(accIntent);
                break;
            case R.id.storeButton:
                Intent strIntent = new Intent();
                strIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.storeActivity");
                startActivity(strIntent);
                break;

            case R.id.imageButton:
                Intent myIntent = new Intent();
                myIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.Home");
                startActivity(myIntent);
                break;

            case R.id.myoffers:
                Intent offIntent = new Intent();
                offIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.MyOffers");
                startActivity(offIntent);
                break;
            case R.id.fab:
                Intent messIntent = new Intent();
                messIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.MyMessages");
                startActivity(messIntent);
                break;
            case R.id.help:
                Intent helpIntent = new Intent();
                helpIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.HelpActivity");
                startActivity(helpIntent);
                break;
            case R.id.quick:
                Intent qkIntent = new Intent();
                qkIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.A4Activity");
                startActivity(qkIntent);
                break;
            case R.id.pen:
                Intent pIntent = new Intent();
                pIntent.setClassName("com.f7.outsiderz.tecoutz", "com.f7.outsiderz.tecoutz.PenActivity");
                startActivity(pIntent);
                break;


        }
    }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

        case R.id.settingsgear:
            Intent accsettIntent = new Intent(MainActivity.this, accountSettings.class);

            startActivity(accsettIntent);
                return true;
        case R.id.offeraddbutt:

            Intent offbuttIntent = new Intent(MainActivity.this, AddOffer.class);

            startActivity(offbuttIntent);


            return true;

    }
    return onOptionsItemSelected(item);
}
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }




}


