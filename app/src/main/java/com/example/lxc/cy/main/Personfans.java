package com.example.lxc.cy.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Personfans extends AppCompatActivity {

//    Bitmap bitmap = null;
//    TextView textView;
//    ImageView imageView,img_show;
//    String uid;
//    String sql;
//    ListView listView;

    ListView listView;
    ImageButton imageButton;
    private String uid = "";
    private P_contactAdapter contactAdapter = null;
    private List<ContactBean> contactBeanList = null;
    private int length = 0;
    private String ip = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personfans);

        ip = getResources().getString(R.string.ip);
        fans_init();





//
//        textView = (TextView) findViewById(R.id.fanstext);
//        img_show = (ImageView)findViewById(R.id.img);
//        listView = (ListView) findViewById(R.id.fans_list);
//
//
//
//
//        //获取账户id
//        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_PRIVATE);
//        uid = sharedPreferences.getString("uid","");
//        System.out.println("----------------------------sbsbsbs+sharedPreferences"+uid);
//
//        int id = 4;
//        //提供查询语句
//        sql = "select user_pic from user where user_id="+id+"";
//        fansbean f = new fansbean();
//        f.setSql(sql);
//        Gson gson = new Gson();
//        final String json = gson.toJson(f);
//        //1.创建OkHttpClient对象
//        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody body = RequestBody.create(JSON,String.valueOf(json));
//        //2.创建Request对象
//        Request request = new Request.Builder()
//                .url("http://192.168.43.243:9000/search")
//                .post(body)
//                .build();
//        //3.创建一个call对象,参数就是Request请求对象
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                String err = e.getMessage().toString();
//                System.out.println("----------------------------sbsbsbs+err"+err);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //注意是String对应url不是toString
//                final String success = response.body().string();
//                System.out.println("----------------------------sbsbsbs+快乐"+success);
//
//                //解析Json包得到url
//                try {
//                    JSONObject jsonObject = new JSONObject(success);
//                    String url = jsonObject.getString("need");
//                    System.out.println("----------------------------sbsbsbs+ee"+url);
//                    String strUrl = "http://192.168.43.243:9000/"+url;
//                    //通过url显示图片
//                    final MyImageView myImageView = (MyImageView) findViewById(R.id.fansimage);
//                    myImageView.setImageURL(strUrl);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//
//
//        }
//        });





















    }


    private void fans_init(){
        listView = (ListView) findViewById(R.id.person_fans_list);
        imageButton = (ImageButton) findViewById(R.id.person_fans_return);

        //id值
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        uid = sharedPreferences.getString("uid","");

        //设置适配器
        contactBeanList = new LinkedList<ContactBean>();
        contactAdapter = new P_contactAdapter((LinkedList<ContactBean>) contactBeanList,Personfans.this);
        listView.setAdapter(contactAdapter);

        fans_data();


    }





    /**
     * 数据获取加入到list集合
     */
    private void fans_data() {


        try {
//            String result = OkhttpHelper.Okhttp_Get(ip)+uid+"/fans";
            String result = OkhttpHelper.Okhttp_Get(ip+"3/fans");
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
