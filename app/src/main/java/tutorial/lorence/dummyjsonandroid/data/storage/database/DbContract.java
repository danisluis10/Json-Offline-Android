package tutorial.lorence.dummyjsonandroid.data.storage.database;

import android.provider.BaseColumns;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public final class DbContract {
    /**
     * Text data type.
     */
    private static final String TEXT_TYPE = " TEXT";
    /**
     * Integer data type.
     */
    private static final String INTEGER_TYPE = " INTEGER";
    /**
     * Comma symbol.
     */
    private static final String COMMA_SEP = ",";
    /**
     * Left bracket symbol.
     */
    private static final String LEFT_BRACKET_SEP = " (";
    /**
     * Right bracket symbol.
     */
    private static final String RIGHT_BRACKET_SEP = " );";
    /**
     * Primary key.
     */
    private static final String PRIMARY_AUTOINCREMENT = " INTEGER PRIMARY KEY AUTOINCREMENT";
    /**
     * Create table statement.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    /**
     * Create query for USER table.
     */
    static final String SQL_CREATE_USER = new StringBuilder(CREATE_TABLE)
            .append(TableUser.TABLE_NAME).append(LEFT_BRACKET_SEP)
            .append(TableUser.COLUMN_NAME_USER_ID).append(INTEGER_TYPE)
            .append(PRIMARY_AUTOINCREMENT)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_USERNAME).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_PASSWORD).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_FULLNAME).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_PATH).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_EMAIL).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(TableUser.COLUMN_NAME_ADDRESS).append(TEXT_TYPE)
            .append(RIGHT_BRACKET_SEP)
            .toString();
    /**
     * Drop table statement.
     */
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    /**
     * Drop query for User table.
     */
    static final String SQL_DELETE_USER = new StringBuilder(DROP_TABLE)
            .append(TableUser.TABLE_NAME).toString();

    /**
     * Constructor. Prevents the DbUser class from being instantiated.
     */
    private DbContract() {
    }

    public abstract static class TableUser implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_FULLNAME = "fullname";
        public static final String COLUMN_NAME_PATH = "path";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ADDRESS = "address";
    }
}
