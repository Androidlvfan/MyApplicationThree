package model;

import net.OkHttpUtils;
import net.OkhttpCallBack;
import java.util.HashMap;
import api.ApiUrl;
import contract.MainGoodContract;

public class MainGoodModel implements MainGoodContract.MainGoodModel {

    @Override
    public void MainGood(HashMap<String, String> params, final MainModelCallBack mainModelCallBack) {
        OkHttpUtils.getmInstance().Get(ApiUrl.MAIN_GOOD, params, new OkhttpCallBack() {
            @Override
            public void FailUre(String msg) {
                if(mainModelCallBack != null){
                    mainModelCallBack.FailUre(msg);
                }
            }

            @Override
            public void Success(String result) {
                if(mainModelCallBack != null){
                    mainModelCallBack.Success(result);
                }
            }
        });
    }

    public interface MainModelCallBack{
       void Success(String result);
       void FailUre(String msg);
   }
}
