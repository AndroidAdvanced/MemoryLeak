package com.app.mgu.memoryleak;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by 123 on 1/25/2018.
 */
public class MyCustomApplication extends Application {

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {

        MyCustomApplication application = (MyCustomApplication) context.getApplicationContext();
        return  application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        refWatcher = LeakCanary.install(this);
    }
}
