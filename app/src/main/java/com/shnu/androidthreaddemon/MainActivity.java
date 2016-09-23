package com.shnu.androidthreaddemon;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Button button;
    ThreadB threadB;
    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_send_message);
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
        threadB = new ThreadB();
        threadB.start();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message  = Message.obtain();
                message.arg1 = 1;
                message.arg2 = 2;
                message.what = 3;
                message.obj = new String("I am from Main");
                threadB.handlerB.sendMessage(message);
            }
        });

    }

    public class ThreadB extends Thread {
        private Handler handlerB;

        @Override
        public void run() {
            super.run();
//            Looper.prepare();
            handlerB = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e("Who Am Receive", Thread.currentThread().getName());
                    Log.e("I am receive a message", msg.toString() + "");
                }
            };
//            Looper.loop();
        }
    }


    public class ThreadA extends Thread {
        @Override
        public void run() {
            super.run();
            Message message = new Message();
            message.arg1 = 4;
            message.arg2 = 5;
            message.what = 6;
            message.sendingUid = 1;
            Log.e("Who Am the Sender ?", Thread.currentThread().getName());
            message.obj = new String("I am from ThreadA");
            handler.sendMessage(message);
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
