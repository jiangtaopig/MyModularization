package daily.yiyuan.com.mymodularization;

import dagger.Component;

/**
 * Created by za-zhujiangtao on 2018/6/22.
 */
@Component(dependencies = ApplicationComponent.class, modules = ApiModule.class)
public interface ApiComponent {
    void inject(MainActivity retrofitActivity);
}
