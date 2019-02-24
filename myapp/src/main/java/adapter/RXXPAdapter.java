package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

import java.util.List;

import entity.MainGoodBean;

public class RXXPAdapter extends RecyclerView.Adapter<RXXPAdapter.RXXP> {
private Context context;
private List<MainGoodBean.ResultBean.RxxpBean.CommodityListBean> list;

    public RXXPAdapter(Context context, List<MainGoodBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RXXP onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /**
         * 接入布局
         */
        View view = LayoutInflater.from(context).inflate(R.layout.item_rxxp_item, viewGroup, false);
        RXXP rxxp = new RXXP(view);
        return rxxp;
    }

    @Override
    public void onBindViewHolder(@NonNull RXXP rxxp, int i) {

        Glide.with(context).load(list.get(i).getMasterPic()).into(rxxp.rxxp_item_icon);
        rxxp.rxxp_item_title.setText(list.get(i).getCommodityName());
        rxxp.rxxp_item_price.setText("￥:"+list.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RXXP extends RecyclerView.ViewHolder {
        private ImageView rxxp_item_icon;
        private TextView rxxp_item_title,rxxp_item_price;
        public RXXP(@NonNull View itemView) {
            super(itemView);
            rxxp_item_icon = itemView.findViewById(R.id.rxxp_item_icon);
            rxxp_item_title = itemView.findViewById(R.id.rxxp_item_title);
            rxxp_item_price = itemView.findViewById(R.id.rxxp_item_price);
        }
    }
}
