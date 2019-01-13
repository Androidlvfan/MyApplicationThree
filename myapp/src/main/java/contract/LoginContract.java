package contract;


import java.util.HashMap;
import model.LoginModel;

public interface LoginContract {
    /**
     * p层
     */
    abstract class LoginPresenter{
        public abstract void register(HashMap<String,String> params);
        public abstract void login(HashMap<String,String> params);
    }
    /**
     * m层
     */
    interface LoginModel{
        void register(HashMap<String,String> params, model.LoginModel.ModelCallBack modelCallBack);
        void login(HashMap<String,String> params, model.LoginModel.ModelCallBack modelCallBack);
    }
    /**
     * v层
     */
    interface LoginView{
        void MobileError(String error);
        void success(String result);
        void Fail(String msg);
    }
}
