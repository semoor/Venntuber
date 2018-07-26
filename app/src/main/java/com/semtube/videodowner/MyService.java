package com.semtube.videodowner;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.semtube.videodowner.util.MyMediaScannerConnectionClient;

import java.io.File;

public class MyService extends Service {
    static final int NOTIFICATION_ID = 543;

    Context context;
    FFmpeg ffmpeg;
    Boolean sc,cev,mq=false;
    Integer aid;
    Handler handler = new Handler();
    NotificationCompat.Builder abuilder;
    NotificationManager amanager;
    Integer aid2;
    String apatt,apatt2;
    Boolean bd;
    NotificationCompat.Builder abuilder2;
    NotificationManager amanager2;
    public static boolean isServiceRunning = false;
    public static final String C= "com.semtube.videodowner.SampleActivity";
    @Override
    public void onCreate() {
        super.onCreate();
        //startServiceWithNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction().equals(C)) {
            context = getApplicationContext();
           /*if(mq && intent.getIntExtra("id",800)!=aid){
                apatt2=intent.getStringExtra("pat");
                bd=intent.getBooleanExtra("bd",false);
                aid2=intent.getIntExtra("id",800);

                abuilder2= (NotificationCompat.Builder) ((ObjectWrapperForBinder)intent.getExtras().getBinder("object_value")).getData();

                amanager2= (NotificationManager) ((ObjectWrapperForBinder2)intent.getExtras().getBinder("object_value2")).getData();

                abuilder2.setContentText("Please wait!");
                amanager2.notify(aid2, abuilder2.build());
                Log.e("bambli",apatt2);
                handler.postDelayed(new Runnable(){
                    public void run(){
                        //do something
                        if(!mq){
                            startServiceWithNotification(apatt2,abuilder2,amanager2,aid2);

                        }
                        else
                            handler.postDelayed(this, 4000);
                    }
                }, 4000);

            }
           else {

           }*/
            /*if(intent.getBooleanExtra("bd",true)){
                abuilder= (NotificationCompat.Builder) ((ObjectWrapperForBinder)intent.getExtras().getBinder("object_value")).getData();
                amanager= (NotificationManager) ((ObjectWrapperForBinder2)intent.getExtras().getBinder("object_value2")).getData();

            }else {
                abuilder= (NotificationCompat.Builder) ((ObjectWrapperForBinder3)intent.getExtras().getBinder("object_value")).getData();

                amanager= (NotificationManager) ((ObjectWrapperForBinder4)intent.getExtras().getBinder("object_value2")).getData();

            }*/
                apatt=intent.getStringExtra("pat");
                aid=intent.getIntExtra("id",0);
               // bd=intent.getBooleanExtra("bd",true);

/*
                abuilder.setContentText("Please wait...");
                amanager.notify(aid, abuilder.build());*/
                Log.e("patt",apatt);

                startServiceWithNotification(apatt,abuilder,amanager,aid);
           // }


        }
        else stopMyService();
        return START_STICKY;
    }

    // In case the service is deleted or crashes some how
    @Override
    public void onDestroy() {
        isServiceRunning = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case of bound services.
        return null;
    }


    void startServiceWithNotification(String patt,NotificationCompat.Builder builder,NotificationManager manager,Integer id) {
        mq=true;
        if (isServiceRunning) return;
        isServiceRunning = true;

        Intent notificationIntent = new Intent(getApplicationContext(),SampleActivity.class);
        notificationIntent.setAction(C);  // A string containing the action name
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Thank You...")
              //  .setSmallIcon(R.drawable.my_icon)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(contentPendingIntent)
                .setOngoing(true)
//                .setDeleteIntent(contentPendingIntent)  // if needed
                .build();
        notification.flags = notification.flags | Notification.FLAG_NO_CLEAR;     // NO_CLEAR makes the notification stay when the user performs a "delete all" command
        startForeground(NOTIFICATION_ID, notification);
        testo(patt,builder,manager,id);

    }

    void stopMyService() {
        stopForeground(true);
        
        isServiceRunning = false;
               SampleActivity.cev(false);
               mq=false;
               stopSelf();
    }

    public void testo(final String pat, final NotificationCompat.Builder builder, final NotificationManager manager, final Integer id){
        mq=true;
        if (ffmpeg == null) {
            Log.d("log", "ffmpeg : era nulo");
            ffmpeg = FFmpeg.getInstance(getApplicationContext());
        }
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    Log.d("log", "ceta");
                    cev=false;

                }

                @Override
                public void onSuccess() {
                    Log.d("log", "ffmpeg : correct Loaded");
                    //cev=true;
                }
            });
        }
        catch (Exception e) {
            Log.d("log", "EXception no controlada : " + e);
        }


        // ffmpeg = FFmpeg.getInstance(context);
        try {

            String[] complexCommand = {"-y", "-i",(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat)).getAbsolutePath()  ,"-vn", "-ar", "44100", "-ac", "2", "-b:a", "320k", "-f", "mp3", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat.replaceAll("3gp","mp3"))).getAbsolutePath()};
            ffmpeg.execute(complexCommand, new ExecuteBinaryResponseHandler() {

                @Override
                public void onStart() {
                    Log.e("gc", "Serves Command Started");
                }

                @Override
                public void onProgress(String message) {
                    Log.e("gc", "onProgress" + message);
                }

                @Override
                public void onFailure(String message) {
                    Log.e("gc", "onFailure command" + message);
                    cev=false;
                    mq=false;
                           //SampleActivity.cev(false);
                           //stopSelf();
                }

                @Override
                public void onSuccess(String message) {
                    Log.e("gc", "onSuccess command" + message);
                    cev=true;

                }

                @Override
                public void onFinish() {
                    Log.e("gc", "onFinish command ");
                    sc=true;
                    Log.e("scs", String.valueOf(sc));
                    if(cev){

                        try {
                            Uri uri = FileProvider.getUriForFile(context,
                                    BuildConfig.APPLICATION_ID + ".provider", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat)));


                            ContentResolver contentResolver = context.getContentResolver();
                            contentResolver.delete(uri, null, null);
                            Log.e("gc oldu", "delete");

                        }
                        catch (Exception e){
                            Log.e("gc error", e.toString());
                        }

                        MediaScannerConnection.MediaScannerConnectionClient client =
                                new MyMediaScannerConnectionClient(
                                        context,  (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat.replaceAll("3gp","mp3"))),  "audio/mp3");
                              //     stopSelf();

                        SampleActivity.son(context,pat.replaceAll("3gp","mp3"),id);

                        /*builder.setTicker("Download Completed");

                        Uri uri = FileProvider.getUriForFile(context,
                                BuildConfig.APPLICATION_ID + ".provider", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat.replaceAll("3gp","mp3"))));



                        builder.setContentText("Download Completed");
                        builder.setAutoCancel(true);
                        builder.setProgress(1, 1, false);

                        //   String last=ad.substring(ad.length()-1,ad.length());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setDataAndType(uri, "audio/mp3").setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        final PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        builder.setContentIntent(contentIntent);

                        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED);
                        intentFilter.addDataScheme("file");
                        manager.notify(id, builder.build());*/
                        mq=false;
                        SampleActivity.cev(false);
                        stopMyService();
                    }
                    else{
                        try {
                            Uri uri = FileProvider.getUriForFile(context,
                                    BuildConfig.APPLICATION_ID + ".provider", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pat)));


                            ContentResolver contentResolver = context.getContentResolver();
                            contentResolver.delete(uri, null, null);
                            Log.e("gc oldu", "delete");

                        }
                        catch (Exception e){
                            Log.e("gc error", e.toString());
                        }

                       SampleActivity.bos(context,pat.replaceAll("3gp","mp3"),id);
                      /*  builder.setTicker("Sorry, Error Occured");




                        builder.setContentText("Sorry, Error Occured");
                        builder.setAutoCancel(true);
                        builder.setProgress(0, 0, false);

                        //   String last=ad.substring(ad.length()-1,ad.length());

                        manager.notify(id, builder.build());*/
                        mq=false;
                      //  SampleActivity.cev(false);
                        stopMyService();
                    }


                }
            });

        } catch (FFmpegCommandAlreadyRunningException e) {
            // Handle if FFmpeg is already running
            e.printStackTrace();
            //SampleActivity.cev(false);
            mq=false;
            stopMyService();
        }



    }
}