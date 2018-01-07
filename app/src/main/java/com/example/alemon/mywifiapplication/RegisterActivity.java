package com.example.alemon.mywifiapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText register_username, register_password;
    private EditText register_ask1,register_ask2,register_ask3;
    private Button register;
    private MyDatabaseHelper dbHelper;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper=new MyDatabaseHelper(this,"User.db",null,1);

        register_username=(EditText)findViewById(R.id.register_username);
        register_password=(EditText)findViewById(R.id.register_password);
        register_ask1=(EditText)findViewById(R.id.register_ask1);
        register_ask2=(EditText)findViewById(R.id.register_ask2);

        register=(Button) findViewById(R.id.register_button_1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得输入框中的信息
                String username= register_username.getText().toString();
                String password= register_password.getText().toString();
                String ask1=register_ask1.getText().toString();
                String ask2=register_ask2.getText().toString();

                //1. 先判断密码长度是否为6-16
                if(password.length()>=6&&password.length()<=16){
                    //2. 再判断帐号密码中是否包含空格
                    if (password.indexOf(" ")==-1&&username.indexOf(" ")==-1){
                        //打开现有的一个数据库
                        SQLiteDatabase db=dbHelper.getWritableDatabase();

                        //判断数据库中是否已有该帐号
                        Cursor cursor=db.query("users",new String[]{"username"},null,null,null,null,null);
                        while (cursor.moveToNext()){
                            String db_username = cursor.getString(cursor.getColumnIndex("username"));
                            //判断输入的帐号是否和数据库的一样
                            if (db_username.equals(username)) {
                                check=true;
                                Toast.makeText(RegisterActivity.this, "该用户名已经注册，请修改用户名！", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (!check){
                            if ((!username.equals(""))) {
                                if ((!password.equals(""))) {
                                    if (!ask1.equals("")&&!ask2.equals("")) {
                                        //数据库中没有含有该用户名，则可以注册
                                        //组装一条数据
                                        ContentValues values = new ContentValues();
                                        values.put("username", username);
                                        values.put("password", password);
                                        values.put("ask1",ask1);
                                        values.put("ask2",ask2);
                                        db.insert("users", null, values);
                                        values.clear();

                                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "请填完整信息", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else {
                                Toast.makeText(RegisterActivity.this, "请填完整信息", Toast.LENGTH_SHORT).show();
                            }
                        }
                        cursor.close();
                    }else {
                        Toast.makeText(RegisterActivity.this,"帐号密码中不能存在空格",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "密码长度须为6-16", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
