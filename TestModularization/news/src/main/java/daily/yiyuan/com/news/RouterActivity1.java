package daily.yiyuan.com.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import daily.yiyuan.com.common.bean.NewsData;


@Route(path = "/news/router_activity1")
public class RouterActivity1 extends AppCompatActivity {

    private final static String TAG = "RouterActivity1";

    //获取参数有2中，方法1
    @Autowired
    public String name;

    //如果想要自定义命名，那么必须传入传递参数的key,如下
    @Autowired(name = "age")
    int mAge;

    @Autowired(name = "newsInfo")
    NewsData mNewsData;

//    @Autowired(name = "user")
//    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router1_layout);
        ARouter.getInstance().inject(this);

        Log.e(TAG, "name = " + name + ", mAge = " + mAge);

        //获取传递参数的方法2
        String name = getIntent().getStringExtra("name");
        Log.e(TAG, "name = " + name);

        if (mNewsData != null){
            Log.e(TAG, "book = " + mNewsData.book+", author = "+mNewsData.author);
        }

//        if (mUser != null){
//            Log.e(TAG, "name = " + mUser.getName() + ", age = " + mUser.getAge() + ", sex = " + mUser.getSex());
//        }

    }
}
