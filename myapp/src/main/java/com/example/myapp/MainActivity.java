package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import contract.LoginContract;
import entity.LoginBean;
import presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginContract.LoginView {
private EditText login_edit_phone,login_edit_pwd;
private TextView rigister;
private LoginPresenter presenter;
private Button login_but;
private CheckBox login_remeber;
private SharedPreferences sharedPreferences;
private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //寻找id
        login_edit_phone = findViewById(R.id.login_edit_phone);
        login_edit_pwd = findViewById(R.id.login_edit_pwd);
        rigister = findViewById(R.id.rigister);
        login_but = findViewById(R.id.login_but);
        login_remeber = findViewById(R.id.login_remeber);

        initData();

          sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
          editor = sharedPreferences.edit();
        boolean ischecked = sharedPreferences.getBoolean("ischecked", false);
        if(ischecked){
            String phone = sharedPreferences.getString("phone", null);
            String pwd = sharedPreferences.getString("pwd", null);
            login_remeber.setChecked(true);
            login_edit_phone.setText(phone);
            login_edit_pwd.setText(pwd);
        }
//点击登录监听
        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取输入框输入的值
                String login_phone = login_edit_phone.getText().toString();
                String login_pwd = login_edit_pwd.getText().toString();
                HashMap<String,String> params = new HashMap<>();
                params.put("phone",login_phone);
                params.put("pwd",login_pwd);

                if(presenter != null){
                    presenter.login(params);
                }

                if(login_remeber.isChecked()){

                        editor.putBoolean("ischecked",true);
                        editor.putString("phone",login_phone);
                        editor.putString("pwd",login_pwd);
                        editor.commit();
                }
            }
        });

        //注册跳转监听
        rigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RigisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        presenter  = new LoginPresenter(this);
    }

    @Override
    public void MobileError(String error) {
        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String result) {
       LoginBean loginBean = new Gson().fromJson(result,LoginBean.class);
        Toast.makeText(MainActivity.this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        if(loginBean.getMessage().equals("登录成功")){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public void Fail(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
//qq登录
    public void qqlogin(View view) {

        if(Build.VERSION.SDK_INT>=23){
            //QQ需要申请写入权限
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }else{
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);

        }

    }


    public void weilogin(View view) {
    }

    public void xinlogin(View view) {
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==123){
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}

