package callback;

public interface CartCallBack {
    /**
     * 进行全选反选调用的接口
     */
    void notifyCartItem(boolean isChecked,int position);

    /**
     * 调用CartUICallBack
     */
    void notifyNum();
}
