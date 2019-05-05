package daily.yiyuan.com.testmodularization;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import daily.yiyuan.com.common.api.ApiModule;
import daily.yiyuan.com.common.api.ApplicationComponent;
import daily.yiyuan.com.common.api.ApplicationModule;
import daily.yiyuan.com.common.api.DaggerApplicationComponent;
import daily.yiyuan.com.common.api.DataBase;
import daily.yiyuan.com.common.api.MyApi;
import daily.yiyuan.com.common.bean.NewsData;
import daily.yiyuan.com.common.bean.User;
import daily.yiyuan.com.testmodularization.http.ApiComponent;
import daily.yiyuan.com.testmodularization.http.DaggerApiComponent;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @BindView(R.id.tttex)
    Button mBtn;

    @BindView(R.id.jump_books)
    Button mJumpBooks;

    @Inject
    MyApi mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        ApiComponent component = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .applicationComponent(applicationComponent)
                .build();
        component.inject(this);

        mBtn.setOnClickListener(v -> {
            getData();
        });


    }

    @OnClick(R.id.jump_btn)
    protected void jumpClick() {
        ARouter.getInstance().build("/news/router_activity1")
                .withString("name", "zzjj")
                .withInt("age", 33)
                .withSerializable("newsInfo", new NewsData("盗墓笔记", "南派三叔"))
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e(TAG, "onArrival 跳转成功");
                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        Log.e(TAG, "onFound");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        Log.e(TAG, "onLost");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        Log.e(TAG, "onInterrupt 拦截了");
                    }
                });
    }

    @OnClick(R.id.jump_books)
    protected void onJumpBooks() {
        User user = new User();
        user.setAge(22);
        user.setName("CabinH");
        user.setSex("male");

        ARouter.getInstance().build("/books/books_main_activity")
                .withString("name", "wwqq")
                .withInt("age", 28)
                .withObject("user", user)//用withObject必须要使用 SerializationService，本例中JsonServiceImpl 实现了该接口
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.e(TAG, "onArrival 跳转成功");
                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        super.onFound(postcard);
                        Log.e(TAG, "onFound");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        super.onLost(postcard);
                        Log.e(TAG, "onLost");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        Log.e(TAG, "onInterrupt 拦截了");
                    }
                });
    }

    private void getData() {
        mApi.getMyData(4, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DataBase>() {
                    @Override
                    public void call(DataBase dataBase) {
                        if (dataBase != null) {
                            Log.e("MainActivity", "size = " + dataBase.getData().size());
                        }
                    }
                });
    }
}
