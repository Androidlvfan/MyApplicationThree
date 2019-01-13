package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

import entity.BannerBean;

public class BannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
   private Context mContext;
   private List<BannerBean.ResultBean> list;
   private ItemClickListener itemClickListener;
   private int currentPosition = 0;

    public BannerAdapter(Context mContext, ViewPager viewPager) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(this);//为什么清楚之后还在添加？
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    //getCount和isViewFromObject
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        currentPosition = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    //为什么要创建监听接口？？
    public interface ItemClickListener{
        void onItemClick(int index);
    }
}
