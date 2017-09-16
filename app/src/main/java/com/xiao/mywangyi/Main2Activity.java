package com.xiao.mywangyi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    private int num =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //2s 跳转到主页面

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        num --;//依次递减
                        if(num == 0) {
                            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });

            }
        };
        timer.schedule(task,1000,1000);
    }
}
