package com.example.alemon.msgtool;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;


/**
 * Created by ALemon on 2018/4/14.
 */

public class SendMsg implements MsgTool {
    private Fragment fragment;

    public SendMsg(Fragment fragment) {
        this.fragment = fragment;
    }

    //发送提示
    @Override
    public void sendMsg(int x, View view, final String myMessage) {
        view = (View) this.fragment.getActivity().findViewById(x);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment.getActivity(),myMessage,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
