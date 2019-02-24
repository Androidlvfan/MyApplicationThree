package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

import java.util.List;

import entity.QuanBean;

public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.Quan> {
    private Context context;
    private List<QuanBean.ResultQuanBean> QuanList;
    private int dianzan_ = 1;

    public QuanAdapter(Context context, List<QuanBean.ResultQuanBean> quanList) {
        this.context = context;
        QuanList = quanList;
    }

    @NonNull
    @Override
    public Quan onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.quan_item, viewGroup, false);
        Quan quan = new Quan(view);
        return quan;
    }

    @Override
    public void onBindViewHolder(@NonNull final Quan quan, int i) {
        quan.nick_name.setText(QuanList.get(i).getNickName());
        quan.create_time.setText(QuanList.get(i).getCreateTime()+"");
        quan.Quan_content.setText(QuanList.get(i).getContent());

        Glide.with(context).load(QuanList.get(i).getHeadPic()).into(quan.head_Pic);
        Glide.with(context).load(QuanList.get(i).getImage()).into(quan.Quan_image);
        //点击点赞功能
        quan.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dianzan_++;
                quan.dianzan_.setText(dianzan_+"");
                Toast.makeText(context,"点赞啦",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return QuanList.size();
    }

    public class Quan extends RecyclerView.ViewHolder {
        private ImageView head_Pic,Quan_image,dianzan;
        private TextView nick_name,create_time,Quan_content,dianzan_;
        public Quan(@NonNull View itemView) {
            super(itemView);
            head_Pic = itemView.findViewById(R.id.head_Pic);
            nick_name = itemView.findViewById(R.id.nick_name);
            create_time = itemView.findViewById(R.id.create_time);
            Quan_content = itemView.findViewById(R.id.Quan_content);
            Quan_image = itemView.findViewById(R.id.Quan_image);
            dianzan = itemView.findViewById(R.id.dianzan);
            dianzan_ = itemView.findViewById(R.id.dianzan_);
        }
    }
}
