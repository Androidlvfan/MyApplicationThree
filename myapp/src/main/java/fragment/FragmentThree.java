package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.CartAdapter;
import callback.CartUICallBack;
import contract.CartContract;
import entity.CartBean;
import presenter.CartPresenter;

public class FragmentThree extends Fragment implements CartContract.CartView, CartUICallBack {
 private RecyclerView three_rcv;
 private CheckBox sum_price;//[总价]
 private CartPresenter cartPresenter;
 private CartAdapter cartAdapter;
 private List<CartBean.ResultCartBean> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        cartPresenter = new CartPresenter(this);
        data = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("userId","21");
        params.put("sessionId","155073376761421");
        cartPresenter.getCarts(params);
    }

    private void initView(View view) {
       three_rcv = view.findViewById(R.id.three_rcv);//rev
       sum_price = view.findViewById(R.id.sum_price);//总价
       three_rcv.setLayoutManager(new LinearLayoutManager(getActivity()));

        /**
         *  点击总价的多选框监听
         */
       sum_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               //已选中
               if(isChecked){
                   /**
                    * 遍历所有，找到一级选中都设置为true
                    */
                   for(CartBean.ResultCartBean datas : data) {
                       datas.isChecked = true;//【商家的多选框设置为true】【一级选中】
                   }
               //未选中
               }else{
                   /**
                    * 遍历所有，找到一级选中都设置为false
                    */
                    for(CartBean.ResultCartBean datas : data){
                        datas.isChecked = false;//【商家的多选框设置为false】【一级选中】
                    }
               }
               cartAdapter.notifyDataSetChanged();//【刷新】
               getTotalPrice();//【调用总价】
           }
       });
    }

    /**
     * 计算总价方法
     */
    private void getTotalPrice() {
        double totalprice = 0;
        for(CartBean.ResultCartBean datas : data){
                totalprice += datas.price * datas.productNum;
        }
        sum_price.setText("全选  合计：$"+totalprice);
    }

    /**
     * 购物车请求成功
     * @param list
     */
    @Override
    public void success(List<CartBean.ResultCartBean> list) {
        Toast.makeText(getActivity(),"购物车请求成功"+list,Toast.LENGTH_SHORT).show();
        if(list != null){
            data = list;
            for(CartBean.ResultCartBean cart : data) {
                cart.productNum = 1;
            }
            cartAdapter = new CartAdapter(getActivity(),data);
            cartAdapter.setCartCallBack(this);
            three_rcv.setAdapter(cartAdapter);
        }else{
            Toast.makeText(getActivity(),"你的购物车为"+list,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failUre(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 价钱接口
     */
    @Override
    public void notifyCart() {
        getTotalPrice();
    }


}
