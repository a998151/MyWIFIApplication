package com.example.alemon.mywifiapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivty extends AppCompatActivity {
    private Button create_database,add_database;
    private Button login_button,register_button;
    private EditText login_username,login_password;
    private String info;        //返回数据
    private MyDatabaseHelper dbHelper;
    private boolean check=false;
    private Handler handler;
    private TextView forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dbHelper=new MyDatabaseHelper(this,"User.db",null,1);

        //初始化View
        login_button=(Button)findViewById(R.id.login_button);
        register_button=(Button)findViewById(R.id.register_button);
        login_username=(EditText)findViewById(R.id.login_username);
        login_password=(EditText)findViewById(R.id.login_password);


        //1. 建表
        Button createDatabase=(Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

//        //2. 写入数据
//        add_database=(Button)findViewById(R.id.add_database);
//        add_database.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db=dbHelper.getWritableDatabase();
//                String db_ask1="";
//                Cursor cursor=db.query("users",new String[]{"ask1"},null,null,null,null,null);
//                while (cursor.moveToNext()){
//                    db_ask1=cursor.getString(cursor.getColumnIndex("ask1"));
//
//                    Toast.makeText(SecondActivty.this,db_ask1,Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //3. 查数据，验证登录
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                String db_username="";
                String db_password="";
                String username=login_username.getText().toString();
                String password=login_password.getText().toString();

                Cursor cursor=db.query("users",new String[]{"username","password"},null,null,null,null,null);
                while (cursor.moveToNext()){
                    db_username = cursor.getString(cursor.getColumnIndex("username"));
                    db_password = cursor.getString(cursor.getColumnIndex("password"));

                    //判断输入的帐号密码是否和数据库的一样
                    if (db_username.equals(username)&&db_password.equals(password)) {
                        check=true;
                    }
                }
                if (check){
                    Toast.makeText(SecondActivty.this, "Success Login", Toast.LENGTH_SHORT).show();

                    //延时两秒执行 run() 方法中的内容
                    handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //跳转到控制界面
                            Intent intent=new Intent(SecondActivty.this,MainActivity.class);
                            startActivity(intent);
                        }
                    },1000);
                }else {
                    Toast.makeText(SecondActivty.this,"登陆失败，请检查用户名或密码是否正确",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        // 注册功能
        //2. 写入数据
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivty.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetpassword=(TextView)findViewById(R.id.forget_password);
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivty.this,FoundPasswordActivity.class);
                startActivity(intent);

//                handler=new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //跳转到找回密码界面
//                        Intent intent=new Intent(SecondActivty.this,FoundPasswordActivity.class);
//                        startActivity(intent);
//                    }
//                },500);
            }
        });

    }
}
