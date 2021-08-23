package com.jlpay.bufferknife;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jlpay.annotationreflectproxy.AnnoReflectProxy;
import com.jlpay.annotationreflectproxy.OnClick2;
import com.jlpay.annotationreflectproxy.OnLongClick2;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AnnoReflectProxy.bind(this);
    }

    @OnClick2({R.id.btn_onclick2, R.id.btn_onlongclick2})
    public void lmn(View view) {
        switch (view.getId()) {
            case R.id.btn_onclick2:
                Toast.makeText(this, "btn_onclick2 的 Click2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_onlongclick2:
                Toast.makeText(this, "btn_onlongclick2 的 Click2", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //TODO 注意：这里的返回值必须为boolean，不能为空。不太理解
    @OnLongClick2({R.id.btn_onclick2, R.id.btn_onlongclick2})
    public boolean qwe(View view) {
        switch (view.getId()) {
            case R.id.btn_onclick2:
                Toast.makeText(this, "btn_onclick2 的 OnLongClick2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_onlongclick2:
                Toast.makeText(this, "btn_onlongclick2 的 OnLongClick2", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
