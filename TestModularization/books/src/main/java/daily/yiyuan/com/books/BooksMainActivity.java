package daily.yiyuan.com.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import daily.yiyuan.com.common.bean.NewsData;
import daily.yiyuan.com.common.bean.User;

@Route(path = "/books/books_main_activity")
public class BooksMainActivity extends AppCompatActivity {

    private final static String TAG = "BooksMainActivity";

    private Button mJumpToNews;

    //获取参数有2中，方法1
    @Autowired
    public String name;

    //如果想要自定义命名，那么必须传入传递参数的key,如下
    @Autowired(name = "age")
    int mAge;

    @Autowired(name = "user")
    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_main_layout);
        ARouter.getInstance().inject(this);

        mJumpToNews = findViewById(R.id.jump_news);

        Log.e(TAG, "name = " + name + ", mAge = " + mAge);

        //获取传递参数的方法2
        String name = getIntent().getStringExtra("name");
        Log.e(TAG, "name = " + name);

        if (mUser != null){
            Log.e(TAG, "name = " + mUser.getName() + ", age = " + mUser.getAge() + ", sex = " + mUser.getSex());
        }

        mJumpToNews.setOnClickListener(v -> {
            jump2News();
        });

    }

    private void jump2News(){
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



}
