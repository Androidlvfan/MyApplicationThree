package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.FindActivity;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import adapter.RcvAdapter;
import api.ApiUrl;
import contract.BannerContract;
import contract.MainGoodContract;
import entity.BannerBean;
import entity.MainGoodBean;
import presenter.BannerPresenter;
import presenter.MainGoodPresenter;

public class FragmentOne extends Fragment implements BannerContract.BannerView,  MainGoodContract.MainGoodView {
private BannerPresenter bannerPresenter;//轮播图p层
private MainGoodPresenter mainGoodPresenter;//多条目p层
private RecyclerView rcv;
private RcvAdapter rcvAdapter;
private EditText find;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        //寻找id
        bannerPresenter = new BannerPresenter(this);//轮播图
        mainGoodPresenter = new MainGoodPresenter(this);//多条目
        rcv = view.findViewById(R.id.main_rcv);//rcv
        find = view.findViewById(R.id.find);//输入框
        //输入框的点击事件
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"搜索",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),FindActivity.class);
                getContext().startActivity(intent);
            }
        });
        rcvAdapter = new RcvAdapter(getActivity());//rcv的Adapter
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //轮播图
        if(bannerPresenter != null){
            bannerPresenter.Banner(ApiUrl.GOOD_BANNER,new HashMap<String, String>());
        }
        //多条目
        if(mainGoodPresenter != null){
            mainGoodPresenter.MainGood(new HashMap<String, String>());
        }
        return view;
    }

    /**
     * 轮播图执行成功
     * @param resultBeans
     */
    @Override
    public void Success(List<BannerBean.ResultBean> resultBeans) {
        rcvAdapter.setResultBean(resultBeans);
    }

    /**
     * 多条目执行成功
     * @param resultBean
     */
    @Override
    public void Successs(MainGoodBean.ResultBean resultBean) {
        if(resultBean != null){
            rcvAdapter.setHomeBean(resultBean);//调用
            rcv.setAdapter(rcvAdapter);
        }else{
            Toast.makeText(getActivity(),"多条目请求失败"+resultBean,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void FailUre(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
