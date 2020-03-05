package com.example.lxc.cy.fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.FA_attentionAdapter;
import com.example.lxc.cy.adapter.FA_circleAdapter;
import com.example.lxc.cy.adapter.FA_recommendAdapter;
import com.example.lxc.cy.adapter.SR_answerAdapter;
import com.example.lxc.cy.adapter.SR_destinationAdapter;
import com.example.lxc.cy.adapter.SR_strategyAdapter;
import com.example.lxc.cy.adapter.SR_travelAdapter;
import com.example.lxc.cy.adapter.SR_userAdapter;
import com.example.lxc.cy.bean.AnswersBean;
import com.example.lxc.cy.bean.AttentionBean;
import com.example.lxc.cy.bean.DestinationBean;
import com.example.lxc.cy.bean.FindCircleBean;
import com.example.lxc.cy.bean.FindImgBean;
import com.example.lxc.cy.bean.RecommendBean;
import com.example.lxc.cy.bean.StrategyBean;
import com.example.lxc.cy.bean.TravelBean;
import com.example.lxc.cy.bean.UserBean;
import com.example.lxc.cy.other.OkhttpHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class find_attention_Fragment extends Fragment {

    private String result,recommendResult,circleResult;
    private List<String[]> attention_listString,recommend_listString,circle_listString;
    private int attention_length;
    private String tag = "cy_lxc";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fa_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //网络连接要在子线程
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        ListView listView = (ListView) getView().findViewById(R.id.fa_list);
        final List<AttentionBean> attentionBeanList = new LinkedList<AttentionBean>();
        final List<AttentionBean> recommendBeanList = new LinkedList<AttentionBean>();
        final List<FindCircleBean> findCircleBeanList = new LinkedList<FindCircleBean>();


        //Fragment内容填充
        Bundle bundle = getArguments();
        if(bundle!=null){
            int arg = bundle.getInt("arg");
            switch (arg){
                case 0://推荐页
                    FA_attentionAdapter attentionAdapter = new FA_attentionAdapter(getActivity(),(LinkedList<AttentionBean>) attentionBeanList);
                    listView.setAdapter(attentionAdapter);
                    //数据填充
                    try {


                        result = OkhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"main/recommend");
                        Log.i(tag,result);
                        attention_listString = attentionData(result);
                        for(int i = 0;i<attention_length;i++){
                            Log.i(tag,attention_listString.toString());
                            List<FindImgBean> imgBeans = new ArrayList<>();
                            String[] img_url = OkhttpHelper.ImgUrls(result,"pic").get(i);
                            for (int j=0;j<img_url.length;j++){
                                String img = getResources().getString(R.string.ip)+"showImg/"+img_url[j];
                                imgBeans.add(new FindImgBean(img));
                            }
                            attentionBeanList.add(new AttentionBean(attention_listString.get(0)[i],attention_listString.get(1)[i],attention_listString.get(2)[i],attention_listString.get(3)[i],
                                    attention_listString.get(4)[i], attention_listString.get(5)[i],attention_listString.get(6)[i],attention_listString.get(7)[i],attention_listString.get(8)[i]
                            ,attention_listString.get(9)[i],imgBeans));

                        }




                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                    attentionAdapter.notifyDataSetChanged();
                    break;
                case 1://关注页

                    FA_attentionAdapter attentionAdapter1 = new FA_attentionAdapter(getActivity(),(LinkedList<AttentionBean>) attentionBeanList);
                    listView.setAdapter(attentionAdapter1);
                    //数据填充
                    try {


                        result = OkhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"main/attention/3");
                        Log.i(tag,result);
                        attention_listString = attentionData(result);
                        for(int i = 0;i<attention_length;i++){
                            Log.i(tag,attention_listString.toString());
                            List<FindImgBean> imgBeans = new ArrayList<>();
                            String[] img_url = OkhttpHelper.ImgUrls(result,"pic").get(i);
                            for (int j=0;j<img_url.length;j++){
                                String img = getResources().getString(R.string.ip)+"showImg/"+img_url[j];
                                imgBeans.add(new FindImgBean(img));
                            }
                            attentionBeanList.add(new AttentionBean(attention_listString.get(0)[i],attention_listString.get(1)[i],attention_listString.get(2)[i],attention_listString.get(3)[i],
                                    attention_listString.get(4)[i], attention_listString.get(5)[i],attention_listString.get(6)[i],attention_listString.get(7)[i],attention_listString.get(8)[i]
                                    ,attention_listString.get(9)[i],imgBeans));

                        }




                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                    attentionAdapter1.notifyDataSetChanged();

//                    FA_attentionAdapter recommendAdapter = new FA_attentionAdapter(getActivity(),(LinkedList <AttentionBean>)recommendBeanList);
//                    listView.setAdapter(recommendAdapter);
//                    try {
//                        recommendResult = OkhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"main/recommend");
//                        recommend_listString = attentionData(recommendResult);
//                        Log.i(tag,"2"+attention_length);
//                        Log.i(tag,"2"+recommend_listString.toString());
//                        for (int i=0;i<attention_length;i++){
//                            List<FindImgBean> imgBeans = new ArrayList<>();
//                            String[] img_url = OkhttpHelper.ImgUrls(result,"pic").get(i);
//                            for (int j=0;j<img_url.length;j++){
//                                String img = getResources().getString(R.string.ip)+img_url[j];
//                                imgBeans.add(new FindImgBean(img));
//                            }
//                            recommendBeanList.add(new AttentionBean(recommend_listString.get(0)[i],recommend_listString.get(1)[i],recommend_listString.get(2)[i],recommend_listString.get(3)[i],
//                                    recommend_listString.get(4)[i], recommend_listString.get(5)[i],recommend_listString.get(6)[i],recommend_listString.get(7)[i],recommend_listString.get(8)[i]
//                                    ,recommend_listString.get(9)[i],imgBeans));
//                        }
//
//                    } catch (IOException | JSONException e) {
//                        e.printStackTrace();
//                    }


//                    recommendAdapter.notifyDataSetChanged();
                    break;
                case 2://圈子页
                    FA_circleAdapter circleAdapter = new FA_circleAdapter(getActivity(),(LinkedList<FindCircleBean>)findCircleBeanList);
                    listView.setAdapter(circleAdapter);



                    circleAdapter.notifyDataSetChanged();

                    break;
                default:break;
            }
        }

    }


    /**
     * 初始化
     * @throws IOException
     */
    private void init() throws IOException {


    }


    /**
     * 关注页数据
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<String[]> attentionData(String r) throws IOException, JSONException {
        List<String[]> list = new ArrayList<String[]>();
        //获取数据个数
        attention_length = OkhttpHelper.JsonArrays(r,"like_num").length;
        String[] like_TF = new String[attention_length];
        //初始化like_TF数据
        for(int i = 0;i<attention_length;i++){
            like_TF[i] = "0";
        }
        //将数据加入list
        list.add(like_TF);
        list.add(OkhttpHelper.JsonArrays(r,"notes_id"));
        list.add(OkhttpHelper.JsonArrays(r,"owner_id"));
        list.add(OkhttpHelper.AddIp(OkhttpHelper.JsonArrays(r,"user_pic")));
        list.add(OkhttpHelper.JsonArrays(r,"user_name"));
        list.add(OkhttpHelper.Create_time(r,"create_time"));
        list.add(OkhttpHelper.JsonArrays(r,"content_text"));
        list.add(OkhttpHelper.JsonArrays(r,"place_name"));
        list.add(OkhttpHelper.JsonArrays(r,"comment_num"));
        list.add(OkhttpHelper.JsonArrays(r,"like_num"));



        return list;
    }
}
