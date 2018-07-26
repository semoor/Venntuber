package com.semtube.videodowner;

import android.os.Binder;

public class ObjectWrapperForBinder4 extends Binder {

    private final Object mData;

    public ObjectWrapperForBinder4(Object data) {
        mData = data;
    }

    public Object getData() {
        return mData;
    }
}