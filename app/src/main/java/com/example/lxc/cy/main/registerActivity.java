package com.example.lxc.cy.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.example.lxc.cy.adapter.Attention_listAdapter;
import com.example.lxc.cy.bean.Attention_listBean;
import com.example.lxc.cy.bean.RegisterBean;
import com.example.lxc.cy.other.OkhttpHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class registerActivity extends AppCompatActivity {
    EditText registerYZM,registerPassword,registerPhone;
    Button registerBtn,YZMbtn;
    ImageView registerReturn;
    private EventHandler eh;//事件接收器
    private TimeCount mTimeCount;//计时器
    String phone,yzm,password,ip;
    private static final int COMPLETED = 0;


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("ShowToast")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                Toast.makeText(getApplicationContext(),"账户已存在",Toast.LENGTH_SHORT).show();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化
        register_init();
        //验证码验证回调初始化
        YZM();
        //跳转
        register_intent();




    }

    /**
     * 控件初始化
     */
    private void register_init(){
        registerBtn = (Button) findViewById(R.id.register_Btn);
        registerYZM = (EditText) findViewById(R.id.register_YZM);
        registerPassword = (EditText) findViewById(R.id.register_Password);
        registerPhone = (EditText) findViewById(R.id.register_Phone);
        registerReturn = (ImageView) findViewById(R.id.register_Return);
        YZMbtn = (Button)findViewById(R.id.YZM_btn);
        mTimeCount = new TimeCount(60000, 1000);
    }

    /**
     * 跳转intent初始化
     * @param cls
     */
    private void init_intent(Class cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

    /**
     * 跳转实现
     */
    private void register_intent(){
        //无登录跳转到登录界面
        registerReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(loginActivity.class);
            }
        });


        //获取验证码
        YZMbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = registerPhone.getText().toString().trim();
                if(!phone.equals("")){
                    if(checkTel(phone)){
                        SMSSDK.getVerificationCode("+86",phone);
                        mTimeCount.start();
                    }else {
                        Toast.makeText(registerActivity.this,"输入正确手机号码",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(registerActivity.this,"输入手机号码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //提交按钮提交
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  phone = registerPhone.getText().toString().trim();
                  yzm = registerYZM.getText().toString().trim();
                  password = registerPassword.getText().toString().trim();
                  ip = getResources().getString(R.string.ip);

                if(!phone.equals("")&&!password.equals("")){
                    if(checkTel(phone)){
                        if(!yzm.equals("")){
                            SMSSDK.submitVerificationCode("+86",phone,yzm);
                        }else {
                            Toast.makeText(registerActivity.this,"输入验证码",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(registerActivity.this,"输入正确手机号码",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(registerActivity.this,"输入手机号码和密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    /**
     * 验证码回调
     */
    private void YZM(){
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if(result == SMSSDK.RESULT_COMPLETE){//回调完成
                    if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){ //提交验证码成功
                        postData();
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }

            }
        };
        SMSSDK.registerEventHandler(eh);
    }


    /**
     * 发送数据到数据库
     * A账户存在
     */

    private void postData() {

        String url = ip+"register";
        Log.i("cy_lxc",url);
        RegisterBean registerBean = new RegisterBean(phone,password);
        String re = "";
        try {
            String result = OkhttpHelper.postRequest(registerBean,url);
            re = OkhttpHelper.JsonString(result,"id");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        //判断注册是否成功
            if (re.equals("01")){
                //不能在子线程toast，用handler发送消息
                Message message = new Message();
                message.what = COMPLETED;
                handler.sendMessage(message);

            }else {
                finish();
                init_intent(loginActivity.class);
            }



    }



    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }


    /**
     * 计时器
     */

    class TimeCount extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            YZMbtn.setClickable(false);
            YZMbtn.setText(millisUntilFinished/1000 + "秒重新GET");
        }

        @Override
        public void onFinish() {
            YZMbtn.setClickable(true);
            YZMbtn.setText("获取验证码");
        }
    }




}
