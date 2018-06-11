package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.service.JsonData;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomePresenterImpl implements HomePresenter {

    private Context mContext;
    private HomeActivity mHomeActivity;
    private HomeView mHomeView;
    private HomeModel mHomeModel;
    private JsonData mJsonData;

    public HomePresenterImpl(Context context, HomeActivity homeActivity, HomeView homeView, HomeModel homeModel, JsonData jsonData) {
        mContext = context;
        mHomeView = homeView;
        mHomeModel = homeModel;
        mHomeActivity = homeActivity;
        mJsonData = jsonData;
        mHomeModel.attachPresenter(this);
        mHomeModel.attachJsonData(mJsonData);
    }

    @Override
    public void getUsers() {
        mHomeModel.getUsers();
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mHomeView.setDisposable(disposable);
    }

    @Override
    public void onGetUsersSuccess() {
        mHomeView.onGetUsersSuccess();
    }

    @Override
    public void onGetUsersFailure(String message) {
        mHomeView.onGetUsersFailure(message);
    }
}
