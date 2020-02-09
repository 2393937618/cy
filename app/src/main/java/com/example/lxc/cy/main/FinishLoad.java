package com.example.lxc.cy.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxc.cy.R;
//import com.example.lxc.cy.other.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FinishLoad extends AppCompatActivity {
    ImageView bt1, bt2, bt3, bt4, bt5, bt6, bt7;
    TextView text1, text3, text2;
    LinearLayout lin1, lin2, lin3;
    TextView username_data, fans_data, attentions_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_load);
        username_data = (TextView) findViewById(R.id.username_data);
        fans_data = (TextView) findViewById(R.id.fans_data);
        attentions_data = (TextView) findViewById(R.id.attentions_data);
        //接收粉丝数量，关注人数，用户名的json包
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址,设置请求方式。
        Request request = new Request.Builder()
                .url("http://192.168.43.243:9000/1")
                .get()
                .build();

        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String err = e.getMessage().toString();
                System.out.println("----------------------------sbsbsbs+err"+err);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String rtn = response.body().string();
                System.out.println("----------------------------sbsbsbs+1111"+rtn);
                //解析收到的json包，根节点为{}
                try {
                    JSONObject jsonObject = new JSONObject(rtn);
                    String username = jsonObject.getString("username");
                    username_data.setText(username);
                    int fans_num = jsonObject.getInt("fans_num");
                    System.out.println("----------------------------sbsbsbs+1111"+fans_num);
                    fans_data.setText(""+fans_num);
                    int attention_num = jsonObject.getInt("attention_num");
                    System.out.println("----------------------------sbsbsbs+1111"+attention_num);
                    attentions_data.setText(""+attention_num);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


















//        String s1,s2="202",s3="303",s4;//用户名，关注数，粉丝数,头像
//        //获取用户名
//        Intent intent=getIntent();
//        s1=intent.getStringExtra("user_name");
//        //获取头像
//        s2=intent.getStringExtra("touxian");
//        CircleImageView c=(CircleImageView)findViewById(R.id.douxian);
//        //设置头像
//        //c.setImageDrawable(this.getFilesDir().toString()+"/my.db");
//        text3.setText(s2);
//        new Thread(){
//            public void run(){
//                bt1=(ImageView)findViewById(R.id.yidenglu_1);
//                bt2=(ImageView)findViewById(R.id.yidenglu_2);
//                bt3=(ImageView)findViewById(R.id.yidenglu_3);
//                bt4=(ImageView)findViewById(R.id.yidenglu_7);
//                bt5=(ImageView)findViewById(R.id.yidenglu_5);
//                bt6=(ImageView)findViewById(R.id.yidenglu_6);
//                bt7=(ImageView)findViewById(R.id.yidenglu_6_1);
//
//                lin1=(LinearLayout)findViewById(R.id.shouchang);
//                lin2=(LinearLayout)findViewById(R.id.fensi);
//                lin3=(LinearLayout)findViewById(R.id.guanzhu);
//               bt1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,SettingMain.class);
//                        startActivity(intent);
//
//                    }
//                });
//                bt2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,Xiaoxi.class);
//                        startActivity(intent);
//
//                    }
//                });
//                bt3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,Gerenxinxi.class);
//                        startActivity(intent);
//
//                    }
//                });

//                bt4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,FinishVideoActivity.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//                bt5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,FinishTravelActivity.class);
//                        startActivity(intent);
//
//                    }
//                });
//                bt6.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,FinishPhotoActivity.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//                bt6.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,AnswerMain.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//                lin1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,FavoritesMain.class);
//                        startActivity(intent);
//
//                    }
//                });


//                lin2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,Fensi.class);
//                        startActivity(intent);
//
//                    }
//                });

//                lin3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(FinishLoad.this,PersonAttention.class);
//                        startActivity(intent);
//
//                    }
//                });
//            }
//        }.start();
    }
}
