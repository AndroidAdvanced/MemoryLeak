package com.app.mgu.memoryleak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 123 on 1/25/2018.
 */

public class Broad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.getApplicationContext();
    }
}
