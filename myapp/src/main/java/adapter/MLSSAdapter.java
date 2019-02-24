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

public class MLSSAdapter extends RecyclerView.Adapter<MLSSAdapter.MLSS> {
private Context context;
private List<MainGoodBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MLSSAdapter(Context context, List<MainGoodBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MLSS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mlss_item, viewGroup, false);
        MLSS mlss = new MLSS(view);
        return mlss;
    }

    @Override
    public void onBindViewHolder(@NonNull MLSS mlss, int i) {
        Glide.with(context).load(list.get(i).getMasterPic()).into(mlss.mlss_icon);
        mlss.mlss_title.setText(list.get(i).getCommodityName());
        mlss.mlss_price.setText("￥:"+list.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MLSS extends RecyclerView.ViewHolder {
        private ImageView mlss_icon;
        private TextView mlss_title,mlss_price;
        public MLSS(@NonNull View itemView) {
            super(itemView);
            mlss_icon = itemView.findViewById(R.id.mlss_icon);
            mlss_title = itemView.findViewById(R.id.mlss_title);
            mlss_price = itemView.findViewById(R.id.mlss_price);
        }
    }
}
