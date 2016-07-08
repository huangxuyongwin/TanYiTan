package cn.campsg.com.tanyitan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.campsg.com.tanyitan.R;
import cn.campsg.com.tanyitan.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher{

    private Button btn_login;
    private EditText et_phone,et_pwd;
    private TextView tv_forget,tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setBackgroundColor(getColor(R.color.my_red));
        toolbar.setTitle(getString(R.string.login_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login = (Button) findViewById(R.id.login_btn);
        et_pwd = (EditText) findViewById(R.id.login_pwd);
        et_phone = (EditText) findViewById(R.id.login_ph);
        tv_forget = (TextView) findViewById(R.id.login_forget);
        tv_msg = (TextView) findViewById(R.id.register_msg);

        btn_login.setOnClickListener(this);
        tv_forget.setOnClickListener(this);

        et_phone.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);

        String phone = getIntent().getStringExtra("phone");
        et_phone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_forget:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                login();
                break;
            default:break;
        }
    }

    private void login(){
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();

        if(!Utils.validatePhone(phone)){
            tv_msg.setText("请输入合法的手机号码！");
            return;
        }

        if(pwd.length() < 6) {
            tv_msg.setText("密码长度必须大于6位！");
            return;
        }

        if(phone.equals("18701721202")&&pwd.equals("qweasd")){
            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            tv_msg.setText("手机号或者密码错误！");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        tv_msg.setText("");
        if(phone.equals("")||pwd.equals("")) {
            btn_login.setTextColor(getColor(R.color.grey));
            btn_login.setClickable(false);
        }else {
            btn_login.setTextColor(getColor(R.color.white));
            btn_login.setClickable(true);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
