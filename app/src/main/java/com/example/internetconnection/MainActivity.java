package com.example.internetconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inspector.PropertyReader;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    Button checkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBtn=findViewById(R.id.check);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()){
                    Toast.makeText(MainActivity.this, "it is connected", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "it is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });



        // broadcastReceiver=new InternetReciver();
       //  registerNetwork();
    }
    public boolean isConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo !=null) {
           if(networkInfo.isConnected())
               return true;
        else

               return false;

        }
        else
        {
           return false;
        }


    }



    ////here for normal internet
    protected  void registerNetwork(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.EXTRA_NO_CONNECTIVITY));
        }
    }
    protected void unregisterNetwork(){
      try {
          unregisterReceiver(broadcastReceiver);
      }catch (IllegalArgumentException e){
          e.printStackTrace();
      }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetwork();
    }
}