package presenter;

import com.google.gson.Gson;

import java.util.HashMap;

import contract.CartContract;
import entity.CartBean;
import model.CartModel;
import model.CartModelCallBack;

public class CartPresenter extends CartContract.CartPresenter {
   private CartModel cartModel;
   private CartContract.CartView cartView;

    public CartPresenter(CartContract.CartView cartView) {
        this.cartModel = new CartModel();
        this.cartView = cartView;
    }
    //
    //
    @Override
    public void getCarts(HashMap<String, String> params) {
        if (cartModel!=null){
            cartModel.getCarts(params, new CartModelCallBack() {
                @Override
                public void success(String result) {
                    CartBean cartBean = new Gson().fromJson(result, CartBean.class);
                    if (cartView!=null)
                    cartView.success(cartBean.resultCartBean);
                }

                @Override
                public void failUre(String msg) {
                    cartView.failUre(msg);
                }
            });
        }

    }
}
