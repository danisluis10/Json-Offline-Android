package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.service.JsonData;
import tutorial.lorence.dummyjsonandroid.view.activities.BaseActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.FragmentRecycler;
import tutorial.lorence.dummyjsonandroid.view.activities.home.loading.FragmentLoading;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeActivity extends BaseActivity implements HomeView {

    private List<User> mGroupUsers = new ArrayList<>();

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    JsonData mJsonData;

    @Inject
    FragmentRecycler mFragmentRecycler;

    @Inject
    HomePresenter mHomePresenter;

    private Disposable mDisposable;

    @Override
    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(this, this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAttributes(Bundle savedInstanceState) {
        super.initAttributes(savedInstanceState);
        if (savedInstanceState == null) {
            mFragmentTransaction.add(R.id.fragment_container, mFragmentLoading);
            mFragmentTransaction.commit();
            mHomePresenter.getUsers();
            showUserOnUI();
        }
    }

    private void showUserOnUI() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, mFragmentRecycler);
                mFragmentTransaction.disallowAddToBackStack();
                mFragmentTransaction.commit();
            }
        }, 1000);
    }

    public List<User> getGroupUsers() {
        return mGroupUsers;
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onGetUsersSuccess(List<User> users) {
        mGroupUsers = users;
    }

    @Override
    public void onGetUsersFailure(String message) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
