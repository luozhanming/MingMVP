package com.example.cdc4512.mingmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luozm.mvplibrary.BaseMVPActivity;
import com.luozm.mvplibrary.BasePresenter;

public class MainActivity extends BaseMVPActivity<MainContract.Presenter> implements MainContract.View {

    Button btn_load;
    TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    protected void bindViews() {
        btn_load = findView(R.id.btn_main_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.operation1();
            }
        });
        tv_data = findView(R.id.tv_main_data);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    public void operationView1() {
        tv_data.setText("数据");
    }


}
