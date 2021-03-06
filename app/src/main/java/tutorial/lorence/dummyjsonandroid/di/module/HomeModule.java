package tutorial.lorence.dummyjsonandroid.di.module;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.di.scope.ActivityScope;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.UserAdapter;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.ViewType;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.FragmentRecycler;
import tutorial.lorence.dummyjsonandroid.service.JsonData;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class HomeModule {

    private HomeActivity mHomeActivity;

    public HomeModule(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;
    }

    @Provides
    @ActivityScope
    HomeActivity provideHomeActivity() {
        return mHomeActivity;
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    JsonData provideJsonData(Context context) {
        return new JsonData(context);
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    FragmentTransaction provideFragmentTransaction() {
        return mHomeActivity.getSupportFragmentManager().beginTransaction();
    }

    @Provides
    @ActivityScope
    ViewType provideViewType() {
        return new ViewType();
    }

    @Provides
    @ActivityScope
    UserAdapter provideUserAdapter(Context context, ViewType viewType) {
        return new UserAdapter(context, viewType, new ArrayList<User>());
    }

    @Provides
    @ActivityScope
    FragmentRecycler provideFragmentRecycler(Context context, HomeActivity homeActivity, UserAdapter userAdapter) {
        return new FragmentRecycler(context, homeActivity, userAdapter);
    }
}
