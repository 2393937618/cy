package com.example.lxc.cy.main;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.fansbean;
import com.example.lxc.cy.other.MyImageView;
import com.example.lxc.cy.other.OkHttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Personfans extends AppCompatActivity {

    Bitmap bitmap = null;
    TextView textView;
    ImageView imageView,img_show;
    String uid;
    String sql;
    ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personfans);

        textView = (TextView) findViewById(R.id.fanstext);
        img_show = (ImageView)findViewById(R.id.img);
        listView = (ListView) findViewById(R.id.fans_list);




        //获取账户id
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_PRIVATE);
        uid = sharedPreferences.getString("uid","");
        System.out.println("----------------------------sbsbsbs+sharedPreferences"+uid);

        int id = 4;
        //提供查询语句
        sql = "select user_pic from user where user_id="+id+"";
        fansbean f = new fansbean();
        f.setSql(sql);
        Gson gson = new Gson();
        final String json = gson.toJson(f);
        //1.创建OkHttpClient对象
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON,String.valueOf(json));
        //2.创建Request对象
        Request request = new Request.Builder()
                .url("http://192.168.43.243:9000/search")
                .post(body)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String err = e.getMessage().toString();
                System.out.println("----------------------------sbsbsbs+err"+err);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //注意是String对应url不是toString
                final String success = response.body().string();
                System.out.println("----------------------------sbsbsbs+快乐"+success);

                //解析Json包得到url
                try {
                    JSONObject jsonObject = new JSONObject(success);
                    String url = jsonObject.getString("need");
                    System.out.println("----------------------------sbsbsbs+ee"+url);
                    String strUrl = "http://192.168.43.243:9000/"+url;
                    //通过url显示图片
                    final MyImageView myImageView = (MyImageView) findViewById(R.id.fansimage);
                    myImageView.setImageURL(strUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }




        }
        });





















    }


}
