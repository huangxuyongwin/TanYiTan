package cn.campsg.com.tanyitan.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.campsg.com.tanyitan.R;
import cn.campsg.com.tanyitan.utils.Utils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_phone, et_pwd;
    private TextInputLayout til_pwd,til_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.page_register);

        Button btn_regist;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setBackgroundColor(getColor(R.color.my_red));
        toolbar.setTitle(getString(R.string.register_title));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_regist = (Button) findViewById(R.id.register_btn);
        et_phone = (EditText) findViewById(R.id.register_ph);
        et_pwd = (EditText) findViewById(R.id.register_pwd);
        til_pwd = (TextInputLayout) findViewById(R.id.register_pwd_til);
        til_phone = (TextInputLayout) findViewById(R.id.register_ph_til);

        assert btn_regist != null;
        btn_regist.setOnClickListener(this);

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                if(til_phone.getError()==null||til_phone.getError().length()!=0){
                    til_phone.setErrorEnabled(true);
                    til_phone.setError("");
                }
                if(c.toString().equals(""))
                    til_phone.setHintEnabled(false);
                else
                    til_phone.setHintEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                if(til_pwd.getError()==null||til_pwd.getError().length()!=0){
                    til_pwd.setErrorEnabled(true);
                    til_pwd.setError("");
                }
                if(c.toString().equals("")) {
                    til_pwd.setHintEnabled(false);
                } else {
                    til_pwd.setHintEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.register_btn:
                regist();
                break;
            default:break;
        }
    }

    private void regist(){
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();

        boolean error_type = true;
        if(!Utils.validatePhone(phone)){
            error_type = false;
            til_phone.setErrorEnabled(true);
            til_phone.setError("请输入合法的手机号码！");
        }

        if(pwd.length() < 6) {
            error_type = false;
            til_pwd.setErrorEnabled(true);
            til_pwd.setError("请输入6位以上密码！");
        }
        if(!error_type)
            return;

        Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }
}
