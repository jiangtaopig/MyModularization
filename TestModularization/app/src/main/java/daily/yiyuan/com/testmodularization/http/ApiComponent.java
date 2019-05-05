package daily.yiyuan.com.testmodularization.http;


import dagger.Component;
import daily.yiyuan.com.common.api.ApiModule;
import daily.yiyuan.com.common.api.ApplicationComponent;
import daily.yiyuan.com.testmodularization.MainActivity;

/**
 * Created by za-zhujiangtao on 2018/6/22.
 */
@Component(dependencies = ApplicationComponent.class, modules = ApiModule.class)
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}
