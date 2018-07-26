
package com.semtube.videodowner.util;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.ServiceCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.semtube.videodowner.SampleActivity;
import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.semtube.videodowner.BuildConfig;
import com.semtube.videodowner.R;

import java.io.File;
import java.util.List;
import java.util.Map;

public class UNotificationEsas extends DownloadListener4WithSpeed {
    private int totalLength;

   NotificationCompat.Builder builder;
   NotificationManager manager;
   Runnable taskEndRunnable;
   Context context;

    String readableTotalLength;
    String ad,adp;
    Boolean mp,cev=false,sc=false;
    FFmpeg ffmpeg;
    Boolean mm=false;
    Integer say=0;
    Intent inten;
    NotificationCompat.Action action;

    public UNotificationEsas(Context context, String ad,Boolean mp) {
        this.context = context.getApplicationContext();
        this.ad=ad;
        this.mp=mp;
    }

    public void attachTaskEndRunnable(Runnable taskEndRunnable) {
        this.taskEndRunnable = taskEndRunnable;
    }

    public void releaseTaskEndRunnable() {
        taskEndRunnable = null;
    }

    public void setAction(NotificationCompat.Action action) {
        this.action = action;
    }

    public void initNotification() {
        if(mp)
            adp=ad.replaceAll("3gp","mp3");
        else
            adp=ad;


        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        final String channelId = "okdownload";
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            final NotificationChannel channel = new NotificationChannel(
                    channelId,
                    ad,
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);

        }

        builder = new NotificationCompat.Builder(context, channelId);


        builder
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setOngoing(true)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle(adp)
                .setContentText("Downloading...")
                .setSmallIcon(R.drawable.ic_file_download_black_24dp);

        if (action != null) {
            builder.addAction(action);
        }
    }

    @Override public void taskStart(@NonNull DownloadTask task) {
        builder.setTicker("Downloading...");
        builder.setOngoing(true);

        builder.setContentText(adp);
        builder.setProgress(0, 0, true);
        manager.notify(task.getId(), builder.build());
    }

    @Override
    public void connectStart(@NonNull DownloadTask task, int blockIndex,
                             @NonNull Map<String, List<String>> requestHeaderFields) {
             builder.setTicker("Download Started");
        builder.setContentText(ad);
        builder.setProgress(0, 0, true);
        manager.notify(task.getId(), builder.build());
    }

    @Override
    public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode,
                           @NonNull Map<String, List<String>> responseHeaderFields) {
        builder.setTicker("Downloading...");
        builder.setContentText("Please Wait...");
        builder.setProgress(0, 0, true);
        manager.notify(task.getId(), builder.build());
    }

    @Override
    public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info,
                          boolean fromBreakpoint,
                          @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {

        if (fromBreakpoint) {
            builder.setTicker("fromBreakpoint");
        } else {
            builder.setTicker("fromBeginning");
        }
        builder.setContentText(
                "This task is yukin fromBreakpoint[" + fromBreakpoint + "]");
        builder.setProgress((int) info.getTotalLength(), (int) info.getTotalOffset(), true);
        manager.notify(task.getId(), builder.build());

        totalLength = (int) info.getTotalLength();
    }

    @Override
    public void progressBlock(@NonNull DownloadTask task, int blockIndex,
                              long currentBlockOffset,
                              @NonNull SpeedCalculator blockSpeed) {
    }

    @Override public void progress(@NonNull DownloadTask task, long currentOffset,
                                   @NonNull SpeedCalculator taskSpeed) {
        final String readableOffset = Util.humanReadableBytes(currentOffset, true);
        readableTotalLength = Util.humanReadableBytes(totalLength, true);
        final String progressStatus = readableOffset + "/" + readableTotalLength;
        final String speed = taskSpeed.speed();
        final String progressStatusWithSpeed = progressStatus + " ( speed: " + speed + " )";
        //builder.setContentText("Downloading with speed: " + taskSpeed.speed());
        builder.setContentText(progressStatusWithSpeed);
        builder.setProgress(totalLength, (int) currentOffset, false);
        manager.notify(task.getId(), builder.build());
    }

    @Override
    public void blockEnd(@NonNull DownloadTask task, int blockIndex, BlockInfo info,
                         @NonNull SpeedCalculator blockSpeed) {  manager.cancelAll();
    }

    @Override
    public void taskEnd(@NonNull final DownloadTask task, @NonNull EndCause cause,
                                  @android.support.annotation.Nullable Exception realCause,
                                  @NonNull SpeedCalculator taskSpeed) {
        builder.setOngoing(false);



          if (cause == EndCause.COMPLETED) {

              //MediaScannerConnection.scanFile(context, new String[]{(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getPath()}, new String[]{"mp3/*"}, null);
             if(!mp){
                 builder.setTicker("Download Completed");
                 Uri uri = FileProvider.getUriForFile(context,
                         BuildConfig.APPLICATION_ID + ".provider", (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), ad)));

                 builder.setContentText("Download Completed: " + Util.humanReadableBytes(totalLength, true));

                 builder.setProgress(1, 1, false);

                 //   String last=ad.substring(ad.length()-1,ad.length());
                 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                 intent.setDataAndType(uri, getMimeType(uri)).setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                 final PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                 builder.setContentIntent(contentIntent);

                 IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_FINISHED);
                 intentFilter.addDataScheme("file");

                 MediaScannerConnection.MediaScannerConnectionClient client =
                         new MyMediaScannerConnectionClient(
                                 context,  (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), ad)),  getMimeType(uri));
                 }
                 else{
               //  ServiceCompat.stopForeground( true );
                 mm=true;
                // manager.cancel(task.getId());
                /* Intent serviceIntent;
                 serviceIntent = new Intent(context, Serves.class);
                 //testo(ad,builder);

                 serviceIntent.putExtra("pat", ad);
                 context.startService(serviceIntent);*/
                 builder.setContentText("Please wait..");
                SampleActivity.serve(context,ad,builder,manager,task.getId());
              //   builder.setContentText("Please wait...");






             }
          }
        else if (cause == EndCause.ERROR) {
               builder.setTicker("Error");
              builder.setContentText("Sorry, Error");
              builder.setProgress(0, 0, false);

        }else if (cause == EndCause.CANCELED) {
               builder.setTicker("Download Cancelled");

               builder.setContentText("Download Cancelled");

        }
        else {
              builder.setTicker("Downloading " + cause);
              builder.setContentText("Downloading " + cause);
          }



        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override public void run() {

                if(!mm){
                    if (taskEndRunnable != null)
                        taskEndRunnable.run();




                    manager.notify(task.getId(), builder.build());
                }
               /* else
                    manager.cancelAll();*/



            }
        }, 100);
    }

    public String getMimeType(Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        }
        else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        }
        return mimeType;
    }

    public void testo(final String pat, final NotificationCompat.Builder builde){

            if (ffmpeg == null) {
                Log.d("log", "ffmpeg : era nulo");
                ffmpeg = FFmpeg.getInstance(context);
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
                    Log.e("gc", "Command Started");
                }

                @Override
                public void onProgress(String message) {
                    Log.e("gc", "onProgress" + message);
                }

                @Override
                public void onFailure(String message) {
                    Log.e("gc", "onFailure command" + message);
                    cev=false;
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


                    }


                }
            });

        } catch (FFmpegCommandAlreadyRunningException e) {
            // Handle if FFmpeg is already running
            e.printStackTrace();
        }



    }

}
