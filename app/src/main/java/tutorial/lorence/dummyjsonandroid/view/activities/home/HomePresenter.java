package tutorial.lorence.dummyjsonandroid.view.activities.home;

import java.util.List;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface HomePresenter {
    void getUsers();

    void setDisposable(Disposable disposable);

    void onGetUsersSuccess(List<User> users);

    void onGetUsersFailure(String message);
}
