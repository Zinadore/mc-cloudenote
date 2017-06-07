package iboutsikas.practical.org.cloudenoteapplication;

import android.app.Application;
import iboutsikas.practical.org.cloudenoteapplication.dagger.AppComponent;
import iboutsikas.practical.org.cloudenoteapplication.dagger.AppModule;
import iboutsikas.practical.org.cloudenoteapplication.dagger.DaggerAppComponent;

/**
 * Created by Zinadore on 6/3/2017.
 */

public class InjectableApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent AppComponent() {
        return this.mAppComponent;
    }

}
