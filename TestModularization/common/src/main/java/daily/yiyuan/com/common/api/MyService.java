package daily.yiyuan.com.common.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by za-zhujiangtao on 2018/6/20.
 */

public interface MyService {
    @GET("api/teacher")
    Observable<DataBase> getData(@Query("type") int type, @Query("num") int num);

}
