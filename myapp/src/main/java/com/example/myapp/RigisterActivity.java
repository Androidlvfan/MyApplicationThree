package com.example.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import contract.LoginContract;
import entity.Register_Bean;
import presenter.LoginPresenter;

public class RigisterActivity extends AppCompatActivity implements LoginContract.LoginView {
private EditText rigister_edit_phone,rigister_edit_pwd,rigister_edit_yzm;
private Button rigister_but;
private LoginPresenter loginPresenter;
private TextView huoqu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        //寻找id
        rigister_edit_phone = findViewById(R.id.rigister_edit_phone);
        rigister_edit_pwd = findViewById(R.id.rigister_edit_pwd);
        rigister_edit_yzm = findViewById(R.id.rigister_edit_yzm);
        rigister_but = findViewById(R.id.rigister_but);
        huoqu = findViewById(R.id.huoqu);//获取二维码
        loginPresenter = new LoginPresenter(this);
    }
        //点击注册
        public void rigister(View view) {
            //获取输入的字符
            String rigister_phone = rigister_edit_phone.getText().toString();
            String rigister_pwd = rigister_edit_pwd.getText().toString();
            String yzm = rigister_edit_yzm.getText().toString();

            HashMap<String,String> params = new HashMap<>();
            params.put("phone",rigister_phone);
            params.put("pwd",rigister_pwd);

            if(loginPresenter != null){
                loginPresenter.register(params);
            }
        }

        //点击登录
        public void login(View view) {
            Intent intent = new Intent(RigisterActivity.this,MainActivity.class);
            startActivity(intent);
        }

    @Override
    public void MobileError(String error) {
        Toast.makeText(RigisterActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String result) {
        Register_Bean register_bean = new Gson().fromJson(result, Register_Bean.class);
        Toast.makeText(RigisterActivity.this,register_bean.getMessage(),Toast.LENGTH_SHORT).show();
        if(register_bean.getMessage().equals("注册成功")){
            Toast.makeText(RigisterActivity.this,"你已"+register_bean.getMessage(),Toast.LENGTH_SHORT).show();
        }
        if(register_bean.getMessage().equals("该手机号已注册，不能重复注册！")){
            Toast.makeText(RigisterActivity.this,register_bean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Fail(String msg) {
        Toast.makeText(RigisterActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
