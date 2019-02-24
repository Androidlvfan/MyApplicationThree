package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

import java.util.List;

import callback.CartCallBack;
import callback.CartUICallBack;
import entity.CartBean;
import widget.AddMinusView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVh> {
    private Context context;
    private List<CartBean.ResultCartBean> cartdata;

    private CartUICallBack cartCallBack;//总价接口

    public void setCartCallBack(CartUICallBack cartCallBack) {
        this.cartCallBack = cartCallBack;
    }

    public CartAdapter(Context context, List<CartBean.ResultCartBean> cartdata) {
        this.context = context;
        this.cartdata = cartdata;
    }

    @NonNull
    @Override
    public CartAdapter.CartVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.three_cart_item_item, viewGroup, false);
        return new CartVh(view);//强转
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.CartVh cartVh, int i) {

        CartBean.ResultCartBean resultCartBean = cartdata.get(i);

        cartVh.cart_project_title.setText(resultCartBean.getCommodityName());
        cartVh.cart_project_price.setText(resultCartBean.getPrice()+"");
        cartVh.cart_checkbox.setChecked(resultCartBean.isChecked);

        //图片加载
        String[] split = resultCartBean.pic.split("\\|");

        if(split != null && split.length > 0){
            Glide.with(context).load(split[0]).into(cartVh.cart_project_icon);
        }
       // Glide.with(context).load(resultCartBean.getPic()).into(cartVh.cart_project_icon);

       //多选框选中
        cartVh.cart_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartCallBack != null){
                    cartCallBack.notifyCart();//价钱接口
                }
                notifyDataSetChanged();//【刷新】
            }
        });

       /*for(CartBean.DataBean.ListBean project : dataBean.list){
            project.pos = i;//这里用了一个pos
        }*/
            //另一个的Adapter
        //CartProjectAdapter cartProjectAdapter = new CartProjectAdapter(context,dataBean.list);
        //cartProjectAdapter.setCartCallBack(this);
        //cartVh.three_rcv_item.setAdapter(cartProjectAdapter);
        //设置适配器
        //cartVh.three_rcv_item.setLayoutManager(new LinearLayoutManager(context));
        /**
         * 一级选中监听【商家多选框】
         */
       /* cartVh.checkbox_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.isChecked = cartVh.checkbox_item.isChecked();//设置为一级选中
                *//*for(CartBean.DataBean.ListBean list : dataBean.list){
                    list.isProjectChecked = dataBean.isChecked;//一级选中关联二级选中
                }*//*
                notifyDataSetChanged();//【刷新】

                if(cartCallBack != null){
                    cartCallBack.notifyCart();
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return cartdata.size();
    }

   /* @Override
    public void notifyCartItem(boolean isChecked, int position) {

        data.get(position).isChecked = isChecked;
        notifyDataSetChanged();//【刷新】

        if(cartCallBack != null){
            cartCallBack.notifyCart();
        }
    }*/

    /*@Override
    public void notifyNum() {
        if(cartCallBack != null){
            cartCallBack.notifyCart();
        }
    }*/

   class CartVh extends RecyclerView.ViewHolder {

       private CheckBox cart_checkbox;
       private ImageView cart_project_icon;
       private TextView cart_project_title,cart_project_price;
       private AddMinusView addminusView;
       public CartVh(@NonNull View itemView) {
           super(itemView);
           cart_checkbox = itemView.findViewById(R.id.cart_checkbox);
           cart_project_icon = itemView.findViewById(R.id.cart_project_icon);
           cart_project_price = itemView.findViewById(R.id.cart_project_price);
           cart_project_title = itemView.findViewById(R.id.cart_project_title);
           addminusView = itemView.findViewById(R.id.addminusView);
       }
   }
}
