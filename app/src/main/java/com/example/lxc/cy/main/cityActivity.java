package com.example.lxc.cy.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.listener.OnSimpleCitySelectListener;
import com.example.lxc.cy.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cityActivity extends AppCompatActivity {


//    private ImageButton btn;
//    LinearLayout linearLayout,linearLayout1;
//    private TextView textView;
//    public LayoutInflater inflater;
//    Button button;

    Button nextScenic,addCityEnd,addBtn,addCityAction,choose_city;
    LinearLayout addCity;
    TextView cityText,date_add,date_num,date_reduce;
    ImageView cityReturn;
    private LayoutInflater inflater;
    private List<String> cityNames;
    private List<String> dateNum;
    private String actionCity="",endCity="";
    private View itemLayout;
    private int i = 0;
    private Map<Integer,String> citys;
    private Map<Integer,String> nums;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);





//        inflater=LayoutInflater.from(this);
//
//        btn=(ImageButton)findViewById(R.id.add_btn);
//        linearLayout=(LinearLayout)findViewById(R.id.add_city);
//
//        button = (Button)findViewById(R.id.add_finsh_go);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                linearLayout1=(LinearLayout)inflater.inflate(R.layout.layout_city,null).findViewById(R.id.add_city_layout1);
//                textView=(TextView)linearLayout1.findViewById(R.id.content_tv);
//                linearLayout.addView(linearLayout1);
//            }
//        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(cityActivity.this,viewActivity.class);
//                startActivity(intent);
//            }
//        });
        



        city_init();
        city_click();





    }


    private void city_init(){
        nextScenic = (Button) findViewById(R.id.next_scenic);
        addCityEnd = (Button) findViewById(R.id.add_city_end);
        addBtn = (Button) findViewById(R.id.add_btn);
        addCity = (LinearLayout) findViewById(R.id.add_city);
        addCityAction = (Button) findViewById(R.id.city_action);
        cityText = (TextView) findViewById(R.id.city_text);
        cityReturn = (ImageView) findViewById(R.id.city_return);

        inflater = LayoutInflater.from(cityActivity.this);
        nums = new HashMap<Integer, String>();
        citys = new HashMap<Integer, String>();
//        city_delete = (ImageButton) itemLayout.findViewById(R.id.city_delete);
//        choose_city = (Button)itemLayout.findViewById(R.id.choose_city);
//        date_add = (TextView)itemLayout.findViewById(R.id.date_add);
//        date_num = (TextView)itemLayout.findViewById(R.id.date_num);
//        date_reduce = (TextView)itemLayout.findViewById(R.id.date_reduce);


    }


    private void city_click(){

        nextScenic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cy_lxc",citys.toString());
                Log.i("cy_lxc",nums.toString());
                Intent intent = new Intent(cityActivity.this,ScenicActivity.class);
                intent.putExtra("cityNames", (Serializable) citys);
                startActivity(intent);
            }
        });

        addCityAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCitys("action");
            }
        });

        addCityEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCitys("end");
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLayout = inflater.inflate(R.layout.layout_city,null);
                itemLayout.setId(i);
                addCity.addView(itemLayout,i);
                getViewInstance(itemLayout);
                i++;
            }
        });





    }



    private void getViewInstance(final View childView){
        final CityViewHolder holder = new CityViewHolder();
        holder.id = childView.getId();
        holder.city_delete = (ImageButton)childView.findViewById(R.id.city_delete);
        holder.city_choose = (Button)childView.findViewById(R.id.choose_city);
        holder.city_add = (TextView)childView.findViewById(R.id.date_add);
        holder.city_num = (TextView)childView.findViewById(R.id.date_num);
        holder.city_reduce = (TextView)childView.findViewById(R.id.date_reduce);
        nums.put(holder.id,holder.city_num.getText().toString());
        holder.city_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity.removeViewAt(addCity.getChildCount()-1);
                i--;
            }
        });

        holder.city_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.city_num.getText().toString());
                num++;
                holder.city_num.setText(String.valueOf(num));
                nums.put(holder.id,holder.city_num.getText().toString());
            }
        });


        holder.city_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(holder.city_num.getText().toString());
                num--;
                holder.city_num.setText(String.valueOf(num));
                nums.put(holder.id,holder.city_num.getText().toString());
            }
        });

        holder.city_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPickerView cityPickerView = new CityPickerView(cityActivity.this);
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
                        String buffer = prov+"省"+city+"市"+area;
                        holder.city_choose.setText(buffer);
                        citys.put(holder.id,buffer);

                    }
                });

                cityPickerView.show();
            }
        });


    }




    private void chooseCitys(final String arg){

        CityPickerView cityPickerView = new CityPickerView(cityActivity.this);
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
                String buffer = prov+"省"+city+"市"+area;
                switch (arg){
                    case "action":
                        addCityAction.setText(buffer);
                        break;
                    case "end":
                        addCityEnd.setText(buffer);
                        break;
                        default:
                            break;
                }


            }
        });

        cityPickerView.show();

    }

    public class CityViewHolder{
        private int id = 0;
        private ImageButton city_delete;
        private Button city_choose;
        private TextView city_add;
        private TextView city_num;
        private TextView city_reduce;

    }



}
