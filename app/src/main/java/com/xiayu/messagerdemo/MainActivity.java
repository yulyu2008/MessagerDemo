package com.xiayu.messagerdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private Intent mIntent;
    private MyServiceConnection mMyServiceConnection;

    private final static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            System.out.println("土豆,土豆,我是地瓜,我已收到你的消息");
        }
    };

    private final static Messenger mReplyMessager = new Messenger(mHandler);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bind(View v) {
        Intent    mIntent = new Intent(MainActivity.this, XiayuService.class);
        MyServiceConnection  mMyServiceConnection = new MyServiceConnection();
        bindService(mIntent, mMyServiceConnection, BIND_AUTO_CREATE);
    }

    public void unbind(View v) {

    }
    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);

            Message msg = Message.obtain();

            Bundle bundle = new Bundle();

            //传输序列化对象
            //bundle.putParcelable();

            //bundle.putSerializable();

            msg.setData(bundle);

            //通过msg把客户端的Messager传送到服务器端(关键代码)
            msg.replyTo =mReplyMessager;

            try {

                messenger.send(msg);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
