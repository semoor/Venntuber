package com.semtube.videodowner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

public class Netwcont extends BroadcastReceiver {

    static boolean iv = false;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        isNetworkAvailable(context);

    }


    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        if(!iv){
                            iv = true;
                        }
                        return true;
                    }
                }
            }
        }
        iv = false;
        new AlertDialog.Builder(context)
                .setTitle("İnternet Bağlantınızı Yoxlayın!!!")
                .setMessage("İnterneti Açın və Proqrama Təzədən Girin")
                .setCancelable(false)
                .setPositiveButton("Oldu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .show();


        return false;
    }
}
