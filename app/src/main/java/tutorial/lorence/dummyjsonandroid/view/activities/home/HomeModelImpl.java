package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.data.storage.database.DbAccess.DAUser;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.other.Utils;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;
import tutorial.lorence.dummyjsonandroid.service.IDisposableListener;
import tutorial.lorence.dummyjsonandroid.service.JsonData;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeModelImpl implements HomeModel, IDisposableListener<User> {

    private Context mContext;
    private DAUser mDaUser;
    private HomePresenter mHomePresenter;
    private JsonData mJsonData;
    private DisposableManager mDisposableManager;

    public HomeModelImpl(Context context, DAUser daUser) {
        mContext = context;
        mDaUser = daUser;
    }

    @Override
    public void attachPresenter(HomePresenterImpl homePresenter) {
        mHomePresenter = homePresenter;
    }

    @Override
    public void attachJsonData(JsonData jsonData) {
        mJsonData = jsonData;
    }

    @Override
    public void attachDisposable(DisposableManager disposableManager) {
        mDisposableManager = disposableManager;
        mDisposableManager.setDisposableInterface(this);
    }

    @Override
    public void getUsers() {
        if (Utils.isInternetOn(mContext)) {
            mHomePresenter.setDisposable(mDisposableManager.callDisposable(Observable.just(mJsonData.getUsersFromJson())));
        } else {
            mHomePresenter.onGetUsersFailure(mContext.getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onHandleData(List<User> users) {
        mHomePresenter.onGetUsersSuccess(users);
    }

    @Override
    public void onApiFailure(Throwable error) {
        mHomePresenter.onGetUsersFailure(mContext.getString(R.string.error_time_out));
    }
}
