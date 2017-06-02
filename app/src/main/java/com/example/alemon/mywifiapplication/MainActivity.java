package com.example.alemon.mywifiapplication;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private static final int TCP_TEXT_1=1;
    private static final int TCP_TEXT_2=2;
    WifiManager wifiManager;
    ImageButton ib_Top,ib_Bottom,ib_M,ib_One,ib_Two,ib_Three;
    OutputStream os;//输出数据到服务器的对象
    Socket client=null;//网络接口对象
    TextView textView,textView_1;//静态文本类
    DhcpInfo dhcp;//服务器信息类
    String str;


    //发送数据函数，一切控制都由此产生，是本程序最重要的方法
    void send(byte[] ch)
    {
        try {
            client=new Socket(str,8080);//连接服务器，IP随热点，端口是5000
            os=client.getOutputStream();//初始化输出
            os.write(ch);//输出数据
            os.close();//关闭输出
            client.close();//关闭网络
            //下面4句是系统自动添加的
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        bt1=(Button)this.findViewById(R.id.bt1);
//        bt2=(Button)this.findViewById(R.id.bt2);
        /**
         *  定义相关View
        */
        ib_Top=(ImageButton)this.findViewById(R.id.ib_Top);
        ib_Bottom=(ImageButton)this.findViewById(R.id.ib_Bottom);
        ib_M=(ImageButton)this.findViewById(R.id.ib_M);
        ib_One=(ImageButton)this.findViewById(R.id.ib_One);
        ib_Two=(ImageButton)this.findViewById(R.id.ib_Two);
        ib_Three=(ImageButton)this.findViewById(R.id.ib_Three);

        textView=(TextView)this.findViewById(R.id.textView1) ;
//        textView_1=(TextView)this.findViewById(R.id.textView2);




        /**
         *  Handler 用于在子线程中更新UI
         */
        /*
        final Handler handler=new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case TCP_TEXT_1:{
//                        send("Hello".getBytes());
                        textView_1.setText("hello");
                        break;
                    }
                    case TCP_TEXT_2: {
//                        send("Hi".getBytes());
                        textView_1.setText("hi");
                        Log.w("wifi ", ".测试一下Hi");
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        */


        //获取 wifi IP 地址
        wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        dhcp=wifiManager.getDhcpInfo();
        str=Formatter.formatIpAddress(dhcp.serverAddress);



        //设置相关的View
        textView.setText("连接IP地址为："+str);

        ib_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("TOP".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_1;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        ib_Bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("BOTTOM".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_2;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        ib_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("SET".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_2;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        ib_One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("ONE".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_2;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        ib_Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("TWO".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_2;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        ib_Three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send("THREE".getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_2;
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
