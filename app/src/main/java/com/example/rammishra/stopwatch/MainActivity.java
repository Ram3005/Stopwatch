package com.example.rammishra.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean running;
    private int seconds=0;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("sec");
            running=savedInstanceState.getBoolean("running");
        }
        settimer();


    }
    public void startstopwatch(View view)
    {
        running=true;
    }
    public void stopstopwatch(View view)
    {
        running=false;
    }
    public void resetstopwatch(View view)
    {
        running=false;
        seconds=0;
    }
    private void settimer () {
        final TextView timeview = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             int hours = seconds / 3600;
                             int mins = (seconds % 3600) / 60;
                             int secs = seconds % 60;
                             String time = String.format("%d:%02d:%02d", hours, mins, secs);
                             timeview.setText(time);
                             if (running) {
                                 seconds++;
                             }
                             handler.postDelayed(this, 1000);
                         }

                     }
        );
    }



    @Override
    public void onSaveInstanceState(Bundle abcd) {
        super.onSaveInstanceState(abcd);
        abcd.putInt("sec",seconds);
        abcd.putBoolean("running",running);
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasrunning=running;
        running=false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasrunning)
        {
            running=true;
        }
    }
}
