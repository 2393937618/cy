package com.example.lxc.cy.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.loginbean;
import com.google.gson.Gson;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {

//    ImageView v1,v2,v3,v4,v5,v6,v7;//返回，密码可见，登陆，注册，微信，微博，空间
    EditText username,password;
    ImageView submit,register;
   String s1,s2;//用户名，密码
    Integer id;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        submit = (ImageView)findViewById(R.id.submit);
        register = (ImageView)findViewById(R.id.register);






        //发送用户名和密码post给服务器
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框内容
                s1 = username.getText().toString();
                s2 = password.getText().toString();
                id = Integer.parseInt(s1);
                //将对象存成json格式
                loginbean adsVo = new loginbean();
                adsVo.setId(id);
                adsVo.setPassword(s2);
                Gson gson = new Gson();
                final String json = gson.toJson(adsVo);

                //1.创建OkHttpClient对象
                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON,String.valueOf(json));
                //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
                Request request = new Request.Builder()
                        .url("http://192.168.43.243:9000/login")
                        .post(body)
                        .build();
                //3.创建一个call对象,参数就是Request请求对象
                Call call = okHttpClient.newCall(request);
                //4.请求加入调度，重写回调方法
                call.enqueue(new Callback() {
                    //请求失败执行的方法
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String err = e.getMessage().toString();
                        System.out.println("----------------------------sbsbsbs+err"+err);
                    }
                    //请求成功执行的方法
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String rtn = response.body().string();
                        try {
                            //解析Json包
                            JSONObject jsonObject = new JSONObject(rtn);
                            uid = jsonObject.getString("id");
                            SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("uid",uid);
                            editor.commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        System.out.println("----------------------------sbsbsbs+success"+rtn);
                        Intent intent = new Intent(loginActivity.this,personal_home.class);
                        startActivity(intent);

                    }
                });


            }
        });


        //测试从SharedPreferences获取id
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
                String r = sharedPreferences.getString("uid","");
                System.out.println("----------------------------sbsbsbs+sharedPreferences"+r);
            }
        });





//
//        e1=(EditText)findViewById(R.id.dengluedit_1);
//        e2=(EditText)findViewById(R.id.dengluedit_2);
//        s1=e1.getText().toString();
//        s2=e2.getText().toString();
//
//
//        v1=(ImageView)findViewById(R.id.dengluyebtn1);
//        v1.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        v2=(ImageView)findViewById(R.id.dengluyebtn2);
//        v2.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//            }
//        });
//
//        v3=(ImageView)findViewById(R.id.dengluyebtn3);
//        v3.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//            }
//        });
//
//        v4=(ImageView)findViewById(R.id.dengluyebtn4);
//        v4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(loginActivity.this,registerActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        v5=(ImageView)findViewById(R.id.dengluyebtn5);
//        v5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        v6=(ImageView)findViewById(R.id.dengluyebtn6);
//        v6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        v7=(ImageView)findViewById(R.id.dengluyebtn7);
//        v7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
