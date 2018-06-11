package tutorial.lorence.dummyjsonandroid.service;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tutorial.lorence.dummyjsonandroid.data.storage.database.DbContract;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class JsonData {

    private Context mContext;

    @Inject
    public JsonData(Context context) {
        mContext = context;
    }

    /**
     * Adds {@link User}'s from a JSON file.
     */
    public List<User> getUsersFromJson() {
        List<User> arrUsers = new ArrayList<>();
        try {
            String jsonDataString = convertJsonToString();
            JSONArray userJsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < userJsonArray.length(); ++i) {
                JSONObject userObject = userJsonArray.getJSONObject(i);
                int userID = userObject.getInt(DbContract.TableUser.COLUMN_NAME_USER_ID);
                String username = userObject.getString(DbContract.TableUser.COLUMN_NAME_USERNAME);
                String password = userObject.getString(DbContract.TableUser.COLUMN_NAME_PASSWORD);
                String fullname = userObject.getString(DbContract.TableUser.COLUMN_NAME_FULLNAME);
                String path = userObject.getString(DbContract.TableUser.COLUMN_NAME_PATH);
                String email = userObject.getString(DbContract.TableUser.COLUMN_NAME_EMAIL);
                String address = userObject.getString(DbContract.TableUser.COLUMN_NAME_ADDRESS);
                User user = new User(userID, username, password, fullname, path, email, address);
                arrUsers.add(user);
            }
        } catch (IOException | JSONException exception) {
            Log.e(HomeActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
        return arrUsers;
    }

    /**
     * Reads the JSON file and converts the JSON data to a {@link String}.
     *
     * @return A {@link String} representation of the JSON data.
     * @throws IOException if unable to read the JSON file.
     */
    private String convertJsonToString() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString;
            inputStream = mContext.getResources().openRawResource(mContext.getResources().getIdentifier("json_user", "raw", mContext.getPackageName()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }

}
