package com.xiayu.messagerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * 创建者     罗夏雨
 * 创建时间   2017/2/22 19:24
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class XiayuService extends Service {

    private final static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Bundle data = msg.getData();

            //data.getSerializable()
            //data.getParcelable()


            System.out.println("地瓜地瓜,我是土豆,我是土豆, 听到请回答,听到请回答");
            //获取Messager
            Messenger messenger = msg.replyTo;
            //创建消息
            Message msg_reply = Message.obtain();
            try {
                //发送
                messenger.send(msg_reply);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private final static Messenger mMessenger = new Messenger(mHandler);


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("------onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("------onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
