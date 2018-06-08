package tutorial.lorence.dummyjsonandroid.data;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class DatabaseInfo {

    // Database version
    public static final int DB_VERSION = 1;

    // Database name
    public static final String DB_NAME = "vogo";

    // Table Listing
    public static class Tables {
        public static final String Listing = "listing";
        public static final String Beams = "beams";
        public static final String Concrete = "concrete";
        public static final String Steel = "steel";
        public static final String ListingOperation = "listingoperation";
        public static final String Operation = "operation";
    }

    public static class Listing {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
    }

    public static class Beams {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_M1 = "m1";
        public static final String COLUMN_M2 = "m2";
    }

    public static class Configuration {
        public static final String COLUMN_MININUM_VERSION = "android-min-version";
        public static final String COLUMN_NEWEST_VERSION = "android-latest-version";
    }

    public static class ListingOperation {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCOPE = "scope";
        public static final String COLUMN_M1 = "m1";
        public static final String COLUMN_M2 = "m2";
        public static final String COLUMN_K1 = "k1";
        public static final String COLUMN_K2 = "k2";
    }

    public static class Concrete {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_VALUE = "value";
    }

    public static class Operation {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_M1 = "m1";
        public static final String COLUMN_M2 = "m2";
        public static final String COLUMN_K1 = "k1";
        public static final String COLUMN_K2 = "k2";
    }

    public static class Steel {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_VALUE = "value";
    }
}
