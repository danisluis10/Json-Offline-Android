package tutorial.lorence.dummyjsonandroid.view.activities.home;

import io.reactivex.disposables.Disposable;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface HomeView {
    void onGetUsersFailure(String message);
    void onGetUsersSuccess();
    void setDisposable(Disposable disposable);
}
