package com.example.alemon.msgtool;

import android.support.v4.app.Fragment;
import android.view.View;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ALemon on 2018/4/14.
 */

public class SendData implements MsgTool {
    private Fragment fragment;
    private OutputStream os;//输出数据到服务器的对象
    private Socket client=null;//网络接口对象
    private String ipStr;    //要发送的数据的ip

    public SendData(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }

    private void send(byte[] ch) {
        try {
            client=new Socket(ipStr,8080);//连接服务器，IP随热点，端口是5000
            os=client.getOutputStream();//初始化输出
            os.write(ch);//输出数据
            os.close();//关闭输出
            client.close();//关闭网络
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMsg(int x, View view, final String myMessage) {
        view = (View) fragment.getActivity().findViewById(x);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(myMessage.getBytes());
//                        Message message=new Message();
//                        message.what=TCP_TEXT_1;
//                        handler.sendMessage(message);
                        System.out.println("成功发送");
                    }
                }).start();
            }
        });
    }
}
