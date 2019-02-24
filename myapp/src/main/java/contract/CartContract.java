package contract;

import java.util.HashMap;
import java.util.List;

import entity.CartBean;
import model.CartModel;
import model.CartModelCallBack;

public interface CartContract {
    /**
     * p
     */
    public abstract class CartPresenter{
        public abstract void getCarts(HashMap<String,String> params);
    }
    /**
     * m
     */
    public interface CartModel{
        void getCarts(HashMap<String,String> params, CartModelCallBack cartCallBack);
    }
    /**
     * v
     */
    public interface CartView{
        void success(List<CartBean.ResultCartBean> list);
        void failUre(String msg);
    }
}
