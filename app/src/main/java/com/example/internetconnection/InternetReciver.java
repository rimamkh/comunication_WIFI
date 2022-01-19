package com.example.internetconnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    if(isConnected(context)){
        Toast.makeText(context, "internet connected", Toast.LENGTH_SHORT).show();
    }else
    {
        Toast.makeText(context, "internet not connected", Toast.LENGTH_SHORT).show();
    }
    }
    public boolean isConnected(Context context){
        try{
            ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }



    }
}
