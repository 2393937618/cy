package com.example.lxc.cy.main;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cc.library.Item;
import com.cc.library.SmartDialog;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.example.lxc.cy.R;
import com.example.lxc.cy.other.OkhttpHelper;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class personal_home extends AppCompatActivity {
    RadioButton personMy,personTrip,personFind,personCommend;
    ImageButton personAdd,personLike,personAttention,personCollection,personInfo,personSetting;
    TextView personMany,personVideo,personAnswer,personTravel,notLogin,personUpdate,personUsername;
    ImageView loginSuccess;

    private static final int TAKE_PHOTO_CODE = 111;
    private static final int TAKE_CAMERA_CODE = 222;
    private static final int COMPLETED = 333;
    private String ip = "";

    private String uid = "";
    private boolean loginSAN = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_home);

        //初始化
        person_init();
        //跳转
        person_intent();
        //判断登录状态
        ChangLogin(loginSAN);




//
//        not_login = (FrameLayout)findViewById(R.id.not_login);
//        success_login = (LinearLayout)findViewById(R.id.success_login);
//        tj = (RadioButton)findViewById(R.id.tj2);
//        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
//        r = sharedPreferences.getString("uid","");
//        //查看是否登录成功
//        if(r.equals(""))
//            Toast.makeText(personal_home.this,"登录看看",Toast.LENGTH_SHORT).show();
//        else {
//            not_login.setVisibility(View.GONE);
//            success_login.setVisibility(View.VISIBLE);
//
//        }
//
//
//
//        //跳转到activity_home
//        tj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(personal_home.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    /**
     * 初始化
     */
    private void person_init(){
        personMy = (RadioButton) findViewById(R.id.person_my);
        personTrip = (RadioButton) findViewById(R.id.person_trip);
        personAdd = (ImageButton) findViewById(R.id.person_add);
        personFind = (RadioButton) findViewById(R.id.person_find);
        personCommend = (RadioButton) findViewById(R.id.person_recommend);
        personMany = (TextView) findViewById(R.id.person_many);
        personVideo = (TextView) findViewById(R.id.person_video);
        personAnswer = (TextView) findViewById(R.id.person_answer);
        personTravel = (TextView) findViewById(R.id.person_travel);
        personLike = (ImageButton) findViewById(R.id.person_like);
        personAttention = (ImageButton) findViewById(R.id.person_attention);
        personCollection = (ImageButton) findViewById(R.id.person_collection);
        loginSuccess = (ImageView) findViewById(R.id.login_success);
        notLogin = (TextView) findViewById(R.id.not_login);
        personInfo = (ImageButton) findViewById(R.id.person_info);
        personSetting = (ImageButton) findViewById(R.id.person_setting);
        personUpdate = (TextView)findViewById(R.id.person_update);
        personUsername = (TextView)findViewById(R.id.person_username);


        //设置radioButton
        SetView(R.drawable.not_click_recommend,personCommend);
        SetView(R.drawable.not_click_find,personFind);
        SetView(R.drawable.not_click_trip,personTrip);
        SetView(R.drawable.click_my,personMy);



        //初始化参数
        ip = getResources().getString(R.string.ip);
        //id值
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        uid = sharedPreferences.getString("uid","");
        //判断是否有id获取登录状态
        if (uid != null) {
            loginSAN = !uid.equals("");
        }

    }









    /**
     * 初始化跳转
     * @param cls
     */
    private void init_intent(Class cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }


    /**
     * 更换登录状态
     */
    private void ChangLogin(boolean uidExits){
        if (uidExits){
            loginSuccess.setVisibility(View.VISIBLE);
            notLogin.setVisibility(View.GONE);
            try {
                String result = OkhttpHelper.Okhttp_Get(ip+uid);
                String name = OkhttpHelper.JsonString(result,"user_pic");
                Glide.with(personal_home.this).load(OkhttpHelper.AddOneIp(name)).into(loginSuccess);
                personUsername.setText("欢迎"+OkhttpHelper.JsonString(result,"username"));
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }else {
            notLogin.setVisibility(View.VISIBLE);
            loginSuccess.setVisibility(View.GONE);
            personUsername.setText("欢迎来到畅游");
        }
    }




    /**
     * 跳转函数
     */
    private void person_intent(){
        //无登录跳转到登录界面
        notLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                init_intent(loginActivity.class);
            }
        });


        personMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(personal_home.this,uid,Toast.LENGTH_SHORT).show();
            }
        });


        personAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(PersonAttention.class);
            }
        });


        personCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(PersonCollection.class);
            }
        });

        personUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(SettingPerson.class);
            }
        });


        personLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(Personfans.class);
            }
        });

        personSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(SettingMain.class);
            }
        });

        loginSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<Item> items = new ArrayList<>();
//                items.add(new Item(0,"拍照获取"));
//                items.add(new Item(1,"相册获取"));
//                new SmartDialog().init(personal_home.this)
//                        .items(items)
//                        .backgroundResEnable(true)
//                        .animEnable(true)
//                        .display();
                showBottomDialog();
            }
        });

        personCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_intent(MainActivity.class);
            }
        });
    }








    /**
     * 显示底部弹出框
     */

    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this, R.layout.dialog_user_pic, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeCamera();
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               takePhoto();
               dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    /**
     * 调用系统相机拍照获取照片
     */
    private void takeCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,TAKE_CAMERA_CODE);
    }


    /**
     * 调用系统相册获取照片
     */
    private void takePhoto(){
        ImageSelector.builder()
                .useCamera(false) // 设置是否使用拍照
                .setCrop(true)   //设置裁剪
                .setSingle(true)  //设置是否单选
                .start(personal_home.this, TAKE_PHOTO_CODE); // 打开相册
    }












    /**
     * 设置radiobutton大小和方向
     * @param layoutId 图片的id
     * @param radioButton button的id
     */
    public void SetView(int layoutId,RadioButton radioButton){
        Drawable drawable = getResources().getDrawable(layoutId);
        drawable.setBounds(0,0,70,70);
        //设置图片在文字的方向
        radioButton.setCompoundDrawables(null,drawable,null,null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data != null){
            switch (requestCode){
                case TAKE_CAMERA_CODE:
                    //保存
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    String fileName = "user_photo"+System.currentTimeMillis() + ".jpg";
                    String fileUrl = Environment.getExternalStorageDirectory().getPath()+"/user_photo"+System.currentTimeMillis() + ".jpg";
                    File file = new File(fileUrl);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    finish();
                    startActivity(new Intent(personal_home.this,personal_home.class));

                    //发送到服务器
                    try {
                        String result = OkhttpHelper.sendFile(fileUrl,fileName,"upload",ip+"upload/"+uid);
                        Log.i("cy_lxc",result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case TAKE_PHOTO_CODE:

                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
                    String url = images.get(0);
                    String Filename = "user_photo"+System.currentTimeMillis()+".jpg";

                    try {
                        String re = OkhttpHelper.sendFile(url,Filename,"upload",ip+"upload/"+uid);
                        Log.i("cy_lxc",re);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    finish();
                    startActivity(new Intent(personal_home.this,personal_home.class));


                    break;

                    default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
