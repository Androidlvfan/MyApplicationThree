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

public class PZSSAdapter extends RecyclerView.Adapter<PZSSAdapter.PZSS> {
    private Context context;
    private List<MainGoodBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public PZSSAdapter(Context context, List<MainGoodBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PZSS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pzsh_item, viewGroup, false);
        PZSS pzss = new PZSS(view);
        return pzss;
    }

    @Override
    public void onBindViewHolder(@NonNull PZSS pzss, int i) {
        Glide.with(context).load(list.get(i).getMasterPic()).into(pzss.pzsh_icon);
        pzss.pzsh_title.setText(list.get(i).getCommodityName());
        pzss.pzsh_price.setText("￥:"+list.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PZSS extends RecyclerView.ViewHolder {

        private TextView pzsh_title,pzsh_price;
        private ImageView pzsh_icon;

        public PZSS(@NonNull View itemView) {
            super(itemView);
            pzsh_icon = itemView.findViewById(R.id.pzsh_icon);
            pzsh_title = itemView.findViewById(R.id.pzsh_title);
            pzsh_price = itemView.findViewById(R.id.pzsh_price);
        }
    }
}
