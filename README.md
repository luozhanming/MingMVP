# MingMVP
超好用的Android MVP架构库，支持Presenter与UI生命周期绑定。

## 特性
* 1.View层与Presenter层双向绑定
* 2.支持Dialog、Activity、Fragment等View层

## 用法
1.创建UI交互接口
```Java

public interface MainContract {
    interface View extends BaseView{
        void operationView1();

    }

    interface Presenter extends BasePresenter{
        void operation1();
    }
}
```

2.创建View层继承BaseMVPActivity/BaseMVPFragment/BaseMVPDialog并实现交互接口的View接口，重写相关方法
```Java
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

```


3.创建Presenter层类，继承PresenterDelegate方法并实现UI交互接口的Presenter接口
```Java
public class MainPresenter extends PresenterDelegate<MainContract.View> implements MainContract.Presenter {
    public MainPresenter(Context ctx, MainContract.View v) {
        super(ctx, v);
    }

    @Override
    public void operation1() {
        mView.operationView1();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }
}
```

4.在View层类和Presenter层类编写相关操作
