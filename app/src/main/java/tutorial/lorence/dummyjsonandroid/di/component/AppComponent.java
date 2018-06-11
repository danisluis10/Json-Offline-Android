package tutorial.lorence.dummyjsonandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.lorence.dummyjsonandroid.di.module.AppModule;
import tutorial.lorence.dummyjsonandroid.di.module.DatabaseModule;
import tutorial.lorence.dummyjsonandroid.di.module.LoadingModule;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Singleton
@Component(
        modules = {
                AppModule.class, LoadingModule.class, DatabaseModule.class
        }
)
public interface AppComponent {
        HomeComponent plus(HomeModule module);
}
