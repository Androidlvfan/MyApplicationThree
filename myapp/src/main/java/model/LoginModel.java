package model;

import net.OkHttpUtils;
import net.OkhttpCallBack;
import net.RequestCallBack;

import java.util.HashMap;

import api.ApiUrl;
import contract.LoginContract;

public class LoginModel implements LoginContract.LoginModel {


    @Override
    public void register(HashMap<String, String> params,final ModelCallBack modelCallBack) {
        OkHttpUtils.getmInstance().Post(ApiUrl.USER_REG, params, new OkhttpCallBack() {
            @Override
            public void FailUre(String msg) {
                if(modelCallBack != null){
                    modelCallBack.FailUre(msg);
                }
            }

            @Override
            public void Success(String result) {
                if(modelCallBack != null){
                    modelCallBack.Success(result);
                }
            }
        });
    }

    @Override
    public void login(HashMap<String, String> params, final ModelCallBack modelCallBack) {
        OkHttpUtils.getmInstance().Post(ApiUrl.USER_LOGIN, params, new OkhttpCallBack() {
            @Override
            public void FailUre(String msg) {
                if(modelCallBack != null){
                    modelCallBack.FailUre(msg);
                }
            }

            @Override
            public void Success(String result) {
                if(modelCallBack != null){
                    modelCallBack.Success(result);
                }
            }
        });
    }

   public interface ModelCallBack{
        void Success(String result);
        void FailUre(String msg);
    }
}
