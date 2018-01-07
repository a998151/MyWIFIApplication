package com.example.alemon.mywifiapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FoundPasswordActivity extends AppCompatActivity {
    private EditText usernameView;
    private MyDatabaseHelper dbHelper;
    private Button checkUsername;
    private boolean check=false;
    private String username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_password);

        dbHelper=new MyDatabaseHelper(this,"User.db",null,1);

        usernameView=(EditText)findViewById(R.id.found_username);


        checkUsername=(Button)findViewById(R.id.found_check);
        checkUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=usernameView.getText().toString();
                if (!username.equals("")){
                    //打开现有的一个数据库
                    SQLiteDatabase db=dbHelper.getWritableDatabase();

                    //判断数据库中是否已有该帐号
                    Cursor cursor=db.query("users",new String[]{"username"},null,null,null,null,null);
                    while (cursor.moveToNext()){
                        String db_username = cursor.getString(cursor.getColumnIndex("username"));
                        //判断输入的帐号是否和数据库的一样
                        if (db_username.equals(username)) {
                            check=true;
                        }
                    }
                    if (check){
                        //跳转到验证修改密码
                        Intent intent=new Intent(FoundPasswordActivity.this,FoundPasswordCheckAskActivity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }else {
                        Toast.makeText(FoundPasswordActivity.this,"这用户名不存在",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(FoundPasswordActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
