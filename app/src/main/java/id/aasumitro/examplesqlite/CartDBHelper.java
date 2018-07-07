package id.aasumitro.examplesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TaniCart.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CartContract.CartEntity.TABLE_NAME + " (" +
                    CartContract.CartEntity._ID + " INTEGER PRIMARY KEY," +
                    CartContract.CartEntity.COLUMN_NAME_SELLER_ID + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_SELLER_NAME + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_ITEM_ID + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_ITEM_NAME + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_ITEM_IMAGE + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_ITEM_DESCRIPTION + " TEXT," +
                    CartContract.CartEntity.COLUMN_NAME_ITEM_PRICE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CartContract.CartEntity.TABLE_NAME;

    public CartDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
