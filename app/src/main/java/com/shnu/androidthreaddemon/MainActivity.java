package com.shnu.androidthreaddemon;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.shnu.androidthreaddemon.multhread.MainApp;
import com.shnu.androidthreaddemon.resume.ResumeDownActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Button button,btnMulThreadDown,btnResumeDownload;
    ThreadA threadA;
    ThreadB threadB;
    Message message;
    private Handler handlerB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_send_message);
        btnMulThreadDown = (Button)findViewById(R.id.btn_mul_thread_download);
        btnResumeDownload = (Button)findViewById(R.id.btn_resume_download);
        btnMulThreadDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainApp.class));
            }
        });
        btnResumeDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ResumeDownActivity.class));
            }
        });
//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Log.e("Who Am Receive",Thread.currentThread().getName());
//                Log.e("I am receive a message",msg.toString()+"");
//            }
//        };
//        ThreadA threadA = new ThreadA();
//        threadA.start();
//        Message message = new Message();
//        message.arg1 = 1;
//        message.arg2 = 2;
//        message.what = 3;
//        message.obj = new String("I am from Main");

//        handler.sendMessage(message);
//        threadB = new ThreadB();
//        threadB.start();
//        threadA = new ThreadA();
//        threadA.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message  = new Message();
                message.arg1 = 1;
                message.arg2 = 2;
                message.what = 3;
                message.obj = new String("I am from Main");
                handlerB.sendMessage(message);
            }
        });
    }
    public class ThreadB extends Thread {

//        handlerB= new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Log.e("Who Am Receive", Thread.currentThread().getName());
//                Log.e("I am receive a message", msg.toString() + "");
//            }
//        };
        @Override
        public void run() {
            super.run();
            Looper.prepare();
//            try {
//                sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            handlerB = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    super.handleMessage(msg);

                    Log.e("Who Am Receive", Thread.currentThread().getName());
                    Log.e("I am receive a message", msg.toString() + "");
                }
            };
            Looper.loop();
        }
    }


    public class ThreadA extends Thread {
        @Override
        public void run() {
            super.run();
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Message message = new Message();
            message.arg1 = 4;
            message.arg2 = 5;
            message.what = 6;
            message.sendingUid = 1;
            Log.e("Who Am the Sender ?", Thread.currentThread().getName());
            message.obj = new String("I am from ThreadA");
            handlerB.sendMessage(message);
        }
    }


//    Thread thread1 = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            Message message = new Message();
//            message.arg1 = 4;
//            message.arg2 = 5;
//            message.what = 6;
//            message.sendingUid = 1;
//            message.obj = new String("I am from thread1");
//            handler.sendMessage(message);
//        }
//    });
//    thread1.start();

}
