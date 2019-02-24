package api;

import java.util.HashMap;
import java.util.Map;

import entity.BannerBean;
import entity.FindBean;
import entity.QuanBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    /**
     * 首页轮播图
     * @param url
     * @param params
     * @return
     */
    @GET
    Call<BannerBean> Banner(@Url String url, @QueryMap HashMap<String,String> params);



    @Headers({
            "userId:1010",
            "sessionId:15320748258726"
    })
    @GET
    Call<FindBean> Find_Show(@Url String url, @QueryMap HashMap<String,String> params);




}
