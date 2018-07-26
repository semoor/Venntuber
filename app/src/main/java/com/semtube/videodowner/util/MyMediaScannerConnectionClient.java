package com.semtube.videodowner.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.io.File;

public final class MyMediaScannerConnectionClient
        implements MediaScannerConnection.MediaScannerConnectionClient {

    private String mFilename;
    private String mMimetype;
    private MediaScannerConnection mConn;

    public MyMediaScannerConnectionClient
            (Context ctx, File file, String mimetype) {
        this.mFilename = file.getAbsolutePath();
        mMimetype=mimetype;
        mConn = new MediaScannerConnection(ctx, this);
        mConn.connect();
        Log.e("qozva","alma");
    }
    @Override
    public void onMediaScannerConnected() {
        mConn.scanFile(mFilename, mMimetype);
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        mConn.disconnect();
    }
}