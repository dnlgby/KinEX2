package com.example.threadbarzel;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.threadbarzel.models.User;
import com.example.threadbarzel.network.Api;

import java.io.IOException;

import retrofit2.Response;

public class MyThread extends Thread {

    private static final String TAG = "MyThread";
    private static final int GET_USER_CODE = 1;

    private Handler mThreadHandler;
    private Handler mMainHander;
    private Looper mLooper;
    private Api mApi;

    public MyThread(Api api)
    {
        mApi = api;
        //Init here will run on Main thread this handler will post on the MainLooper.
        mMainHander = new Handler();
    }

    @Override
    public void run() {
        Looper.prepare();
        prepareHandler();
        mLooper = Looper.myLooper();
        Looper.loop();
    }

    public void loadUserId(int userId, Runnable postExecuteRunnable)
    {
        Message message = Message.obtain(mThreadHandler, postExecuteRunnable);
        message.what = GET_USER_CODE;
        message.arg1 = userId;
        //Enqueue this message in the looper MessageQueue for later dispatch of the given handler - mThreadHandler
        message.sendToTarget();
    }

    @SuppressLint("HandlerLeak")
    // I am not worry about this leak because it's a class member and there is no any context inside.
    private void prepareHandler(){
        mThreadHandler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                // This if is not really necessary. since this is our handler, gets only our requests.
                if (msg.what == GET_USER_CODE) {
                    int userId = msg.arg1;
                    try {
                        Response<User> response = mApi.getUser(userId).execute();
                        if (response.isSuccessful()) {
                            User user = response.body();
                            MyRunnable cbk = ((MyRunnable) msg.getCallback());
                            cbk.setUser(user);
                            mMainHander.post(cbk);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void stopThread(){
        mLooper.quit();
    }

}
