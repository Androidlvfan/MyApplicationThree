package net;

import android.os.AsyncTask;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import api.ApiUrl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private Handler handler = new Handler();
    private OkHttpClient okHttpClient;
    public static OkHttpUtils mInstance;
    /**
     * 私有构造
     */
    private OkHttpUtils(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    /**
     * 实例化
     */
    public static OkHttpUtils getmInstance() {
        if(mInstance == null){
            synchronized (OkHttpUtils.class){
               if(mInstance == null){
                   mInstance = new OkHttpUtils();
               }
            }
        }
        return mInstance;
    }
    /**
     * get请求
     */
    public void Get(String url,HashMap<String,String> params,final OkhttpCallBack okhttpCallBack){
        StringBuilder builder = new StringBuilder();

        String nurl = url;
         if(params != null && params.size()>0){
            for(Map.Entry<String,String> map : params.entrySet()){
                builder.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }
            nurl = url+"?"+builder.toString();
        }

        Request request = new Request.Builder().url(nurl).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(okhttpCallBack != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallBack.FailUre("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if(okhttpCallBack != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallBack.Success(result);
                        }
                    });
                }
            }
        });
    }
    /**
     * post请求
     */
    public void Post(String url, HashMap<String,String> params,final OkhttpCallBack okhttpCallBack){

        FormBody.Builder  formBody = new FormBody.Builder();
        for(Map.Entry<String,String> p : params.entrySet()){
             formBody.add(p.getKey(),p.getValue());
        }
        RequestBody requestBody = formBody.build();
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallBack != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallBack.FailUre("网络异常");
                        }
                    });
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(okhttpCallBack != null){
                    if (response.code() == 200){
                      final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallBack.Success(result);
                            }
                        });
                    }
                }
            }
        });
    }

}
