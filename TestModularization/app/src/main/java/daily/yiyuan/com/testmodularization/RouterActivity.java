package daily.yiyuan.com.testmodularization;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = "/app/router_activity")
public class RouterActivity extends AppCompatActivity {

    private final static String TAG = "RouterActivity1";

    @BindView(R.id.msg_tv)
    TextView mMsgTv;

    //获取参数有2中，方法1
    @Autowired
    public String name;

    //如果想要自定义命名，那么必须传入传递参数的key,如下
    @Autowired(name = "age")
    int mAge;

//    @Autowired(name = "user")
//    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router_layout);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        Log.e(TAG, "name = " + name + ", mAge = " + mAge);

        //获取传递参数的方法2
        String name = getIntent().getStringExtra("name");
        Log.e(TAG, "name = " + name);

//        if (mUser != null){
//            Log.e(TAG, "name = " + mUser.getName() + ", age = " + mUser.getAge() + ", sex = " + mUser.getSex());
//        }

    }
}
