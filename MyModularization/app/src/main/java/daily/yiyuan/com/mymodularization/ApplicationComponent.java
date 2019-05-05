package daily.yiyuan.com.mymodularization;

import android.content.Context;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by za-zhujiangtao on 2018/7/11.
 */

@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface ApplicationComponent {
    Context getContext();

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();
}
