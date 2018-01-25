package com.app.mgu.memoryleak;

import android.content.Context;

/**
 * Created by 123 on 1/25/2018.
 */

public class RandomClass {

    public static RandomClass randomClass;

    private Context mContext;

    RandomClass(Context mContext) {
        this.mContext = mContext;
    }

    public static RandomClass getInstance(Context mContext) {
        if(randomClass == null) {
            randomClass = new RandomClass(mContext);
        }
        return randomClass;
    }
}
