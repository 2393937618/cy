package com.example.lxc.cy.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.other.ImageUtil;
import com.example.lxc.cy.other.OkHttpUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonAttention extends AppCompatActivity {
    ImageView attention_photo;
    TextView attention_username,attention_intro;
    ImageView picture;
    Button takephoto;
    final int PHOTO_REQUEST_CODE = 200;
    //把用户名和用户简介放进二维数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_attention);
//        attention_username = (TextView)findViewById(R.id.attention_username);
//        attention_intro = (TextView)findViewById(R.id.attention_intro);
//        attention_photo = (ImageView)findViewById(R.id.attention_photo);


        picture = (ImageView) findViewById(R.id.picture);
        takephoto = (Button) findViewById(R.id.take_photo);

        //调用图库
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,PHOTO_REQUEST_CODE);
            }
        });








    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    //通过uri的方式返回，部分手机uri可能为空
                    if(uri != null){
                        try {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            //获取图片的uri
                            uri= data.getData();
                            //图片的绝对路径
                            ImageUtil imageUtil = new ImageUtil();
                            String image_path = imageUtil.getRealPathFromUriAboveApi19(this, uri);

                            //通过uri获取到bitmap对象
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            picture.setImageBitmap(bitmap);
                            //上传图片
                            OkHttpClient okHttpClient= new OkHttpClient();
                            RequestBody requestBody = new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM)
                                    .addFormDataPart("upload","user_photo"+System.currentTimeMillis()+".jpg",RequestBody.create(MediaType.parse("multipart/form-data"),new File(image_path)))
                                    .build();

                            System.out.println("---------------------------sbsbsbs+err"+System.currentTimeMillis());
                            Request request = new Request.Builder()
                                    .header("Authorization","Client-ID"+UUID.randomUUID())
                                    .url("http://192.168.43.243:9000/upload/4")
                                    .post(requestBody)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    String err = e.getMessage().toString();
                                    System.out.println("---------------------------sbsbsbs+err"+err);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    System.out.println("返回值为：");
                                    final String s = response.body().string();
                                    System.out.println("---------------------------sbsbsbs"+s);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                        }
                                    });
                                }
                            });





                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else {
                        //部分手机可能直接存放在bundle中
//                        Bundle bundleExtras = data.getExtras();
//                        if(bundleExtras != null){
//                            Bitmap bitmaps = bundleExtras.getParcelable("data");
//                            picture.setImageBitmap(bitmaps);
//                        }
                    }

                }
                break;
        }
    }








}
