package com.example.lxc.cy.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.SettingPersonBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingPerson extends AppCompatActivity {

    EditText setting_names,setting_sexs,setting_birthdays,setting_addresss,setting_intros;
    String name,sex,bir,place,show;
    TextView setting_saves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_person);
        setting_names = (EditText)findViewById(R.id.setting_name);
        setting_sexs = (EditText)findViewById(R.id.setting_sex);
        setting_birthdays = (EditText)findViewById(R.id.setting_birthday);
        setting_addresss = (EditText)findViewById(R.id.setting_address);
        setting_intros = (EditText)findViewById(R.id.setting_intro);
        setting_saves = (TextView)findViewById(R.id.setting_save);


        //获取账号id
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        String id = sharedPreferences.getString("uid","");
        System.out.println("----------------------------sbsbsbs+sharedPreferences"+id);

        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址,设置请求方式。
        Request request = new Request.Builder()
                .url("http://192.168.43.243:9000/"+id+"/info_search")
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String err = e.getMessage().toString();
                System.out.println("----------------------------sbsbsbs+err"+err);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String success = response.body().string();
                System.out.println("----------------------------sbsbsbs+success"+success);

                try {
                    //解析json包
                    JSONObject jsonObject = new JSONObject(success);
                    String name = jsonObject.getString("name");
                    String sex = jsonObject.getString("sex");
                    String bir = jsonObject.getString("bir");
                    String place = jsonObject.getString("place");
                    String show = jsonObject.getString("show");
                    System.out.println("----------------------------sbsbsbs+success"+name);
                    //设置输入框值
                    setting_names.setText(name);
                    setting_sexs.setText(sex);
                    setting_birthdays.setText(bir);
                    setting_addresss.setText(place);
                    setting_intros.setText(show);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        setting_saves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setting_names.equals("")||setting_sexs.equals("")||setting_birthdays.equals("")||setting_addresss.equals("")||setting_intros.equals(""))
                    Toast.makeText(SettingPerson.this,"请先完善所有信息",Toast.LENGTH_LONG).show();

                else {
                    //获取EditText数据
                    name = setting_names.getText().toString();
                    sex = setting_sexs.getText().toString();
                    bir = setting_birthdays.getText().toString();
                    place = setting_addresss.getText().toString();
                    show = setting_intros.getText().toString();

                    //打包成json格式
                    SettingPersonBean s = new SettingPersonBean();
                    s.setName(name);
                    s.setSex(sex);
                    s.setBir(bir);
                    s.setPlace(place);
                    s.setShow(show);
                    Gson gson = new Gson();
                    final String json = gson.toJson(s);
                    System.out.println("----------------------------sbsbsbs+json"+json);
                    //添加json
                    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody body = RequestBody.create(JSON,String.valueOf(json));

                    //发送post到服务器
                    final Request request = new Request.Builder()
                            .url("http://192.168.43.243:9000/4/set")
                            .post(body)
                            .build();
                    //回调方法
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            String err = e.getMessage().toString();
                            System.out.println("----------------------------sbsbsbs+err"+err);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String rtn = response.body().string();
                            System.out.println("----------------------------sbsbsbs+success"+rtn);

                        }
                    });



                }
            }
        });







    }
}
