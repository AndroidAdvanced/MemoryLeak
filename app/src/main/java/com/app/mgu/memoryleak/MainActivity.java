package com.app.mgu.memoryleak;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RandomClass randomClass;

    static Object myInnerClass;

    class CustomHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    class CustomRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Run Runnable");
        }
    }

    class CustomTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("Custom Timer Task");
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }

    Thread t;

    public static TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        randomClass = RandomClass.getInstance(this);

        // 4.
        txt = (TextView) findViewById(R.id.txt);

        class MyInnerClass {

        }

        // 1.
        myInnerClass =  new MyInnerClass();

        // 2.
        /* new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        }.execute();*/
        // Dont make Anonymous class as it hold MainActivity Reference.

        new MyAsyncTask().execute();


        // 3.
        t = new Thread() {

            @Override
            public void run() {
                super.run();

                if(!interrupted()) {

                }
            }
        };
        t.start();

        // 5.
        // Here Created Anonymous Handler And Anonymous Runnable, Since it is Anonymous both of having reference of MainActivity
        /*new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        }.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);*/

        new CustomHandler().postDelayed(new CustomRunnable(), 1000);


        // 6.
        // Instead of using Anonymous TimerTask use CustomClass of TimerTask.
       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                    System.out.println("Timer Scheduled");
            }
        }, 1000);*/
       new Timer().schedule(new CustomTimerTask(),1000);

       //7 Use UnregisterSensor.

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        txt = null;
        t.interrupt();;
        // unregister Receiver.
    }
}
