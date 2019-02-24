package model;

import net.OkHttpUtils;
import net.OkhttpCallBack;
import net.RetrofitUtils;

import java.util.HashMap;

import api.Api;
import api.ApiUrl;
import contract.BannerContract;
import entity.BannerBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerModel implements BannerContract.BnannerModel {


    @Override
    public void Success(String url, HashMap<String, String> params, final BannerContract.BannerModelCallBack modelCallBack) {
        Api api = RetrofitUtils.getmIstance().api();
        Call<BannerBean> banner = api.Banner(ApiUrl.GOOD_BANNER, params);
        banner.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                if(modelCallBack != null){
                    modelCallBack.Success(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                if(modelCallBack != null){
                    modelCallBack.FailUre(t.getMessage());
                }
            }
        });
    }
}
