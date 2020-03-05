package com.example.lxc.cy.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.lxc.cy.R;

public class search_main extends AppCompatActivity {

    private SuggestionSearch mSuggestionSearch;
    private EditText text;
    private TextView text_show;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        Button imageButton = (Button) findViewById(R.id.btn);
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.grogshop);
        ImageButton imageButton2 = (ImageButton)findViewById(R.id.close_btn);
        text = (EditText) findViewById(R.id.edittext);
        text_show = (TextView) findViewById(R.id.search_history_text);
        button = (Button) findViewById(R.id.btn);
        /**
         * 地点检索
         */
        //创建Sug检索实例
        mSuggestionSearch = SuggestionSearch.newInstance();
        //创建Sug检索监听器
        OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                //处理sug检索结果
                if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                    //未找到相关结果
                    Toast.makeText(search_main.this,"not search",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(search_main.this,"search",Toast.LENGTH_SHORT).show();
//                    text_show.setText(suggestionResult.getAllSuggestions().toString());
                }

            }
        };
        //设置Sug检索监听器
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city("北京")
                        .keyword("肯"));

                Intent intent = new Intent(search_main.this,search_result.class);
                intent.putExtra("city",text.getText().toString());
                startActivity(intent);
            }
        });

        //监听输入框
//        text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });





        /**
         * 类型图片跳转
         */
//        imageButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(search_main.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(search_main.this,search_result.class);
//                startActivity(intent);
//            }
//        });
//
//        imageButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(search_main.this,grogshopActivity.class);
//                intent.putExtra("result","深圳");
//                startActivity(intent);
//            }
//        });


    }
}
