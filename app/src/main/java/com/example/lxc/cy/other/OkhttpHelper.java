package com.example.lxc.cy.other;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.FindImgBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpHelper {


    /**
     * get请求提交
     * @param ip
     * @return
     * @throws IOException
     */
    public static String Okhttp_Get(String ip) throws IOException{


        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ip)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

    /**
     * post请求提交
     * @param o 对象用来转json格式
     * @param url
     * @return post请求返回值
     * @throws IOException
     */
    public  static String postRequest(Object o,String url) throws IOException {

        Gson gson = new Gson();
        //将对象转为json格式
        String data = gson.toJson(o);
        //上传文件格式
        final MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(contentType,String.valueOf(data));
        //post请求上传
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        //返回值获取
        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();



    }


    /**
     * 单个值获取
     * @param result
     * @param name
     * @return
     * @throws JSONException
     */
    public static String JsonString(String result,String name) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        String re = jsonObject.getString(name);

        return  re;
    }




    /**
     * 发送文件
     * @param FileUrl 文件路径
     * @param FileName 文件名
     * @param name 别名
     * @param url ip地址
     * @return
     * @throws IOException
     */
    public static String sendFile(String FileUrl,String FileName,String name,String url) throws IOException {
        File file = new File(FileUrl);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(name,FileName,RequestBody.create(MediaType.parse("multipart/form-data"),file))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization","Client-ID"+UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();


    }


    /**
     * 针对create_time不规则数据提取
     * @param result
     * @param name
     * @return
     * @throws JSONException
     */
    public static String[] Create_time(String result,String name) throws JSONException {

        JSONObject jsonObject = new JSONObject(result);
        String ct = jsonObject.getString(name).replaceAll("\n","");
        String[] create_time = ct.substring(1,ct.length()-1).split(",");
        return create_time;
    }

    public static String[] AddIp(String[] img){
        for(int i=0;i<img.length;i++){
            img[i] = "http://120.79.48.142:80/showImg/"+img[i];
        }
        return img;
    }



    public static String AddOneIp(String img){

            img = "http://120.79.48.142:80/showImg/"+img;

        return img;
    }


    public static List<String[]> ImgUrls(String result,String name) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(name);

        //{1.jpg;1.jpg,2.jpg,3.jpg}
        String[] img_array = new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++){
            img_array[i] = jsonArray.getString(i);
        }

        /**
         * {
         *     {1.jpg,1.jpg},
         *     {2.jpg},
         *     {3.jpg}
         * }
         */
        List<String[]> img_list = new ArrayList<>();
        for (int j=0;j<img_array.length;j++){
            String[] img = img_array[j].split(";");
            img_list.add(img);
//            for(int z=0;z<img.length;z++){
//                img_list.add(new FindImgBean("http://120.79.48.142:80/"+img[z]));
//            }
        }

        return img_list;




    }



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











}
