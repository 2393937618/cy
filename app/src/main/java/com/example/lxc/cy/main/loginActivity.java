package com.example.lxc.cy.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.loginbean;
import com.example.lxc.cy.other.OkhttpHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {



    ImageView QQ,WeiBo,WeChat,loginReturn;
    EditText loginUsername,loginPassword;
    TextView loginToRegister;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //初始化
        login_init();
        //跳转
        login_intent();






//        //发送用户名和密码post给服务器
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //获取输入框内容
//                s1 = username.getText().toString();
//                s2 = password.getText().toString();
//                id = Integer.parseInt(s1);
//                //将对象存成json格式
//                loginbean adsVo = new loginbean();
//                adsVo.setId(id);
//                adsVo.setPassword(s2);
//                Gson gson = new Gson();
//                final String json = gson.toJson(adsVo);
//
//                //1.创建OkHttpClient对象
//                final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                OkHttpClient okHttpClient = new OkHttpClient();
//                RequestBody body = RequestBody.create(JSON,String.valueOf(json));
//                //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
//                Request request = new Request.Builder()
//                        .url("http://192.168.43.243:9000/login")
//                        .post(body)
//                        .build();
//                //3.创建一个call对象,参数就是Request请求对象
//                Call call = okHttpClient.newCall(request);
//                //4.请求加入调度，重写回调方法
//                call.enqueue(new Callback() {
//                    //请求失败执行的方法
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        String err = e.getMessage().toString();
//                        System.out.println("----------------------------sbsbsbs+err"+err);
//                    }
//                    //请求成功执行的方法
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String rtn = response.body().string();
//                        try {
//                            //解析Json包
//                            JSONObject jsonObject = new JSONObject(rtn);
//                            uid = jsonObject.getString("id");
//                            SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("uid",uid);
//                            editor.commit();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        System.out.println("----------------------------sbsbsbs+success"+rtn);
//                        Intent intent = new Intent(loginActivity.this,personal_home.class);
//                        startActivity(intent);
//
//                    }
//                });
//
//
//            }
//        });
//
//
//        //测试从SharedPreferences获取id
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
//                String r = sharedPreferences.getString("uid","");
//                System.out.println("----------------------------sbsbsbs+sharedPreferences"+r);
//            }
//        });
//





    }

    /**
     * 控件初始化
     */
    private void login_init(){
        QQ = (ImageView) findViewById(R.id.QQ);
        WeiBo = (ImageView) findViewById(R.id.WeiBo);
        WeChat = (ImageView) findViewById(R.id.WeChat);
        loginToRegister = (TextView) findViewById(R.id.loginToRegister);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginReturn = (ImageView) findViewById(R.id.login_return);

    }


    /**
     * 跳转实现
     */
    private void login_intent(){
        //无登录跳转到注册界面
        loginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,registerActivity.class));

            }
        });

        //已经登录跳转到用户主页面
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckLogin();
            }
        });
    }


    /**
     * 验证输入的账号密码并跳转到主页
     * A 密码错误,B 账户不存在
     */
    private void CheckLogin(){
        String username = loginUsername.getText().toString().trim();
        Log.i("cy_lxc",username);
        String password = loginPassword.getText().toString().trim();
        Log.i("cy_lxc",password);
        String re = "";
        //正确为返回id
        String result = CheckPasswordAndUsername(password,username);
        try {
             re = OkhttpHelper.JsonString(result,"id");
            Log.i("cy_lxc",re);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //判断输入框是否为空
        if(!username.equals("")){
            if(!password.equals("")){
                if(re.equals("01")){

                    Toast.makeText(loginActivity.this,"输入正确密码",Toast.LENGTH_SHORT).show();
                }else if(re.equals("02")){

                    Toast.makeText(loginActivity.this,"该账号不存在",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginActivity.this,registerActivity.class));
                }
                else {
                    //存储id
                    saveId(re);
                    //跳转到person_home
                    startActivity(new Intent(loginActivity.this,personal_home.class));
                }
            }else {
                Toast.makeText(loginActivity.this,"输入密码",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(loginActivity.this,"输入手机号码",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 验证账户密码
     */
    private String CheckPasswordAndUsername(String password,String username){
        String ip = getResources().getString(R.string.ip)+"login";
        Log.i("cy_lxc",ip);
        String result = null;
        loginbean loginBtn = new loginbean(username,password);
        try {
             result = OkhttpHelper.postRequest(loginBtn,ip);
            Log.i("cy_lxc",result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }





    /**
     * 存储id
     */
    private void saveId(String uid){
       SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString("uid",uid);
       editor.apply();
    }
}
