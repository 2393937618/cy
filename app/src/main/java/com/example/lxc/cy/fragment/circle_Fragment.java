package com.example.lxc.cy.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lxc.cy.other.OkhttpHelper;
import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.CircleAdapter;
import com.example.lxc.cy.adapter.CircleTextAdapter;
import com.example.lxc.cy.bean.CircleBean;
import com.example.lxc.cy.bean.CircleTextBean;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class circle_Fragment extends Fragment {
    String[] pic,place_name,content_text,user_pic,user_name,pic_1,like_TF,type,like_num,notes_id,owner_id;
    List<String> true_pic,true_pic2;
    String[] pic2,place_name2,content_text2,user_pic2,user_name2,pic_2,like_TF2,type2,like_num2,notes_id2,owner_id2;


    String[] place_name3,content_text3,user_pic3,user_name3,like_TF3,type3,like_num3,notes_id3,owner_id3;
    String[] place_name4,content_text4,user_pic4,user_name4,like_TF4,type4,like_num4,notes_id4,owner_id4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_circle_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //网络连接要在子线程
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final List<CircleBean> list = new LinkedList<CircleBean>();
        final List<CircleTextBean> list2 = new LinkedList<CircleTextBean>();
        //获取listView
        ListView listView = (ListView)getView().findViewById(R.id.lv1);





        Bundle bundle = getArguments();
        if (bundle!=null){
            int arg = bundle.getInt("arg");
            switch (arg){
                case 0://有图片的fragment

                    //建一个适配的变量，将上下文和list加载到ListVIew的适配器中，然后放到适配器变量里
                    CircleAdapter mainListViewAdapter = new CircleAdapter((LinkedList<CircleBean>) list,getActivity());
                    listView.setAdapter(mainListViewAdapter);

                    try {
                        //左列数据获取
                        //pic,place_name,content_text,user_pic,user_name
                        String result = OkhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"tag_content/3");
                        System.out.println("sb_csx---"+result);
                        place_name = OkhttpHelper.Circle_JsonArrays_String(result,"place_name").get(0);
                        content_text = OkhttpHelper.Circle_JsonArrays_String(result,"content_text").get(0);
                        user_pic = OkhttpHelper.Circle_JsonArrays_String(result,"user_pic").get(0);
                        user_name = OkhttpHelper.Circle_JsonArrays_String(result,"user_name").get(0);
                        type = OkhttpHelper.Circle_JsonArrays_String(result,"type").get(0);
                        like_num = OkhttpHelper.Circle_JsonArrays_String(result,"like_num").get(0);
                        notes_id = OkhttpHelper.Circle_JsonArrays_String(result,"notes_id").get(0);
                        owner_id = OkhttpHelper.Circle_JsonArrays_String(result,"owner_id").get(0);
                        /**
                         * pic:获取到的图片有多张以分号隔开
                         * true_pic:接收第一张图片
                         * pic_1:强转为数组格式
                         * pic.length为4，有数据的实际只有前两个0，1
                         */
                        pic = OkhttpHelper.Circle_JsonArrays_String(result,"pic").get(0);
                        true_pic = new ArrayList<>();
                        for(int i=0;i<pic.length/2;i++){
                            System.out.println("sb_csx"+pic[i].split(";")[0]);
                            true_pic.add(pic[i].split(";")[0]);
                        };
                        pic_1 = new String[true_pic.size()];
                        true_pic.toArray(pic_1);




                        //右列数据获取
                        place_name2 = OkhttpHelper.Circle_JsonArrays_String(result,"place_name").get(1);
                        content_text2 = OkhttpHelper.Circle_JsonArrays_String(result,"content_text").get(1);
                        user_pic2 = OkhttpHelper.Circle_JsonArrays_String(result,"user_pic").get(1);
                        user_name2 = OkhttpHelper.Circle_JsonArrays_String(result,"user_name").get(1);
                        type2 = OkhttpHelper.Circle_JsonArrays_String(result,"type").get(1);
                        like_num2 = OkhttpHelper.Circle_JsonArrays_String(result,"like_num").get(1);
                        notes_id2 = OkhttpHelper.Circle_JsonArrays_String(result,"notes_id").get(1);
                        owner_id2 = OkhttpHelper.Circle_JsonArrays_String(result,"owner_id").get(1);
                        //针对pic处理(pic包括好几张图片的数组,true_pic包括一张图片的list,pic_1是包括一张图片的数组)
                        pic2 = OkhttpHelper.Circle_JsonArrays_String(result,"pic").get(1);
                        true_pic2 = new ArrayList<>();
                        for(int i=0;i<pic2.length/2;i++){
                            System.out.println("sb_csx"+pic2[i].split(";")[0]);
                            true_pic2.add(pic2[i].split(";")[0]);
                        }
                        pic_2 = new String[true_pic2.size()];
                        true_pic2.toArray(pic_2);




                        System.out.println("sb_csx"+pic_2.length);
                        like_TF = new String[pic.length];
                        like_TF2 = new String[pic.length];
                        //String pic, int type, String place_name, String content_text, String user_pic, String user_name, int like_num, String pic2, int type2, String place_name2, String content_text2, String user_pic2, String user_name2, int like_num2
                        for (int i=0;i<pic.length/2;i++){

                            //以图片名字转成网络图片地址
                            pic_1[i] = getResources().getString(R.string.ip)+"find_img/"+pic_1[i];
                            pic_2[i] = getResources().getString(R.string.ip)+"find_img/"+pic_2[i];
                            user_pic[i] = getResources().getString(R.string.ip)+user_pic[i];
                            user_pic2[i] = getResources().getString(R.string.ip)+user_pic2[i];
                            like_TF[i] = "0";
                            like_TF2[i] = "0";
                            switch (type[i]){
                                case "0":
                                    type[i] = "游记";
                                    break;
                                case "1":
                                    type[i] = "攻略";
                                    break;
                                case "2":
                                    type[i] = "动态";
                                    break;
                                 default:break;
                            }
                            switch (type2[i]){
                                case "0":
                                    type2[i] = "游记";
                                    break;
                                case "1":
                                    type2[i] = "攻略";
                                    break;
                                case "2":
                                    type2[i] = "动态";
                                    break;
                                default:break;
                            }
                            //加入到CircleBean类型的list集合
                            list.add(new CircleBean(like_TF[i],like_TF2[i],notes_id[i],notes_id2[i],owner_id[i],owner_id2[i],pic_1[i],type[i],place_name[i],content_text[i],user_pic[i],user_name[i],like_num[i],
                                    pic_2[i],type2[i],place_name2[i],content_text2[i],user_pic2[i],user_name2[i],like_num2[i]));
                        }










                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                    mainListViewAdapter.notifyDataSetChanged();
                    break;
                case 1://只有文字的fragment

                    CircleTextAdapter circleTextAdapter = new CircleTextAdapter((LinkedList<CircleTextBean>) list2,getActivity());
                    listView.setAdapter(circleTextAdapter);


                    //左列数据获取
                    //place_name,content_text,user_pic,user_name
                    try {
                        String result2 = OkhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"tag_content_text/3");
                        System.out.println("sb_csx2---"+result2);
                        place_name3 = OkhttpHelper.Circle_JsonArrays_String(result2,"place_name").get(0);
                        content_text3 = OkhttpHelper.Circle_JsonArrays_String(result2,"content_text").get(0);
                        user_pic3 = OkhttpHelper.Circle_JsonArrays_String(result2,"user_pic").get(0);
                        user_name3 = OkhttpHelper.Circle_JsonArrays_String(result2,"user_name").get(0);
                        type3 = OkhttpHelper.Circle_JsonArrays_String(result2,"type").get(0);
                        like_num3 = OkhttpHelper.Circle_JsonArrays_String(result2,"like_num").get(0);
                        notes_id3 = OkhttpHelper.Circle_JsonArrays_String(result2,"notes_id").get(0);
                        owner_id3 = OkhttpHelper.Circle_JsonArrays_String(result2,"owner_id").get(0);



                        //右列数据获取
                        place_name4 = OkhttpHelper.Circle_JsonArrays_String(result2,"place_name").get(1);
                        content_text4 = OkhttpHelper.Circle_JsonArrays_String(result2,"content_text").get(1);
                        user_pic4 = OkhttpHelper.Circle_JsonArrays_String(result2,"user_pic").get(1);
                        user_name4 = OkhttpHelper.Circle_JsonArrays_String(result2,"user_name").get(1);
                        type4 = OkhttpHelper.Circle_JsonArrays_String(result2,"type").get(1);
                        like_num4 = OkhttpHelper.Circle_JsonArrays_String(result2,"like_num").get(1);
                        notes_id4 = OkhttpHelper.Circle_JsonArrays_String(result2,"notes_id").get(1);
                        owner_id4 = OkhttpHelper.Circle_JsonArrays_String(result2,"owner_id").get(1);


                        like_TF3 = new String[user_name3.length];
                        like_TF4 = new String[user_name3.length];
                        for (int i=0;i<user_name3.length/2;i++){
                            user_pic3[i] = getResources().getString(R.string.ip)+user_pic3[i];
                            user_pic4[i] = getResources().getString(R.string.ip)+user_pic4[i];
                            like_TF3[i] = "0";
                            like_TF4[i] = "0";
                            //判断点赞默认是不是为null
                            if(like_num3[i].equals("null")||like_num3[i].equals("")){
                                like_num3[i] = "0";
                            }
                            if(like_num4[i].equals("null")||like_num4[i].equals("")){
                                like_num4[i] = "0";
                            }
                            //判断type类型
                            switch (type3[i]){
                                case "0":
                                    type3[i] = "游记";
                                    break;
                                case "1":
                                    type3[i] = "攻略";
                                    break;
                                case "2":
                                    type3[i] = "动态";
                                    break;
                                default:break;
                            }
                            switch (type4[i]){
                                case "0":
                                    type4[i] = "游记";
                                    break;
                                case "1":
                                    type4[i] = "攻略";
                                    break;
                                case "2":
                                    type4[i] = "动态";
                                    break;
                                default:break;
                            }
                            //加入到CircleBean类型的list集合
                            list2.add(new CircleTextBean(type3[i],place_name3[i],content_text3[i],user_pic3[i],user_name3[i],like_num3[i],
                                    type4[i],place_name4[i],content_text4[i],user_pic4[i],user_name4[i],like_num4[i],
                                    like_TF3[i],like_TF4[i],notes_id3[i],notes_id4[i],owner_id3[i],owner_id4[i]));
                        }



                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                    circleTextAdapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }








    }

}
