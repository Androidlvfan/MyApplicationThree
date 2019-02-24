package presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import contract.MainGoodContract;
import entity.MainGoodBean;
import model.MainGoodModel;

public class MainGoodPresenter extends MainGoodContract.MainGoodPersenter {
    private MainGoodModel mainGoodModel;
    private MainGoodContract.MainGoodView mainGoodView;

    public MainGoodPresenter(MainGoodContract.MainGoodView mainGoodView) {
        this.mainGoodModel = new MainGoodModel();
        this.mainGoodView = mainGoodView;
    }

    @Override
    public void MainGood(HashMap<String, String> params) {
        mainGoodModel.MainGood(params, new MainGoodModel.MainModelCallBack() {
            @Override
            public void Success(String result) {
                if(mainGoodModel != null){
                    MainGoodBean mainGoodBean = new Gson().fromJson(result, MainGoodBean.class);
                    MainGoodBean.ResultBean resultBean = mainGoodBean.getResult();
                   mainGoodView.Successs(resultBean);
                }
            }

            @Override
            public void FailUre(String msg) {

            }
        });
    }
}
