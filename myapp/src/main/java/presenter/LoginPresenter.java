package presenter;

import com.google.gson.Gson;

import net.ValidatorUtil;

import java.util.HashMap;

import contract.LoginContract;
import entity.LoginBean;
import entity.Register_Bean;
import model.LoginModel;

public class LoginPresenter extends LoginContract.LoginPresenter {
    private LoginModel loginModel;
    private LoginContract.LoginView loginView;

    public LoginPresenter( LoginContract.LoginView loginView) {
        this.loginModel = new LoginModel();
        this.loginView = loginView;
    }

    @Override
    public void register(HashMap<String, String> params) {
        //判断手机号
        String phone = params.get("phone");
        if(!ValidatorUtil.isMobile(phone)){
            if(loginView != null){
                loginView.MobileError("手机号不合法");
            }
            return;
        }
        loginModel.register(params, new LoginModel.ModelCallBack() {
            @Override
            public void Success(String result) {
                if(loginModel != null){
                    loginView.success(result);
                }
            }

            @Override
            public void FailUre(String msg) {
                if(loginModel != null){
                    loginView.Fail(msg);
                }
            }
        });
    }

    @Override
    public void login(HashMap<String, String> params) {
        String phone = params.get("phone");
        if(!ValidatorUtil.isMobile(phone)){
            if(loginView != null){
                loginView.MobileError("手机号不合法");
            }
            return;
        }
        loginModel.login(params, new LoginModel.ModelCallBack() {
            @Override
            public void Success(String result) {
                if(loginModel != null){
                    loginView.success(result);
                }
            }

            @Override
            public void FailUre(String msg) {
                if(loginModel != null){
                    loginView.Fail(msg);
                }
            }
        });
    }
}
