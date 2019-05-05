package daily.yiyuan.com.mymodularization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    MyApi myApi;

    @BindView(R.id.click)
    Button mBtn;

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
            myApi.getMyData(4, 10)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DataBase>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(DataBase dataBase) {
                            if (dataBase != null) {
                                Log.e("RetrofitActivity", "size = " + dataBase.getData().size());
                            }
                        }
                    });
        });


    }
}
