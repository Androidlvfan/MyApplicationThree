package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.R;

public class AddMinusView extends LinearLayout {
    /**
     * 第一步：创建自定义view，然后继承linearlayout生成三个方法
     * @param context
     */
private TextView addTv,minusTv;
private EditText numTv;
private int num = 1;
    public AddMinusView(Context context) {
        this(context,null);
    }

    public AddMinusView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);//传一个content是为了下一步的连接子布局
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_min_max, this, true);
       addTv = view.findViewById(R.id.add);
      minusTv = view.findViewById(R.id.minus);
      numTv = view.findViewById(R.id.et_num);
      numTv.setText("1");

      //点击+
        addTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                numTv.setText(num+"");

                if(addMinusCallBack != null){
                    addMinusCallBack.numCallBack(num);
                }
            }
        });
        //点击-
        minusTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if(num == 0){
                    num = 1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                numTv.setText(num+"");

                if(addMinusCallBack != null){
                    addMinusCallBack.numCallBack(num);
                }
            }
        });
    }

    /**
     * 获取当前数量
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * 创建回调接口
     */
    private AddMinusCallBack addMinusCallBack;

    public void setAddMinusCallBack(AddMinusCallBack addMinusCallBack){
        this.addMinusCallBack = addMinusCallBack;
    }

    public interface AddMinusCallBack{
        void numCallBack(int num);
    }

}
