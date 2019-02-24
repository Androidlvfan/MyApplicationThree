package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.recker.flybanner.FlyBanner;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import entity.BannerBean;
import entity.MainGoodBean;

public class RcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private MainGoodBean.ResultBean data;
    private List<BannerBean.ResultBean> bannerList;//轮播图的

    public void setResultBean(List<BannerBean.ResultBean> bannerList) {

        if (bannerList!=null){
            this.bannerList = bannerList;
            notifyDataSetChanged();//刷新
          //  bannerList.clear();
        }

    }
    public void setHomeBean(MainGoodBean.ResultBean data) {
        if (data!=null){
            this.data = data;
            notifyDataSetChanged();//刷新
        }


    }

    public RcvAdapter(Context context) {
        this.context = context;
        }

    private List<String> imgs = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.xbanner, viewGroup, false);
            XreBanVH xreBanVH = new XreBanVH(view);
            return xreBanVH;
        } else if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_rxxp, viewGroup, false);
            RXXP rxxp = new RXXP(view);
            return rxxp;
        } else if (i == 2) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_mlss, viewGroup, false);
            MLSS mlss = new MLSS(view);
            return mlss;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_pzsh, viewGroup, false);
            PZSH pzsh = new PZSH(view);
            return pzsh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof XreBanVH) {
            for (BannerBean.ResultBean bean : bannerList) {
                //imgs.clear();
                imgs.add(bean.getImageUrl());
                Log.i("======",imgs.toString());
            }
            ((XreBanVH) viewHolder).myxbanner.setData(imgs, null);
            ((XreBanVH) viewHolder).myxbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(imgs.get(position)).into((ImageView) view);
                }
            });
        } else if (viewHolder instanceof RXXP) {
            List<MainGoodBean.ResultBean.RxxpBean> rxxp = data.getRxxp();
            RXXPAdapter rxxpAdapter = new RXXPAdapter(context, rxxp.get(0).getCommodityList());
            //在这儿设置为横向滑动
            ((RXXP) viewHolder).item_rxxp.setLayoutManager(new GridLayoutManager(context, 3));
            ((RXXP) viewHolder).item_rxxp.setAdapter(rxxpAdapter);
        } else if (viewHolder instanceof MLSS) {
            List<MainGoodBean.ResultBean.MlssBean> mlss = data.getMlss();
            MLSSAdapter mlssAdapter = new MLSSAdapter(context, mlss.get(0).getCommodityList());
            ((MLSS) viewHolder).item_mlss.setLayoutManager(new LinearLayoutManager(context));
            ((MLSS) viewHolder).item_mlss.setAdapter(mlssAdapter);
        } else if (viewHolder instanceof PZSH) {
            List<MainGoodBean.ResultBean.PzshBean> pzsh = data.getPzsh();
            PZSSAdapter pzssAdapter = new PZSSAdapter(context, pzsh.get(0).getCommodityList());
          ((PZSH) viewHolder).item_pzsh.setLayoutManager(new GridLayoutManager(context, 2));
            ((PZSH) viewHolder).item_pzsh.setAdapter(pzssAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * XBanner轮播
     */
    class XreBanVH extends RecyclerView.ViewHolder {
        XBanner myxbanner;

        public XreBanVH(@NonNull View itemView) {
            super(itemView);
            myxbanner = itemView.findViewById(R.id.myxbanner);
        }
    }

    /**
     * 日销新品
     */
    class RXXP extends RecyclerView.ViewHolder {
        RecyclerView item_rxxp;

        public RXXP(@NonNull View itemView) {
            super(itemView);
            item_rxxp = itemView.findViewById(R.id.item_rxxp);
        }
    }

    /**
     * 魔丽时尚
     */
    class MLSS extends RecyclerView.ViewHolder {
        RecyclerView item_mlss;

        public MLSS(@NonNull View itemView) {
            super(itemView);
            item_mlss = itemView.findViewById(R.id.item_mlss);
        }
    }

    /**
     * 品质生活
     */
    class PZSH extends RecyclerView.ViewHolder {
        RecyclerView item_pzsh;

        public PZSH(@NonNull View itemView) {
            super(itemView);
            item_pzsh = itemView.findViewById(R.id.item_pzsh);
        }
    }

}
