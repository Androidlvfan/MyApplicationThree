package net;

import api.Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static RetrofitUtils mIstance;
    public Retrofit retrofit;

    /**
     * 私有化
     */
    private RetrofitUtils(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitUtils getmIstance() {
        if(mIstance == null){
            synchronized (RetrofitUtils.class){
                mIstance = new RetrofitUtils();
            }
        }
        return mIstance;
    }

    public Api api(){
        return retrofit.create(Api.class);
    }
}
