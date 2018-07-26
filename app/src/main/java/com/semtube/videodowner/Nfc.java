package com.semtube.videodowner;

import android.app.NotificationManager;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

public class Nfc {
    Integer id;
    NotificationCompat.Builder builder;
    NotificationManager manager;

    public Nfc(Integer id, NotificationCompat.Builder builder, NotificationManager manager) {
        this.id = id;
        this.builder = builder;
        this.manager = manager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotificationCompat.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(NotificationCompat.Builder builder) {
        this.builder = builder;
    }

    public NotificationManager getManager() {
        return manager;
    }

    public void setManager(NotificationManager manager) {
        this.manager = manager;
    }
}
