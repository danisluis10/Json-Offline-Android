package tutorial.lorence.dummyjsonandroid.data.storage.database.DbAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tutorial.lorence.dummyjsonandroid.data.storage.database.DbContract;
import tutorial.lorence.dummyjsonandroid.data.storage.database.DbHelper;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.User;
import tutorial.lorence.dummyjsonandroid.other.Constants;
import tutorial.lorence.dummyjsonandroid.other.Utils;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class DAUser {

    @Inject
    public void DAUser() {}

    private ContentValues getContentValues(final User user, Context context) {
        ContentValues values = new ContentValues();
        values.put(DbContract.TableUser.COLUMN_NAME_USER_ID, user.getUserID());
        values.put(DbContract.TableUser.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(DbContract.TableUser.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(DbContract.TableUser.COLUMN_NAME_FULLNAME, user.getFullname());
        values.put(DbContract.TableUser.COLUMN_NAME_PATH, user.getPath());
        values.put(DbContract.TableUser.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(DbContract.TableUser.COLUMN_NAME_ADDRESS, user.getAddress());
        return values;
    }

    private User getFromCursor(final Cursor cursor) {
        int userid = cursor.getInt(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_USER_ID));
        String username = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_USERNAME));
        String password = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_PASSWORD));
        String fullname = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_FULLNAME));
        String path = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_PATH));
        String email = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_EMAIL));
        String address = cursor.getString(cursor.getColumnIndex(DbContract.TableUser.COLUMN_NAME_ADDRESS));
        return new User(userid, username, password, fullname, path, email, address);
    }

    public long add(User user, Context context) {
        long id = 0;
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(user, context);
        if (db != null && db.isOpen()) {
            id = db.insert(DbContract.TableUser.TABLE_NAME, null, values);
        }
        return id;
    }

    public boolean edit(User user, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.TableUser.COLUMN_NAME_USERNAME, user.getUsername());
        cv.put(DbContract.TableUser.COLUMN_NAME_PASSWORD, user.getPassword());
        cv.put(DbContract.TableUser.COLUMN_NAME_FULLNAME, user.getFullname());
        cv.put(DbContract.TableUser.COLUMN_NAME_PATH, user.getPath());
        cv.put(DbContract.TableUser.COLUMN_NAME_EMAIL, user.getEmail());
        cv.put(DbContract.TableUser.COLUMN_NAME_ADDRESS, user.getAddress());
        try {
            db.update(DbContract.TableUser.TABLE_NAME, cv, DbContract.TableUser.COLUMN_NAME_USER_ID + "=" + user.getUserID(), null);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void addList(List<User> users, Context context) {
        if (users.size() > 0) {
            DbHelper dbHelper = DbHelper.getInstance(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null && db.isOpen()) {
                for (User user : users) {
                    ContentValues values = getContentValues(user, context);
                    db.insert(DbContract.TableUser.TABLE_NAME, null, values);
                }
            }
        }
    }

    private String[] getProjection() {
        return new String[]{
                DbContract.TableUser.COLUMN_NAME_USER_ID,
                DbContract.TableUser.COLUMN_NAME_USERNAME,
                DbContract.TableUser.COLUMN_NAME_PASSWORD,
                DbContract.TableUser.COLUMN_NAME_FULLNAME,
                DbContract.TableUser.COLUMN_NAME_PATH,
                DbContract.TableUser.COLUMN_NAME_EMAIL,
                DbContract.TableUser.COLUMN_NAME_ADDRESS
        };
    }

    public List<User> getAll(Context context) {
        List<User> listContact = new ArrayList<>();
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db != null && db.isOpen()) {
            Cursor cursor = db.query(DbContract.TableUser.TABLE_NAME, getProjection(),
                    null, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user;
                do {
                    user = getFromCursor(cursor);
                    listContact.add(user);
                } while (cursor.moveToNext());
            }
            // Close the cursor
            cursor.close();
        }
        return listContact;
    }

    public boolean deleteAll(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db != null && db.isOpen()
                && db.delete(DbContract.TableUser.TABLE_NAME, null, null) > 0;
    }

    public boolean deleteById(int index, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(DbContract.TableUser.COLUMN_NAME_USER_ID).append(Constants.EQUAL)
                .toString();
        String[] selectionArgs = {String.valueOf(index)};
        return db != null && db.isOpen()
                && db.delete(DbContract.TableUser.TABLE_NAME, selection, selectionArgs) > 0;
    }

    public boolean deleteByIds(List<Integer> userIds, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String userIDs = TextUtils.join(",", userIds.toArray());
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(DbContract.TableUser.COLUMN_NAME_USER_ID).append(Constants.IN)
                .append(Constants.LEFT_BRACKET_SEP).append(userIDs).append(Constants.RIGHT_BRACKET_SEP).toString();
        return db != null && db.isOpen()
                && db.delete(DbContract.TableUser.TABLE_NAME, selection, null) > 0;
    }

    public List<User> searchForUser(Context context, String data, String type) {
        List<User> contacts = new ArrayList<>();
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(Utils.equalsIgnoreCase(type, "name") ? DbContract.TableUser.COLUMN_NAME_USERNAME : DbContract.TableUser.COLUMN_NAME_FULLNAME)
                .append(Constants.LIKE)
                .toString();
        String[] selectionArgs = {data + Constants.LIKE_SEP};
        if (db != null && db.isOpen()) {
            Cursor cursor = db.query(DbContract.TableUser.TABLE_NAME, getProjection(),
                    selection, selectionArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user;
                do {
                    user = getFromCursor(cursor);
                    contacts.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return contacts;
    }
}
