package com.example.lxc.cy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.FA_attentionAdapter;
import com.example.lxc.cy.bean.AttentionBean;
import com.example.lxc.cy.bean.FindImgBean;
import com.example.lxc.cy.other.OkhttpHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonCollection extends AppCompatActivity {

    private ListView listView;
    private int collection_length = 0;
    private String ip = "";
    private List<AttentionBean> beanList;
    private FA_attentionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_collection);
        ip = getResources().getString(R.string.ip);
        collection_init();
    }


    private void collection_init(){
        listView = (ListView)findViewById(R.id.person_collection_list);

        //设置适配器
        beanList = new LinkedList<AttentionBean>();
        adapter = new FA_attentionAdapter(PersonCollection.this,beanList,"1");
        listView.setAdapter(adapter);

        try {

            collection_data();


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }


    private void collection_data() throws IOException, JSONException {


        String r = OkhttpHelper.Okhttp_Get(ip+"3/collect");
        List<String[]> collection_listString = new ArrayList<String[]>();
        //获取数据个数
        collection_length = OkhttpHelper.JsonArrays(r,"like_num").length;
        String[] like_TF = new String[collection_length];
        //初始化like_TF数据
        for(int i = 0;i<collection_length;i++){
            like_TF[i] = "0";
        }
        //将数据加入list
        collection_listString.add(like_TF);
        collection_listString.add(OkhttpHelper.JsonArrays(r,"notes_id"));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"owner_id"));
        collection_listString.add(OkhttpHelper.AddIp(OkhttpHelper.JsonArrays(r,"user_pic")));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"user_name"));
        collection_listString.add(OkhttpHelper.Create_time(r,"create_time"));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"content_text"));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"place_name"));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"comment_num"));
        collection_listString.add(OkhttpHelper.JsonArrays(r,"like_num"));



        Log.i("cy_lxc",r);
        for(int i = 0;i<collection_length;i++){
            List<FindImgBean> imgBeans = new ArrayList<>();
            String[] img_url = OkhttpHelper.ImgUrls(r,"pic").get(i);
            for (int j=0;j<img_url.length;j++){
                String img = getResources().getString(R.string.ip)+"showImg/"+img_url[j];
                imgBeans.add(new FindImgBean(img));
            }
            beanList.add(new AttentionBean(collection_listString.get(0)[i],collection_listString.get(1)[i],collection_listString.get(2)[i],collection_listString.get(3)[i],
                    collection_listString.get(4)[i], collection_listString.get(5)[i],collection_listString.get(6)[i],collection_listString.get(7)[i],collection_listString.get(8)[i]
                    ,collection_listString.get(9)[i],imgBeans));

        }





    }



}
