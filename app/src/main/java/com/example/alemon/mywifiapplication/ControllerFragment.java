package com.example.alemon.mywifiapplication;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.alemon.msgtool.SendData;
import com.example.alemon.msgtool.SendMsg;

/**
 * Created by ALemon on 2018/4/11.
 */

public class ControllerFragment extends Fragment {

    private static final int TCP_TEXT_1=1;
    private static final int TCP_TEXT_2=2;
    private Button button;
    private WifiManager wifiManager;
    private ImageButton ib_Top,ib_Bottom,ib_M,ib_One,ib_Two,ib_Three;
    private DhcpInfo dhcp;//服务器信息类
    private String ipStr;
    private SendMsg sendMsg;
    private SendData sendData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_control,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        //获取 wifi IP 地址
        wifiManager=(WifiManager)this.getActivity().getSystemService(Context.WIFI_SERVICE);
        dhcp=wifiManager.getDhcpInfo();
        ipStr= Formatter.formatIpAddress(dhcp.serverAddress);

        sendMsg = new SendMsg(this);
        sendMsg.sendMsg(R.id.test_button,button,"Test Success !");

        //初始化与绑定控件
        sendData = new SendData(this);
        sendData.sendMsg(R.id.ib_One,ib_One,"one");
        sendData.sendMsg(R.id.ib_Two,ib_Two,"two");
        sendData.sendMsg(R.id.ib_Three,ib_Three,"three");
        sendData.sendMsg(R.id.ib_M,ib_M,"M");
        sendData.sendMsg(R.id.ib_Top,ib_Top,"top");
        sendData.sendMsg(R.id.ib_Bottom,ib_Bottom,"bottom");

    }
}
