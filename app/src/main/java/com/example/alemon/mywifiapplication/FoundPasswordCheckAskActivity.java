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

public class FoundPasswordCheckAskActivity extends AppCompatActivity {
    private EditText foundAsk1, foundAsk2, foundAsk3;
    private Button foundCheckAsk;
    private boolean check=false;
    private MyDatabaseHelper dbHelper;
    private String ask1,ask2,ask3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_password_check_ask);

        //获取上个活动传输过来的数据
        Intent intent=getIntent();
        final String username=intent.getStringExtra("username");

        dbHelper=new MyDatabaseHelper(this,"User.db",null,1);

        foundAsk1 =(EditText)findViewById(R.id.found_ask1);
        foundAsk2=(EditText)findViewById(R.id.found_ask2);

        foundCheckAsk=(Button)findViewById(R.id.found_check_ask_button);
        foundCheckAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ask1=foundAsk1.getText().toString();
                ask2=foundAsk2.getText().toString();

                Cursor cursor=db.query("users",new String[]{"username","ask1","ask2"},null,null,null,null,null);
                while (cursor.moveToNext()){
                    String dbusername=cursor.getString(cursor.getColumnIndex("username"));
                    String dbAsk1 = cursor.getString(cursor.getColumnIndex("ask1"));
                    String dbAsk2 = cursor.getString(cursor.getColumnIndex("ask2"));

                    if (!ask1.equals("")&&!ask2.equals("")){
                        if (dbusername.equals(username)){
                            if (dbAsk1.equals(ask1)) {
                                if (dbAsk2.equals(ask2)){
                                    check=true;
                                }else Toast.makeText(FoundPasswordCheckAskActivity.this,"输入的信息不正确",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(FoundPasswordCheckAskActivity.this,"输入的信息不正确",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        Toast.makeText(FoundPasswordCheckAskActivity.this,"请填完整信息",Toast.LENGTH_SHORT).show();
                    }
                }
                if (check){
                    Toast.makeText(FoundPasswordCheckAskActivity.this,"OK",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
