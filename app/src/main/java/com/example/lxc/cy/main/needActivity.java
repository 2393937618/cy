package com.example.lxc.cy.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lxc.cy.R;

import java.util.ArrayList;
import java.util.List;

public class needActivity extends AppCompatActivity {


//    private RadioButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    private Button button;
    private RadioGroup radioGroup1,radioGroup2,radioGroup3;
    private ImageView close;
    private String aText="",bText="",cText="",dText="";
    private List<CheckBox> checkBoxList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need);


        need_init();
        needClick();





    }



    private void need_init(){

        close=(ImageView)findViewById(R.id.need_return);
        button=(Button)findViewById(R.id.next_country);

        checkBox1=(CheckBox) findViewById(R.id.checkbox1);
        checkBox2=(CheckBox) findViewById(R.id.checkbox2);
        checkBox3=(CheckBox) findViewById(R.id.checkbox3);
        checkBox4=(CheckBox) findViewById(R.id.checkbox4);
        checkBox5=(CheckBox) findViewById(R.id.checkbox5);
        checkBox6=(CheckBox) findViewById(R.id.checkbox6);

//        btn1=(RadioButton) findViewById(R.id.peopleButton);
//        btn2=(RadioButton) findViewById(R.id.peopleButton1);
//        btn3=(RadioButton) findViewById(R.id.peopleButton2);
//        btn4=(RadioButton) findViewById(R.id.hobbitybutton);
//        btn5=(RadioButton) findViewById(R.id.hobbitybutton1);
//        btn6=(RadioButton) findViewById(R.id.hobbitybutton2);
//        btn7=(RadioButton) findViewById(R.id.moneyButton);
//        btn8=(RadioButton) findViewById(R.id.moneyButton1);
//        btn9=(RadioButton) findViewById(R.id.moneyButton2);

        radioGroup1=(RadioGroup)findViewById(R.id.people);
        radioGroup2=(RadioGroup)findViewById(R.id.hobbity);
        radioGroup3=(RadioGroup)findViewById(R.id.money);

        //追加到list里面

        checkBoxList.add(checkBox1);
        checkBoxList.add(checkBox2);
        checkBoxList.add(checkBox3);
        checkBoxList.add(checkBox4);
        checkBoxList.add(checkBox5);
        checkBoxList.add(checkBox6);



    }


    /**
     * 点击事件
     */
    private void needClick(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PeopleNum();
                Hobbit();
                Money();
                CheckBoxValue();

                if(aText.length()==0||bText.length()==0||cText.length()==0||dText.length()==0){
                    Toast.makeText(needActivity.this,"还有选项没有选择",Toast.LENGTH_SHORT).show();
                }else {
                    //跳到城市Activity
                    Intent intent=new Intent(needActivity.this,cityActivity.class);
                    startActivity(intent);

                }


            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 人数获取
     */
    private void PeopleNum(){

        for(int i=0;i<radioGroup1.getChildCount();i++){
            RadioButton rb1_1=(RadioButton)radioGroup1.getChildAt(i);
            if(rb1_1.isChecked()){
                bText=rb1_1.getText().toString();
            }
        }
    }


    /**
     * 偏好获取
     */
    private void Hobbit(){
        for(int j=0;j<radioGroup2.getChildCount();j++){
            RadioButton rb1_2=(RadioButton)radioGroup2.getChildAt(j);
            if(rb1_2.isChecked()){
                cText=rb1_2.getText().toString();
            }
        }
    }

    /**
     * 预算获取
     */
    private void Money(){
        for(int h=0;h<radioGroup1.getChildCount();h++){
            RadioButton rb1_3=(RadioButton)radioGroup3.getChildAt(h);
            if(rb1_3.isChecked()){
                dText=rb1_3.getText().toString();
            }
        }
    }


    /**
     * 多选获取
     */
    private void CheckBoxValue(){

        for(CheckBox checkBox:checkBoxList){
            if(checkBox.isChecked()){
                aText=checkBox.getText().toString();
            }
        }

    }


    private void showBottomDialog(){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this);
        //2、设置布局
        View view = View.inflate(this,R.layout.dialog_choosephoto,null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


}
