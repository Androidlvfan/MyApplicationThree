package contract;

import java.util.HashMap;
import java.util.List;

import entity.MainGoodBean;
import model.MainGoodModel;

public interface MainGoodContract {
    /**
     * p
     */
    public abstract class MainGoodPersenter{
       public abstract void MainGood(HashMap<String,String> params);
    }
    /**
     * m
     */
    public interface MainGoodModel{
        void MainGood(HashMap<String,String> params, model.MainGoodModel.MainModelCallBack mainModelCallBack);
    }
    /**
     * v
     */
    public interface MainGoodView{
        void Successs(MainGoodBean.ResultBean resultBean);
        void FailUre(String msg);
    }
}
