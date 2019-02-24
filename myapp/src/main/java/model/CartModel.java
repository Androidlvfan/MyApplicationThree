package model;

import net.OkHttpUtils;
import net.OkhttpCallBack;

import java.util.HashMap;

import api.ApiUrl;
import contract.CartContract;

public class CartModel implements CartContract.CartModel {

    @Override
    public void getCarts(HashMap<String, String> params, final CartModelCallBack cartCallBack) {
        OkHttpUtils.getmInstance().Get(ApiUrl.CART_GOOD, params, new OkhttpCallBack() {
            @Override
            public void FailUre(String msg) {
                if(cartCallBack != null){
                    cartCallBack.failUre(msg);
                }
            }

            @Override
            public void Success(String result) {
                if(cartCallBack != null){
                    cartCallBack.success(result);
                }
            }
        });
    }

}
