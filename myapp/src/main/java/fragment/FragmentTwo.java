package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.QuanAdapter;
import api.ApiUrl;
import contract.QuanContract;
import entity.QuanBean;
import presenter.QuanPresenter;
import retrofit2.http.HeaderMap;

public class FragmentTwo extends Fragment implements QuanContract.QuanView {
    private RecyclerView two_rcv;
    private QuanAdapter quanAdapter;
    private QuanPresenter quanPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        two_rcv = view.findViewById(R.id.two_rcv);
        quanPresenter = new QuanPresenter(this);
        HashMap<String,String> params = new HashMap<>();
        params.put("page","1");
        params.put("count","10");
        quanPresenter.getQuanP(params);
    }

    @Override
    public void Success(List<QuanBean.ResultQuanBean> result) {
        two_rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        quanAdapter = new QuanAdapter(getActivity(),result);
        two_rcv.setAdapter(quanAdapter);
        Toast.makeText(getActivity(),"圈子请求成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void FailUre(String msg) {
       Toast.makeText(getActivity(),msg+"请求失败",Toast.LENGTH_SHORT).show();
    }
}
