package com.example.lxc.cy.other;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpHelper {



    public static String[] JsonArrays(String result,String name) throws IOException, JSONException {



            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray(name);

            String[] json = new String[jsonArray.length()];

            for(int i=0;i<jsonArray.length();i++){
                json[i] = jsonArray.getString(i);
            }




        return json;


    }




    //针对两条listView的数据解析
    public static List<String[]> Circle_JsonArrays_String(String result, String name) throws IOException, JSONException {
        /**
         * result:json字符串
         * name：json里面的key
         * len：value的长度判断是不是偶数，不是减一
         * json：获取奇数列的数据
         * json2获取偶数列数据
         * j：数组的下标
         * 比如有四条数据，数组长度为4即len，有数据的长度就为2即j
         *
         */
        List<String[]> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(name);

        int len = jsonArray.length();
        if(len%2 != 0){
            len = len-1;
        }
        String[] json = new String[len];
        String[] json2 = new String[len];
        int j = 0;
        for(int i=0;i<len;i=i+2){
            System.out.println("i="+i);
            System.out.println("j="+j);
            json[j] = jsonArray.getString(i);
            System.out.println("json[j]="+json[j]);
            json2[j] = jsonArray.getString(i+1);
            System.out.println("json2[j]="+json2[j]);
            j++;
        }


        list.add(json);
        list.add(json2);




        return list;


    }


    public static List<int[]> Circle_JsonArrays_int(String result,String name) throws IOException, JSONException {



        List<int[]> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(name);
        int len = jsonArray.length();
        if(len%2 != 0){
            len = len-1;
        }
        int[] json = new int[len];
        int[] json2 = new int[len];
        int j = 0;
        for(int i=0;i<jsonArray.length();i=i+2){
            json[j] = Integer.parseInt(jsonArray.getString(i));
            json2[j] = Integer.parseInt(jsonArray.getString(i+1));
            j++;
        }

        list.add(json);
        list.add(json2);



        return list;


    }





    public static int[] JsonArrays_int(String result,String name) throws IOException, JSONException {



        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(name);

        int[] json = new int[jsonArray.length()];

        for(int i=0;i<jsonArray.length();i++){
            json[i] = Integer.parseInt(jsonArray.getString(i));
        }



        return json;


    }







    public static String Okhttp_Get(String ip) throws IOException{


        OkHttpClient okHttpClient = new OkHttpClient();




            Request request = new Request.Builder()
                    .url(ip)
                    .get()
                    .build();


            Response response = okHttpClient.newCall(request).execute();




            return response.body().string();






    }



}
