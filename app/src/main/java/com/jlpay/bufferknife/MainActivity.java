package com.jlpay.bufferknife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jlpay.annotationreflect.AnnotationReflect;
import com.jlpay.bindview.OnClick;
import com.jlpay.bindview.OnLongClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotationReflect.bind(this);
    }


    @OnClick({R.id.btn_onclick, R.id.btn_onlongclick})
    public void abc(View view) {
        switch (view.getId()) {
            case R.id.btn_onclick:
                Toast.makeText(this, "btn_onclick 的 onClick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_onlongclick:
                Toast.makeText(this, "btn_onlongclick 的 onClick", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnLongClick({R.id.btn_onclick, R.id.btn_onlongclick})
    public void edf(View view) {
        switch (view.getId()) {
            case R.id.btn_onclick:
                Toast.makeText(this, "btn_onclick 的 LongClick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_onlongclick:
                Toast.makeText(this, "btn_onlongclick 的 LongClick", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 第三种思路：
     * 利用反射、注解、动态代理实现OnClick与OnLongClick事件的自动注入
     *
     * @param view
     */
    public void annotationReflectProxy(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}