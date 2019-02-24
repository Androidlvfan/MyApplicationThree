package contract;

import java.util.HashMap;
import java.util.List;

import entity.BannerBean;
import model.BannerModel;

public interface BannerContract {
    /**
     * p
     */
    public abstract class BannerPresenter{
        public abstract void Banner(String url,HashMap<String,String> params);
    }
    /**
     * m
     */
    public interface BnannerModel{
        void Success(String url,HashMap<String,String> params, BannerModelCallBack modelCallBack);
    }
    /**
     * v
     */
    public interface BannerView{
        void Success(List<BannerBean.ResultBean> resultBean);
        void FailUre(String msg);
    }
    /**
     * callBack
     */
    public interface BannerModelCallBack{
        void Success(List<BannerBean.ResultBean> resultBeans);
        void FailUre(String msg);
    }
}
