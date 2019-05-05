package daily.yiyuan.com.common.api;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by za-zhujiangtao on 2018/7/11.
 */

@Module
public class ApiModule {
    @Provides
    MyApi provideMyApi(Retrofit retrofit){
        return new MyApi(retrofit);
    }
}
