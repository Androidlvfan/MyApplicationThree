package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import entity.CartBean;
import widget.AddMinusView;

public class CartProjectAdapter extends RecyclerView.Adapter<CartProjectAdapter.CartVH> {
   private Context context;

   private CartCallBack cartCallBack;


    public void setCartCallBack(CartCallBack cartCallBack) {
        this.cartCallBack = cartCallBack;
    }

   /* public CartProjectAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }*/

    @NonNull
    @Override
    public CartProjectAdapter.CartVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.three_cart_item_item, viewGroup, false);
        return new CartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartProjectAdapter.CartVH cartVH, final int i) {
       /* final CartBean.DataBean.ListBean listBean = list.get(i);

        String[] split = listBean.images.split("\\|");

        if(split != null && split.length > 0){
            Glide.with(context).load(split[0]).into(cartVH.iv_cart_project);//图片加载
        }
        cartVH.checkbox.setChecked(listBean.isProjectChecked);
        cartVH.project_title.setText(listBean.title);
        cartVH.project_price.setText("$:"+listBean.price);

        cartVH.addminusView.setAddMinusCallBack(new AddMinusView.AddMinusCallBack() {
            @Override
            public void numCallBack(int num) {
                listBean.productNum = num;
                if(cartCallBack != null){
                    cartCallBack.notifyNum();
                }
            }
        });*/

       /* cartVH.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CartBean.DataBean.ListBean bean : list) {
                    list.get(i).isProjectChecked = cartVH.checkbox.isChecked();
                    if (!bean.isProjectChecked){
                        cartCallBack.notifyCartItem(false,listBean.pos);
                        return;
                    }
                    cartCallBack.notifyCartItem(true,listBean.pos);


                }


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CartVH extends RecyclerView.ViewHolder {
        private CheckBox checkbox;
        private ImageView iv_cart_project;
        private TextView project_title,project_price;
        private AddMinusView addminusView;
        public CartVH(@NonNull View itemView) {
            super(itemView);
           checkbox = itemView.findViewById(R.id.checkbox);
            /*iv_cart_project = itemView.findViewById(R.id.iv_cart_project);
            project_title = itemView.findViewById(R.id.project_title);
            project_price = itemView.findViewById(R.id.project_price);*/
            addminusView = itemView.findViewById(R.id.addminusView);
        }
    }
}
