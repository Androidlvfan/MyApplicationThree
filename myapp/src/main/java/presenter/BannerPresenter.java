package presenter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import contract.BannerContract;
import entity.BannerBean;
import model.BannerModel;

public class BannerPresenter extends BannerContract.BannerPresenter {
    private BannerModel bannerModel;
    private BannerContract.BannerView bannerView;

    public BannerPresenter(BannerContract.BannerView bannerView) {
        this.bannerModel = new BannerModel();
        this.bannerView = bannerView;
    }

    @Override
    public void Banner(String url, HashMap<String, String> params) {
        bannerModel.Success(url, params, new BannerContract.BannerModelCallBack() {
            @Override
            public void Success(List<BannerBean.ResultBean> resultBeans) {
                bannerView.Success(resultBeans);
            }

            @Override
            public void FailUre(String msg) {
                bannerView.FailUre(msg);
            }
        });
    }
}
