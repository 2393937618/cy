package com.example.lxc.cy.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.P_contactAdapter;
import com.example.lxc.cy.bean.ContactBean;
import com.example.lxc.cy.other.OkhttpHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonAttention extends AppCompatActivity {
//    ImageView attention_photo;
//    TextView attention_username,attention_intro;
//    ImageView picture;
//    Button takephoto;
    private ListView listView;
    final int PHOTO_REQUEST_CODE = 200;
    private String uid = "";
    private P_contactAdapter contactAdapter = null;
    private List<ContactBean> contactBeanList = null;
    private int length = 0;
    private String ip = "";

    //把用户名和用户简介放进二维数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_attention);

        ip = getResources().getString(R.string.ip);
        attention_init();


//        attention_username = (TextView)findViewById(R.id.attention_username);
//        attention_intro = (TextView)findViewById(R.id.attention_intro);
//        attention_photo = (ImageView)findViewById(R.id.attention_photo);


//        picture = (ImageView) findViewById(R.id.picture);
//        takephoto = (Button) findViewById(R.id.take_photo);
//
//        //调用图库
//        takephoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,PHOTO_REQUEST_CODE);
//            }
//        });
//
//
//
//
//
//
//
//
//    }
//
//
//
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case PHOTO_REQUEST_CODE:
//                if(resultCode == RESULT_OK){
//                    Uri uri = data.getData();
//                    //通过uri的方式返回，部分手机uri可能为空
//                    if(uri != null){
//                        try {
//                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                            //获取图片的uri
//                            uri= data.getData();
//                            //图片的绝对路径
//                            ImageUtil imageUtil = new ImageUtil();
//                            String image_path = imageUtil.getRealPathFromUriAboveApi19(this, uri);
//
//                            //通过uri获取到bitmap对象
//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                            picture.setImageBitmap(bitmap);
//                            //上传图片
//                            OkHttpClient okHttpClient= new OkHttpClient();
//                            RequestBody requestBody = new MultipartBody.Builder()
//                                    .setType(MultipartBody.FORM)
//                                    .addFormDataPart("upload","user_photo"+System.currentTimeMillis()+".jpg",RequestBody.create(MediaType.parse("multipart/form-data"),new File(image_path)))
//                                    .build();
//
//                            System.out.println("---------------------------sbsbsbs+err"+System.currentTimeMillis());
//                            Request request = new Request.Builder()
//                                    .header("Authorization","Client-ID"+UUID.randomUUID())
//                                    .url("http://192.168.43.243:9000/upload/4")
//                                    .post(requestBody)
//                                    .build();
//                            Call call = okHttpClient.newCall(request);
//                            call.enqueue(new Callback() {
//                                @Override
//                                public void onFailure(Call call, IOException e) {
//                                    String err = e.getMessage().toString();
//                                    System.out.println("---------------------------sbsbsbs+err"+err);
//                                }
//
//                                @Override
//                                public void onResponse(Call call, Response response) throws IOException {
//                                    System.out.println("返回值为：");
//                                    final String s = response.body().string();
//                                    System.out.println("---------------------------sbsbsbs"+s);
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                        }
//                                    });
//                                }
//                            });
//
//
//
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }else {
//                        //部分手机可能直接存放在bundle中
////                        Bundle bundleExtras = data.getExtras();
////                        if(bundleExtras != null){
////                            Bitmap bitmaps = bundleExtras.getParcelable("data");
////                            picture.setImageBitmap(bitmaps);
////                        }
//                    }
//
//                }
//                break;
//        }










    }

    private void attention_init(){
        listView = (ListView)findViewById(R.id.person_attention_list);

        //id值
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        uid = sharedPreferences.getString("uid","");

        //设置适配器
        contactBeanList = new LinkedList<ContactBean>();
        contactAdapter = new P_contactAdapter((LinkedList<ContactBean>) contactBeanList,PersonAttention.this);
        listView.setAdapter(contactAdapter);

        //数据填充
        attention_data();

    }


    /**
     * 数据获取加入到list集合
     */
    private void attention_data() {


        try {
//            String result = OkhttpHelper.Okhttp_Get(ip)+uid+"/attentions";
            String result = OkhttpHelper.Okhttp_Get(ip+"3/attentions");
            Log.i("cy_lxc",result);
            List<String[]> list = new ArrayList<>();
            list.add(OkhttpHelper.JsonArrays(result,"user_name"));
            list.add(OkhttpHelper.AddIp(OkhttpHelper.JsonArrays(result,"user_pic")));
            list.add(OkhttpHelper.JsonArrays(result,"user_show"));
            length = OkhttpHelper.JsonArrays(result,"user_pic").length;

            for (int i = 0;i<length;i++){
                contactBeanList.add(new ContactBean(list.get(0)[i],list.get(1)[i],list.get(2)[i]));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }



}
