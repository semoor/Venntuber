package com.semtube.videodowner;

import android.os.Binder;

public class ObjectWrapperForBinder2 extends Binder {

    private final Object mData;

    public ObjectWrapperForBinder2(Object data) {
        mData = data;
    }

    public Object getData() {
        return mData;
    }
}