package com.example.lxc.cy.main;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.airsaid.pickerviewlibrary.TimePickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.SettingPersonBean;
import com.example.lxc.cy.other.OkhttpHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingPerson extends AppCompatActivity {

//    EditText setting_names,setting_sexs,setting_birthdays,setting_addresss,setting_intros;
    String name="",sex = "",bir="",address="",show="";
    EditText update_name,update_show;
    Button update_sex,update_address,update_data,setting_btn;
    String ip="";
    String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_person);

        Setting_init();

        OnClickButton();







//        //获取账号id
//        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
//        String id = sharedPreferences.getString("uid","");
//        System.out.println("----------------------------sbsbsbs+sharedPreferences"+id);
//
//        //1.创建OkHttpClient对象
//        OkHttpClient okHttpClient = new OkHttpClient();
//        //2.创建Request对象，设置一个url地址,设置请求方式。
//        Request request = new Request.Builder()
//                .url("http://192.168.43.243:9000/"+id+"/info_search")
//                .get()
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                String err = e.getMessage().toString();
//                System.out.println("----------------------------sbsbsbs+err"+err);
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String success = response.body().string();
//                System.out.println("----------------------------sbsbsbs+success"+success);
//
//                try {
//                    //解析json包
//                    JSONObject jsonObject = new JSONObject(success);
//                    String name = jsonObject.getString("name");
//                    String sex = jsonObject.getString("sex");
//                    String bir = jsonObject.getString("bir");
//                    String place = jsonObject.getString("place");
//                    String show = jsonObject.getString("show");
//                    System.out.println("----------------------------sbsbsbs+success"+name);
//                    //设置输入框值
//                    setting_names.setText(name);
//                    setting_sexs.setText(sex);
//                    setting_birthdays.setText(bir);
//                    setting_addresss.setText(place);
//                    setting_intros.setText(show);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//
//        setting_saves.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(setting_names.equals("")||setting_sexs.equals("")||setting_birthdays.equals("")||setting_addresss.equals("")||setting_intros.equals(""))
//                    Toast.makeText(SettingPerson.this,"请先完善所有信息",Toast.LENGTH_LONG).show();
//
//                else {
//                    //获取EditText数据
//                    name = setting_names.getText().toString();
//                    sex = setting_sexs.getText().toString();
//                    bir = setting_birthdays.getText().toString();
//                    place = setting_addresss.getText().toString();
//                    show = setting_intros.getText().toString();
//
//                    //打包成json格式
//                    SettingPersonBean s = new SettingPersonBean();
//                    s.setName(name);
//                    s.setSex(sex);
//                    s.setBir(bir);
//                    s.setPlace(place);
//                    s.setShow(show);
//                    Gson gson = new Gson();
//                    final String json = gson.toJson(s);
//                    System.out.println("----------------------------sbsbsbs+json"+json);
//                    //添加json
//                    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                    OkHttpClient okHttpClient = new OkHttpClient();
//                    RequestBody body = RequestBody.create(JSON,String.valueOf(json));
//
//                    //发送post到服务器
//                    final Request request = new Request.Builder()
//                            .url("http://192.168.43.243:9000/4/set")
//                            .post(body)
//                            .build();
//                    //回调方法
//                    Call call = okHttpClient.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            String err = e.getMessage().toString();
//                            System.out.println("----------------------------sbsbsbs+err"+err);
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            final String rtn = response.body().string();
//                            System.out.println("----------------------------sbsbsbs+success"+rtn);
//
//                        }
//                    });
//
//
//
//                }
//            }
//        });







    }


    private void Setting_init(){
//        setting_names = (EditText)findViewById(R.id.setting_name);
//        setting_sexs = (EditText)findViewById(R.id.setting_sex);
//        setting_birthdays = (EditText)findViewById(R.id.setting_birthday);
//        setting_addresss = (EditText)findViewById(R.id.setting_address);
//        setting_intros = (EditText)findViewById(R.id.setting_intro);
//        setting_saves = (TextView)findViewById(R.id.setting_save);

        update_sex = (Button)findViewById(R.id.update_sex);
        update_address = (Button)findViewById(R.id.update_address);
        update_data = (Button)findViewById(R.id.update_data);
        update_name = (EditText)findViewById(R.id.update_name);
        update_show = (EditText)findViewById(R.id.update_show);
        setting_btn = (Button)findViewById(R.id.setting_btn);

        //初始化参数
        ip = getResources().getString(R.string.ip);
        //id值
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        uid = sharedPreferences.getString("uid","");

        Begin_data();
    }


    private void OnClickButton(){


        update_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex();
            }
        });

        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data();
            }
        });

        update_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address();
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Setting_data();
            }
        });
    }



    //数据首次显示
    private void Begin_data(){
        //                    String name = jsonObject.getString("name");
//                    String sex = jsonObject.getString("sex");
//                    String bir = jsonObject.getString("bir");
//                    String place = jsonObject.getString("place");
//                    String show = jsonObject.getString("show");
        try {
            String result = OkhttpHelper.Okhttp_Get(ip+uid+"/info_search");
            update_name.setText(OkhttpHelper.JsonString(result,"name"));
            update_sex.setText(OkhttpHelper.JsonString(result,"sex"));
            update_address.setText(OkhttpHelper.JsonString(result,"place"));
            update_data.setText(OkhttpHelper.JsonString(result,"bir"));
            update_show.setText(OkhttpHelper.JsonString(result,"show"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }


    //获取值并上传
    private void Setting_data(){
        name = update_name.getText().toString();
        show = update_show.getText().toString();

        if(sex.equals("") || address.equals("") || bir.equals("") || name.equals("") || show.equals("")){
            Toast.makeText(SettingPerson.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
        }else {
            SettingPersonBean personBean = new SettingPersonBean(name,sex,bir,address,show);
            try {
                String result = OkhttpHelper.postRequest(personBean,ip+uid+"/set");
                Log.i("cy_lxc",result);
                Toast.makeText(SettingPerson.this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sex(){
        OptionsPickerView<String> optionsPickerView = new OptionsPickerView<>(SettingPerson.this);
        final ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        //设置数据
        optionsPickerView.setPicker(list);
        //返回性别
        optionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String s = list.get(option1);
                Toast.makeText(SettingPerson.this,s,Toast.LENGTH_SHORT).show();
                update_sex.setText(s);
                sex = s;
            }
        });

        optionsPickerView.show();

    }



    private void address(){
        CityPickerView cityPickerView = new CityPickerView(SettingPerson.this);
        //设置点击外部消失
//        cityPickerView.setCancelable(true);
        //设置滚轮字体大小
        cityPickerView.setTextSize(15f);
        //设置标题
        cityPickerView.setTitle("选择城市");
        //设置取消文字
        cityPickerView.setCancelText("取消");
        //设置取消文字颜色
        cityPickerView.setCancelTextColor(Color.RED);
        //设置取消文字大小
        cityPickerView.setCancelTextSize(13f);
        //设置确定文字
        cityPickerView.setSubmitText("确定");
        //设置确定文字颜色
        cityPickerView.setSubmitTextColor(Color.BLACK);
        //设置确定文字大小
        cityPickerView.setSubmitTextSize(13f);
        //设置头部背景
        cityPickerView.setHeadBackgroundColor(Color.WHITE);
        cityPickerView.setOnCitySelectListener(new OnSimpleCitySelectListener(){
            @Override
            public void onCitySelect(String prov, String city, String area) {
                super.onCitySelect(prov, city, area);
                String buffer = prov+"省"+city+"市"+area+"区";
                Toast.makeText(SettingPerson.this,buffer,Toast.LENGTH_SHORT).show();
                address = buffer;
                update_address.setText(buffer);

            }
        });

        cityPickerView.show();

    }




    private void data(){
        TimePickerView timePickerView = new TimePickerView(SettingPerson.this,TimePickerView.Type.YEAR_MONTH_DAY);
        //设置是否循环
        timePickerView.setCyclic(true);
        //设置滚轮文字大小
        timePickerView.setTextSize(TimePickerView.TextSize.SMALL);
        //设置时间可选范围(结合 setTime 方法使用,必须在)
        Calendar calendar = Calendar.getInstance();
        timePickerView.setRange(calendar.get(Calendar.YEAR) - 100, calendar.get(Calendar.YEAR));
        //设置选中时间
        timePickerView.setTime(new Date());
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                //时期格式
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String d = format.format(date);
                Toast.makeText(SettingPerson.this,d,Toast.LENGTH_SHORT).show();
                update_data.setText(d);
                bir = d;
            }
        });

        timePickerView.show();
    }
}
