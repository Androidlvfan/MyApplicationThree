package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.CardTransformer;
import com.example.myapp.R;

import net.OkHttpUtils;

import java.util.List;

import adapter.BannerAdapter;
import api.ApiUrl;
import entity.BannerBean;

public class FragmentOne extends Fragment  {
    private ViewPager viewPager2;
    private BannerAdapter bannerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        //寻找id
       viewPager2 = view.findViewById(R.id.viewPager2);
        initBanner();
        return view;
    }

    private void initBanner() {


        bannerAdapter = new BannerAdapter(getActivity(),viewPager2);


        viewPager2.setAdapter(bannerAdapter);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.setPageMargin(30);
        viewPager2.setClipChildren(false);
        viewPager2.setPageTransformer(true,new CardTransformer());

        viewPager2.setCurrentItem(0);
    }
}
