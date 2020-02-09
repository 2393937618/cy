package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.Attention_listAdapter;
import com.example.lxc.cy.bean.Attention_listBean;
import com.example.lxc.cy.adapter.Attention_listAdapter.MyClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class attention_main extends AppCompatActivity implements OnItemClickListener {
    ImageButton like,collect;
    TextView add;
    String ip2 = "http://172.16.243.163:9000/main/attention/3";
    String ip3 = "http://172.16.243.163:9000/like/";


    private int index = 0;


    String[] like_TF,create_name,content_text,place_name,comment_num,like_num,type,owner_id,notes_id,place_id,create_time,user_name,user_pic;
    private static final int COMPLETED = 0;
    private static final int COMPLETED1 = 1;
    TextView likes_num;
    ListView listView;
    View heardView,footView;
    String note_id="";
    List<Attention_listBean> aData;
    Attention_listAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                listView.setAdapter(new Attention_listAdapter((LinkedList<Attention_listBean>) aData,attention_main.this));//UI更改操作
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_main);
        adapter = new Attention_listAdapter((LinkedList<Attention_listBean>) aData,attention_main.this);


        listView = (ListView)findViewById(R.id.attention_list);
        final LayoutInflater inflater = LayoutInflater.from(this);
        aData = new LinkedList<Attention_listBean>();

        listView.setOnItemClickListener(this);






        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址,设置请求方式。
        Request request = new Request.Builder()
                .url(ip2)
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
                //获取数据包
                String rtn = response.body().string();
                System.out.println("----------------------------sbsbsbs+success"+rtn);
                try {
                    //解析json数据：create_time
                    JSONObject jsonObject = new JSONObject(rtn);
                    //将/n替换
                    String a = jsonObject.getString("create_time").replaceAll("\n","");
                    //去除[]，并生成数组
                    create_time = a.substring(1,a.length()-1).split(",");
                    //去除空格，测试输出结果
                    for(int i=0;i<create_time.length;i++){
                        System.out.println("----------------------------sbsbsbs+success"+create_time[i].substring(4));
                    }
                    //解析[]的json数据
                    JSONArray jsonArray = jsonObject.getJSONArray("comment_num");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("content_text");
                    JSONArray jsonArray2 = jsonObject.getJSONArray("like_num");
                    JSONArray jsonArray3 = jsonObject.getJSONArray("place_name");
                    JSONArray jsonArray4 = jsonObject.getJSONArray("user_name");
                    JSONArray jsonArray5 = jsonObject.getJSONArray("user_pic");
                    JSONArray jsonArray6 = jsonObject.getJSONArray("notes_id");

                    //定义数组长度
                    comment_num = new String[jsonArray.length()];
                    content_text = new String[jsonArray.length()];
                    like_num = new String[jsonArray.length()];
                    place_name = new String[jsonArray.length()];
                    user_name = new String[jsonArray.length()];
                    user_pic = new String[jsonArray.length()];
                    notes_id = new String[jsonArray.length()];
                    like_TF = new String[jsonArray.length()];



                    //将json数据存到数组
                    for(int i=0;i<jsonArray.length();i++){
                        comment_num[i] = jsonArray.getString(i);
                        content_text[i] = jsonArray1.getString(i);
                        like_num[i] = jsonArray2.getString(i);
                        place_name[i] = jsonArray3.getString(i);
                        user_name[i] = jsonArray4.getString(i);
                        user_pic[i] = "http://172.16.243.163:9000/"+jsonArray5.getString(i);
                        notes_id[i] = jsonArray6.getString(i);
                        like_TF[i] = "0";
                        //测试数组数据
                        System.out.println("----------------------------sbsbsbs+user_name"+user_name[i]);
                        System.out.println("----------------------------sbsbsbs+content_text"+content_text[i]);
                        System.out.println("----------------------------sbsbsbs+like_num"+like_num[i]);
                        System.out.println("----------------------------sbsbsbs+place_name"+place_name[i]);
                        System.out.println("----------------------------sbsbsbs+user_name"+user_name[i]);
                        System.out.println("----------------------------sbsbsbs+user_pic"+user_pic[i]);
                        System.out.println("----------------------------sbsbsbs+notes_id"+notes_id[i]);
                        aData.add(new Attention_listBean(like_TF[i],notes_id[i],user_pic[i],user_name[i],create_time[i].substring(4),content_text[i],place_name[i],comment_num[i],like_num[i]));

                    }
                    //不能在子线程更新UI，用handler发送消息
                    Message message = new Message();
                    message.what = COMPLETED;
                    handler.sendMessage(message);







                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });












    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(attention_main.this, "listview的item被点击了！，点击的位置是-->" + position,
                Toast.LENGTH_SHORT).show();
    }


}

