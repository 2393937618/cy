package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.Attention_listAdapter;
import com.example.lxc.cy.adapter.EditRecyAdapter;
import com.example.lxc.cy.bean.Attention_listBean;
import com.example.lxc.cy.bean.EditBean;
import com.example.lxc.cy.bean.more_photo_buffer;
import com.example.lxc.cy.other.OkhttpHelper;
import com.example.lxc.cy.other.SpaceItemDecoration;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class editActivity extends AppCompatActivity {

    String type;
    TextView issue;
    EditText content_text, place_name;
    ImageView imageView;
    String filename;
    StringBuffer stringBuffer;
    more_photo_buffer more_photo_buffer;
    int click = 1;
    private String ip;
    private static final int REQUEST_CODE = 0x00000011;
    private RecyclerView recyclerView;
    private List<EditBean> editBeanList;
    private String firstPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ip = getResources().getString(R.string.ip);
        init();

        //获取type
        Intent intent = getIntent();
        type = intent.getStringExtra("type");



        //发布点击
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    //获取输入框的值存储成对象
                    more_photo_buffer = insert_edit();
                    //第一次提交，提交除了图片的其他信息，得到note_id的值
                    firstPost = OkhttpHelper.postRequest(more_photo_buffer,ip+"publish/3");
                    //第二次提交，提交图片pic内容
//                    more_photo_buffer.setPic(stringBuffer.toString());
//                    String secondPost = OkhttpHelper.postRequest(more_photo_buffer,ip+"save_filename/"+firstPost);

                    Toast.makeText(editActivity.this,firstPost,Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

//                Gson gso1 = new Gson();
//                String json1 = gso1.toJson(more_photo_buffer);
//                final MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");
//                OkHttpClient okHttpClient1 = new OkHttpClient();
//                RequestBody body1 = RequestBody.create(JSON1,String.valueOf(json1));
//
//                //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
//                Request request1 = new Request.Builder()
//                        .url("http://172.16.243.163:9000/publish/3")
//                        .post(body1)
//                        .build();
//                //3.创建一个call对象,参数就是Request请求对象
//                Call call1 = okHttpClient1.newCall(request1);
//                call1.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        String err = e.getMessage().toString();
//                        System.out.println("---------------------------sbsbsbs+err"+err);
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String s = response.body().string();
//                        System.out.println("---------------------------sbsbsbs+success"+s);
//                        //返回note_id
//                        System.out.println("---------------------------sbsbsbs+note_id"+s);
//                        System.out.println("---------------------------sbsbsbs+click"+click);
                        //判断有点击图片的话就为1，没有点击就为0
//                        if(click == 1){
//                            //将拼接的图片字符串发送给服务器
//                            more_photo_buffer.setPic(stringBuffer.toString());
//                            Gson gson = new Gson();
//                            String json = gson.toJson(more_photo_buffer);
//                            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                            OkHttpClient okHttpClient = new OkHttpClient();
//                            RequestBody body = RequestBody.create(JSON,String.valueOf(json));
//
//                            //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
//                            Request request = new Request.Builder()
//                                    .url("http://172.16.243.163:9000/save_filename/"+firstPost)
//                                    .post(body)
//                                    .build();
//                            //3.创建一个call对象,参数就是Request请求对象
//                            Call call2 = okHttpClient.newCall(request);
//                            call2.enqueue(new Callback() {
//                                @Override
//                                public void onFailure(Call call, IOException e) {
//                                    String err = e.getMessage().toString();
//                                    System.out.println("---------------------------sbsbsbs+err"+err);
//
//                                }
//
//                                @Override
//                                public void onResponse(Call call, Response response) throws IOException {
//                                    final String s = response.body().string();
//                                    System.out.println("---------------------------sbsbsbs+success"+s);
//                                }
//                            });
//
//
//
//
//
//                                    }
//
//                            });
//
//
//
//
////                //初始化
////                click = 0;
            }
        });

        //上传图片点击
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用ImageSelector框架打开相册
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                        .start(editActivity.this, REQUEST_CODE); // 打开相册


            }
        });


    }

    /**
     * 初始化
     */
    private void init(){

        issue = (TextView) findViewById(R.id.issue);
        content_text = (EditText) findViewById(R.id.content_text);
        place_name = (EditText) findViewById(R.id.place_name);
        imageView = (ImageView) findViewById(R.id.photo);
        recyclerView = (RecyclerView)findViewById(R.id.e_view);
        editBeanList = new ArrayList<>();

    }


    /**
     * 添加数据
     *
     */
    public more_photo_buffer insert_edit(){

        more_photo_buffer buffer = new more_photo_buffer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        //获取note_id，为了存储pic的字符串
        buffer.setContent_text(content_text.getText().toString());
        buffer.setPlace_id(place_name.getText().toString());
        buffer.setType(type);
        buffer.setCreate_time(simpleDateFormat.format(date));

        return buffer;

    }

    /**
     * 初始化recycle
     */
    public void EditRecycler(){
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        EditRecyAdapter adapter = new EditRecyAdapter(editBeanList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(3));
        recyclerView.setAdapter(adapter);
    }


    //回调相册方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取recycle
        EditRecycler();
        if (requestCode == REQUEST_CODE && data != null) {
            //获取图片所有路径
            ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.i("cy_lxc",images.toString());
            //设置返回值
//            String sendFileResult = null;
            //拼接路径方便存储
            stringBuffer = new StringBuffer();
            //循环发送到服务器
            for (int i = 0; i < images.size(); i++) {
                    filename = "attention_photo" + System.currentTimeMillis()+".jpg";
                try {
                    String sendFileResult = OkhttpHelper.sendFile(images.get(i),filename,"upload",ip+"findImg_upload/"+firstPost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //拼接图片名称用于第二次提交
                stringBuffer.append(filename+";");
                //显示图片
                editBeanList.add(new EditBean(images.get(i)));


            }

            Log.i("cy_lxc",stringBuffer.toString());
//            Log.i("cy_lxc",sendFileResult);


//                File file = new File(images.get(i));
//
//
//
//
//
//                RequestBody requestBody = new MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart("upload", filename, RequestBody.create(MediaType.parse("multipart/form-data"), file))
//                        .build();
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .header("Authorization","Client-ID"+UUID.randomUUID())
//                        .url("http://172.16.243.163:9000/upload")
//                        .post(requestBody)
//                        .build();
//                Call call = okHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        String err = e.getMessage().toString();
//                        System.out.println("---------------------------sbsbsbs+err"+err);
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        final String s = response.body().string();
//                        System.out.println("---------------------------sbsbsbs+success"+s);
//                    }
//                });









        }
    }



}
